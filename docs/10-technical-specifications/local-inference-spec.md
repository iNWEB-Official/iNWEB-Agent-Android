# DOC-22 — On-Device Inference Specification

**Status:** Proposed; hardware and engine support remain unverified.

## Engine boundary

Define an engine-neutral interface for model discovery, metadata, load, generate, cancel, unload and telemetry. Candidate native backend: llama.cpp, subject to Android NDK packaging, license review and target-device benchmarks. Do not hard-wire the orchestrator to one engine.

## Model admission

Before installation/load, validate:
- source and user consent;
- license and redistribution/use restrictions;
- model format and supported architecture;
- cryptographic checksum;
- quantization and context configuration;
- estimated memory and storage requirement;
- tokenizer/template compatibility.

## Resource control

Model file size is not peak memory. Budget weights, KV cache, context, compute buffers, native allocator overhead, UI, database and OS headroom. Begin with 1B–3B quantized candidates as experiments; 4B is conditional; 7B is not a default target on this 8 GB physical-RAM device.

## Runtime behavior

- CPU path is mandatory fallback.
- Vulkan/GPU path remains experimental until the exact GPU/driver and runtime are verified.
- Default context length is conservative and configurable.
- Expose load/unload and stop controls.
- Reject or reduce a workload before memory pressure becomes critical.
- Do not keep a model resident indefinitely when idle.
- Thermal throttling and battery impact are measured, not inferred.

## Model catalog

Store model name, publisher, exact revision, file size, checksum, license URL, quantization, architecture, context limit, download origin and validation state. Never silently replace a model revision.

## Acceptance criteria

Offline generation, cancellation, repeated load/unload, memory peak, thermal soak, battery impact, output correctness, corrupted-model rejection and app lifecycle recovery on the actual target device.
