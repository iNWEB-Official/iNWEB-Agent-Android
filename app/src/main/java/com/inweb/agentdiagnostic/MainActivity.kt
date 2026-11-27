package com.inweb.agentdiagnostic

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.opengl.EGL14
import android.opengl.GLES20
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.PowerManager
import android.os.StatFs
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.json.JSONObject
import java.text.DateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    private var report by mutableStateOf(JSONObject())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        report = collectDiagnostics(this)
        setContent {
            MaterialTheme {
                val export = rememberLauncherForActivityResult(
                    ActivityResultContracts.CreateDocument("application/json")
                ) { uri ->
                    if (uri != null) contentResolver.openOutputStream(uri)?.use {
                        it.write(report.toString(2).toByteArray(Charsets.UTF_8))
                    }
                }
                val data = report
                Column(
                    Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("iNWEB Device Diagnostic", style = MaterialTheme.typography.headlineSmall)
                    Text("Local-only hardware and Android capability report")
                    data.keys().asSequence().forEach { key ->
                        val value = data.opt(key)
                        ElevatedCard(Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(12.dp)) {
                                Text(key, style = MaterialTheme.typography.titleSmall)
                                Text(value?.toString() ?: "Not available")
                            }
                        }
                    }
                    Button(onClick = { report = collectDiagnostics(this@MainActivity) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Refresh diagnostics")
                    }
                    OutlinedButton(onClick = { export.launch("iNWEB-device-diagnostic.json") }, modifier = Modifier.fillMaxWidth()) {
                        Text("Export JSON report")
                    }
                    Text("No internet permission. No telemetry. Export only when you choose a destination.",
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

private fun collectDiagnostics(context: Context): JSONObject {
    val out = JSONObject()
    fun put(k: String, v: Any?) { out.put(k, v ?: JSONObject.NULL) }
    put("generated_at", DateFormat.getDateTimeInstance().format(Date()))
    put("device_manufacturer", Build.MANUFACTURER)
    put("device_brand", Build.BRAND)
    put("device_model", Build.MODEL)
    put("device_product", Build.PRODUCT)
    put("device", Build.DEVICE)
    put("android_release", Build.VERSION.RELEASE)
    put("android_sdk", Build.VERSION.SDK_INT)
    put("build_id", Build.DISPLAY)
    put("supported_abis", Build.SUPPORTED_ABIS.joinToString(", "))
    put("available_processors", Runtime.getRuntime().availableProcessors())

    val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val mem = ActivityManager.MemoryInfo()
    am.getMemoryInfo(mem)
    put("ram_total_bytes", mem.totalMem)
    put("ram_available_bytes", mem.availMem)
    put("ram_low_memory", mem.lowMemory)
    put("app_memory_class_mb", am.memoryClass)
    put("app_large_memory_class_mb", am.largeMemoryClass)

    val dataStats = StatFs(Environment.getDataDirectory().absolutePath)
    put("data_partition_total_bytes", dataStats.totalBytes)
    put("data_partition_available_bytes", dataStats.availableBytes)
    val filesStats = StatFs(context.filesDir.absolutePath)
    put("app_data_partition_available_bytes", filesStats.availableBytes)

    val pm = context.packageManager
    put("vulkan_feature_level", if (Build.VERSION.SDK_INT >= 24 && pm.hasSystemFeature("android.hardware.vulkan.level")) "present" else "not_declared")
    put("vulkan_version_feature", if (Build.VERSION.SDK_INT >= 24 && pm.hasSystemFeature("android.hardware.vulkan.version")) "present" else "not_declared")
    put("vulkan_compute_feature", pm.hasSystemFeature("android.hardware.vulkan.compute"))
    put("gles_version_feature", pm.systemAvailableFeatures.firstOrNull { it.name == "android.hardware.opengles.aep" }?.name ?: "not_declared")
    put("thermal_status", if (Build.VERSION.SDK_INT >= 29) {
        val power = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        power.currentThermalStatus
    } else "unsupported_api")

    val egl = readEglInfo()
    put("egl_initialized", egl.first)
    put("opengl_es_version", egl.second)
    put("gpu_renderer", egl.third)
    put("gpu_vendor", egl.fourth)
    put("gpu_glsl_version", egl.fifth)
    put("vulkan_physical_device_enumeration", "not_implemented_in_this_build")
    put("limitations", "PackageManager feature flags are declarations, not a Vulkan device/driver benchmark. Sustained performance, GPU compute, CPU topology and thermal throttling require dedicated on-device tests.")
    return out
}

private fun readEglInfo(): Five {
    return try {
        val display = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY)
        val versions = IntArray(2)
        val initialized = EGL14.eglInitialize(display, versions, 0, versions, 1)
        if (!initialized) return Five(false, null, null, null, null)
        val attrs = intArrayOf(EGL14.EGL_RENDERABLE_TYPE, 4, EGL14.EGL_NONE)
        val configs = arrayOfNulls<android.opengl.EGLConfig>(1)
        val count = IntArray(1)
        val chose = EGL14.eglChooseConfig(display, attrs, 0, configs, 0, 1, count, 0)
        val config = configs.firstOrNull()
        if (!chose || config == null) {
            EGL14.eglTerminate(display)
            return Five(true, null, null, null, null)
        }
        val ctx = EGL14.eglCreateContext(display, config, EGL14.EGL_NO_CONTEXT,
            intArrayOf(EGL14.EGL_CONTEXT_CLIENT_VERSION, 2, EGL14.EGL_NONE), 0)
        val current = ctx != null && ctx != EGL14.EGL_NO_CONTEXT &&
            EGL14.eglMakeCurrent(display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, ctx)
        val result = if (current) Five(
            true,
            GLES20.glGetString(GLES20.GL_VERSION),
            GLES20.glGetString(GLES20.GL_RENDERER),
            GLES20.glGetString(GLES20.GL_VENDOR),
            GLES20.glGetString(GLES20.GL_SHADING_LANGUAGE_VERSION)
        ) else Five(true, null, null, null, null)
        if (current) EGL14.eglMakeCurrent(display, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)
        if (ctx != null && ctx != EGL14.EGL_NO_CONTEXT) EGL14.eglDestroyContext(display, ctx)
        EGL14.eglTerminate(display)
        result
    } catch (_: Throwable) { Five(false, null, null, null, null) }
}
private data class Five(val first: Boolean, val second: String?, val third: String?, val fourth: String?, val fifth: String?)
