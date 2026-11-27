# DOC-09 — Local AI Inference

## Target

Support optional on-device inference with a conservative memory profile and CPU fallback.

## Device baseline

Xiaomi Redmi 15, model identifier 25062RN2DA, Snapdragon 685 4G Mobile Platform, octa-core CPU up to 2.80 GHz, 8 GB physical RAM plus 8 GB virtual extension, 128 GB storage, Android 16.

Exact GPU/driver and acceleration APIs are unverified. Do not infer a GPU model or Vulkan compatibility from the chipset label alone.

## Model selection strategy

Start by evaluating quantized 1B–3B parameter models. A 4B-class model is experimental and must pass memory, latency, thermal, and quality tests. 7B-class models are not default targets for this physical-RAM configuration.

Model file size is not total inference memory. Include weights, KV cache, context, compute buffers, runtime overhead, and application memory.

## Runtime candidate

Evaluate llama.cpp for Android ARM64 as a candidate, not a preselected guaranteed solution. Verify supported model formats and acceleration backends.

## Modes

- **Local-only:** inference on device; external AI requests blocked.
- **External:** remote provider only after explicit selection and data disclosure.
- **Hybrid:** local default; remote routing requires explicit consent.

## Context and acceleration

Begin benchmarks at modest context sizes (e.g. 2K tokens), then evaluate 4K. CPU is the required fallback. Vulkan is experimental until verified with the actual device driver.

## Benchmark record

Record model, quantization, file size, context, engine version, backend, peak memory, prompt/generation throughput, thermal behavior, battery impact, and test duration.
