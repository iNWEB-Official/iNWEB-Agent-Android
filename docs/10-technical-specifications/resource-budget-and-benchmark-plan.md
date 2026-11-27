# DOC-23 — Resource Budget and Benchmark Plan

**Status:** Initial test plan; thresholds are provisional until measurements.

## Device facts

Target: Xiaomi Redmi 15, Snapdragon 685 4G, 8 GB physical RAM, 8 GB virtual extension, 128 GB internal storage, Android 16. Virtual RAM is not counted as physical RAM. Exact ABI, GPU, Vulkan and thermal behavior require runtime inspection.

## Resource classes

Measure separately:
- Java/Kotlin heap;
- native heap and mapped model pages;
- total process PSS/RSS where available;
- system available memory;
- workspace/model/cache disk use;
- CPU utilization and wall time;
- battery delta and thermal status.

## Benchmark scenarios

1. Cold launch and warm launch.
2. Project open, file search and large-file edit.
3. Small script execution and cancellation.
4. Model load, prompt processing and token generation.
5. 15-minute sustained inference.
6. Concurrent UI + inference + filesystem activity.
7. Low-storage and low-memory behavior.
8. Background/foreground transition and process recreation.

## Test matrix

Record OS build, app commit, runtime versions, ABI, model checksum, quantization, context length, thread count, temperature/thermal state, battery level, ambient conditions and repeat count. Run at least three repetitions for performance comparisons and report median plus range.

## Guardrails

Do not use fixed memory figures as guaranteed device capacity. Keep an OS/UI safety margin. Pause or refuse heavy inference under critical thermal/memory pressure. Never kill or corrupt user work to reclaim memory. Model recommendations are generated from measured fit, not marketing parameter counts.

## Required outputs

Raw benchmark CSV/JSON, environment manifest, summary report, crash/ANR logs with secrets redacted, and pass/fail decision for each candidate.
