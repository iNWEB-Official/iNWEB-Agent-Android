# DOC-31 — Technology Validation Matrix

**Status:** Proposed test matrix; no candidate is approved solely by this document.

| Candidate | Role | Known Android path | Main risk | Required proof | Current decision |
|---|---|---|---|---|---|
| Kotlin + Compose | Native UI | Official Android toolchain | Build/dependency compatibility | Clean build and target-device install | Preferred |
| Room/SQLite | Metadata | Android Jetpack | Migration/recovery design | Migration and crash tests | Preferred candidate |
| App-private files + SAF | Project data | Android platform APIs | Uninstall loss; SAF URI lifecycle | Persistence, export and restore tests | Preferred |
| llama.cpp | Local LLM CPU backend | Official Android docs and NDK arm64 build | Native packaging, memory, thermal, license | Build, install, offline generation and soak test | First candidate |
| llama.cpp KleidiAI | CPU optimization | Current build docs for arm64-v8a | CPU feature and integration compatibility | Compare against portable CPU baseline | Experimental |
| llama.cpp OpenCL | GPU acceleration | Documented Android NDK build path | OpenCL ICD/driver availability on this phone | Runtime enumeration and measured speedup | Defer |
| Vulkan backend | GPU acceleration | Backend build options exist | Device feature/driver/backend support | Vulkan enumeration + inference correctness/stability | Defer |
| Embedded Node/Python | Project runtimes | No single guaranteed system runtime | Packaging size, native dependencies, Android restrictions | One runtime proof-of-concept per language | Not selected |
| Arbitrary Linux shell | General execution | Not a native Android guarantee | Sandbox and ABI incompatibility | Security architecture and strict compatibility tests | Out of scope as a promise |

## Selection rules

1. Choose the simplest implementation that meets local-first requirements.
2. Require a working proof-of-concept on the target device before adopting native dependencies.
3. Compare CPU and accelerator backends on identical model, context and prompt sets.
4. Record upstream project revision, license, build flags and ABI for every native dependency.
5. Prefer supported public Android APIs; do not rely on hidden APIs or root.
6. A backend that is merely buildable is not accepted until it runs correctly and safely on-device.

## Research references

- llama.cpp Android: https://github.com/ggml-org/llama.cpp/blob/master/docs/android.md
- llama.cpp build options: https://github.com/ggml-org/llama.cpp/blob/master/docs/build.md
- Android app-specific storage: https://developer.android.com/training/data-storage/app-specific
- Android Vulkan feature flags: https://developer.android.com/reference/android/content/pm/PackageManager
- Android 16 SDK setup: https://developer.android.com/about/versions/16/setup-sdk
