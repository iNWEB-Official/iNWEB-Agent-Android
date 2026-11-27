# DOC-29 — Device and Platform Feasibility Assessment

**Assessment date:** 2026-11-27  
**Target:** Xiaomi Redmi 15, model 25062RN2DA  
**Status:** Desktop research completed; on-device measurements pending. This is not a performance certification.

## 1. Confirmed baseline

| Item | Finding | Evidence status |
|---|---|---|
| Android | Android 16 / API 36 | Owner screenshot + Android API mapping |
| SoC | Qualcomm Snapdragon 685 | Xiaomi official product/FAQ |
| CPU | 8 cores; 4× Cortex-A73 up to 2.8 GHz + 4× Cortex-A53 up to 1.9 GHz | Xiaomi official FAQ |
| GPU | Qualcomm Adreno 610 | Xiaomi official FAQ |
| Physical RAM | 8 GB | Owner screenshot and clarification |
| Memory extension | 8 GB virtual; excluded from physical-RAM budget | Owner clarification |
| Storage | 128 GB internal | Owner screenshot + Xiaomi specification |
| Co-processor/NPU | Xiaomi FAQ says no co-processor | Vendor FAQ; no separate NPU capability established |
| ABI list | Not yet read from this exact handset | Unverified |
| Vulkan version/features | Not yet queried on handset | Unverified |
| Sustained performance/thermal limits | Not measured | Unverified |

Sources:
- Xiaomi Redmi 15 specifications: https://www.mi.com/global/product/redmi-15/specs/
- Xiaomi Redmi 15 FAQ (CPU/GPU/co-processor): https://www.mi.com/global/support/faq/details/KA-592870/
- Android 16 SDK/API 36: https://developer.android.com/about/versions/16/setup-sdk
- Android PackageManager Vulkan feature detection: https://developer.android.com/reference/android/content/pm/PackageManager

## 2. Feasibility conclusions

### Native Android app — FEASIBLE
Kotlin + Jetpack Compose is a suitable native implementation direction. Build against Android SDK 36 for Android 16 behavior validation. Minimum SDK remains a product-support decision and must not be inferred from the target handset.

### On-device files and projects — FEASIBLE
Android app-private internal storage provides an isolated persistent directory without broad storage permission. Use Android Storage Access Framework for explicit user-selected import/export. App-specific files are removed on uninstall, so export/backup must be explicit and visible.

### Local command/runtime execution — CONDITIONALLY FEASIBLE
Android apps run within an app sandbox and do not provide a general desktop Linux environment. Do not promise arbitrary shell commands, package compatibility, root access, or desktop-style background daemons. Bundle or integrate only runtimes proven to work on the device ABI and Android release. Commands must run through the app's policy-controlled supervisor.

### Local AI inference — CONDITIONALLY FEASIBLE
The current llama.cpp Android documentation describes a native Android path and NDK cross-compilation for arm64-v8a. This makes it a credible CPU-first candidate, not a selected or tested engine. Build and benchmark on the exact device before adoption.

### GPU inference — UNVERIFIED / EXPERIMENTAL
Adreno 610 is confirmed by Xiaomi, but the exact handset's exposed Vulkan version, feature level, driver, and compatibility with the chosen inference backend have not been measured. Android PackageManager feature flags and native Vulkan enumeration must both be checked. Do not assume Vulkan compute support from the GPU name alone.

### NPU inference — NOT A BASELINE
Xiaomi's FAQ reports no co-processor for this model. No NPU-backed inference requirement should be placed in the initial release.

## 3. Model feasibility posture

- Begin with small quantized models in the 1B–3B range as test candidates, not guaranteed recommendations.
- 4B candidates only after measured memory and thermal tests.
- 7B is excluded from the default target profile until compelling handset evidence exists.
- Model weight size is not peak memory: include KV cache, context, compute buffers, native allocator, UI and Android system headroom.
- CPU path is the required baseline. GPU acceleration is optional and must never be required for core app functionality.

## 4. Technology recommendation

| Area | Current recommendation | Gate |
|---|---|---|
| App | Kotlin + Jetpack Compose | Clean install/build |
| Build | Gradle Kotlin DSL; SDK 36 compile/target validation | Reproducible CI build |
| Persistence | Room/SQLite metadata + app-private files | Migration and crash tests |
| File exchange | Storage Access Framework | Round-trip tests |
| Native inference candidate | llama.cpp Android integration, arm64-v8a first | License, build, device benchmark |
| GPU backend | Defer | Vulkan and backend-specific validation |
| Runtime packages | Evaluate one runtime at a time | ABI, sandbox, lifecycle and security tests |
| Remote AI | Excluded from Local-only baseline | Separate explicit-consent design |

## 5. Blockers before implementation lock

1. Read exact ABI list from the handset.
2. Query Vulkan feature flags, API version and physical-device properties.
3. Measure free storage, memory headroom and thermal behavior.
4. Build and install a minimal native diagnostic APK.
5. Benchmark a CPU inference candidate and record peak memory, latency, stability and temperature.
6. Decide minimum supported Android version and supported ABI policy.

## 6. Decision

Proceed with a native Kotlin/Compose shell, local workspace, database and policy architecture. Keep runtime execution and local inference behind interfaces until handset validation passes. Do not commit to Vulkan, NPU, a specific model, or arbitrary shell compatibility at this stage.

## Source references

- llama.cpp Android integration/build notes: https://github.com/ggml-org/llama.cpp/blob/master/docs/android.md
- llama.cpp Android/arm64 build options: https://github.com/ggml-org/llama.cpp/blob/master/docs/build.md
- Android app-specific storage: https://developer.android.com/training/data-storage/app-specific
