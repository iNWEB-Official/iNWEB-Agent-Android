# DOC-30 — Target Device Validation Protocol

**Status:** Ready for execution when a diagnostic APK or authorized ADB session is available. No device measurements are claimed by this document.

## 1. Required environment record

Capture timestamp, model, manufacturer, Android release, SDK, build fingerprint, security patch, kernel, app version/commit, battery percentage, charging state, ambient conditions and free storage.

## 2. Required device queries

The diagnostic app must collect these using public Android APIs where available:

| Measurement | Android source/API | Required output |
|---|---|---|
| Model/build | Build.MODEL, DEVICE, MANUFACTURER, FINGERPRINT | Exact runtime identity |
| Android/API | Build.VERSION.RELEASE, SDK_INT, SECURITY_PATCH | Runtime platform |
| ABI | Build.SUPPORTED_ABIS | Ordered ABI list |
| Memory | ActivityManager.MemoryInfo; Debug.MemoryInfo | totalMem, availMem, lowMemory, app PSS/native/Java |
| Storage | StatFs on app files directory | total and available bytes |
| Vulkan presence | PackageManager.FEATURE_VULKAN_HARDWARE_LEVEL / VERSION / COMPUTE | Feature flags and version integer |
| Vulkan device | Native Vulkan instance/device enumeration | vendor/device IDs, API/driver version, extensions |
| GPU renderer | EGL/GL renderer and version strings | Actual driver-reported renderer/version |
| Thermal | PowerManager thermal status APIs where supported | status transitions during load |
| CPU | Runtime.availableProcessors and /proc/cpuinfo only if access is permitted | visible core count and available identifiers |

Do not treat PackageManager feature flags as a substitute for successful Vulkan device enumeration and an actual inference-backend smoke test.

## 3. Optional ADB cross-check

When a trusted computer and USB debugging are available, capture:
- `adb shell getprop ro.product.model`
- `adb shell getprop ro.build.version.release`
- `adb shell getprop ro.build.version.sdk`
- `adb shell getprop ro.product.cpu.abilist`
- `adb shell dumpsys meminfo <package>`
- `adb shell df -h /data`
- `adb shell dumpsys thermalservice`

ADB is an optional verification route, not a requirement for normal app use. Do not ask the owner to expose private identifiers or secrets in a public issue.

## 4. Benchmark procedure

1. Reboot device; allow idle stabilization.
2. Record baseline memory, storage, battery and thermal status.
3. Cold-launch app; measure launch and memory.
4. Create project, write/read/search files, force-stop and relaunch; verify persistence.
5. Run a bounded test command/runtime; capture exit status, duration, output and cancellation.
6. Load a small CPU model; record model checksum, quantization, context, thread count, load time, peak PSS, prompt processing and generation rate.
7. Run 15-minute sustained inference; sample thermal and memory periodically.
8. Repeat after cooling; minimum three runs for comparable performance tests.
9. Test low-storage and memory-pressure handling without risking user files.
10. Repeat with Vulkan only if feature checks and backend build both succeed.

## 5. Safety limits

- Never run stress tests while the phone is unusually hot, charging under high thermal load, or reporting critical thermal status.
- Stop inference if thermal status reaches severe/critical, the app is killed, or memory pressure threatens user data.
- Do not use fixed memory figures as guaranteed capacity.
- Keep a conservative system/UI reserve; derive usable model budget from observed app and system memory.
- Do not benchmark by deleting user data or disabling system safety mechanisms.

## 6. Acceptance record template

| Field | Result |
|---|---|
| Device/build | Pending |
| ABI | Pending |
| Vulkan feature flags/API | Pending |
| GPU renderer/driver | Pending |
| Free storage | Pending |
| Baseline/peak app PSS | Pending |
| Model/checksum/quantization/context | Pending |
| Load time / tokens per second | Pending |
| Thermal start/end/max | Pending |
| Stability/repeated runs | Pending |
| Pass/fail and limitations | Pending |

## 7. Completion rule

A capability is **Verified** only when the exact handset has produced repeatable evidence. Vendor specifications and third-party device databases are planning references, not substitutes for on-device measurements.
