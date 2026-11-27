# DOC-12 — Test Strategy

## Test layers

- Unit: path validation, policy decisions, resource calculations, schemas, database migrations.
- Integration: project persistence, file operations, process execution/cancel, Git, model load/unload, backup restore.
- Device: Android 16 install, process death, reboot, storage/memory pressure, thermal behavior, background limits, acceleration detection.
- Privacy/security: offline isolation, consent, path traversal, secret redaction, archive validation, command restrictions.

## Benchmark record

Record exact device/build, runtime and engine versions, model and quantization, file size, context, backend, peak memory, prompt/generation throughput, battery/thermal observations, repetitions, and duration.

## Acceptance matrix

| Capability | Evidence |
|---|---|
| Project persistence | Reopen after process kill and reboot |
| Offline workspace | Complete supported task with network disabled |
| Local inference | Generate output offline |
| External AI controls | Consent and data disclosure verified |
| Command execution | Allowed, denied, timeout, cancellation cases |
| File safety | Traversal and unauthorized path tests |
| Resource manager | Pressure response |
| APK | Install, launch, upgrade, signature verification |

Report Passed, Failed, Blocked, Not Run, or N/A. Include steps, expected/actual result, environment, evidence, and limitations. Never fabricate test results.
