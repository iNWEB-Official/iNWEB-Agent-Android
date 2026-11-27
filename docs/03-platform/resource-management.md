# DOC-06 — Resource Management

## Physical memory

The device has 8 GB physical RAM. The additional 8 GB is virtual memory extension and is not equivalent to physical RAM; it must not justify larger model budgets.

## Initial workload envelope

These are provisional targets, not guaranteed allocations:

| Workload | Planning range |
|---|---:|
| Main app | 350–500 MB |
| Runtime/tools | 400–800 MB |
| Initial model working set | 1.5–2.5 GB preferred |
| Build/test tasks | 500 MB–1 GB when headroom permits |
| System/other apps | Outside app control |
| Safety headroom | Adaptive; preserve under pressure |

Measure actual memory on the device. Android may kill processes under system pressure.

## Workload modes

- Normal: routine file work and measured inference.
- Constrained: reduce concurrency/context and defer large builds.
- Critical: pause nonessential tasks and unload model.
- Thermal constrained: reduce sustained CPU/GPU load.

Thresholds must be calibrated using device telemetry.

## Storage

The screenshot reported 92.6 GB available of 128 GB at capture time. Re-query before large operations. Suggested planning ceilings: projects 25 GB, models 15 GB, runtimes 8 GB, cache 5 GB, backups 10 GB. Preserve a configurable free-space reserve.

## Monitoring

Show supported app memory, available memory, free storage, workspace/model sizes, running tasks, and thermal state where public APIs expose it. Mark unavailable metrics as unavailable.

## Acceptance

Model unload releases memory; cancellation stops managed processes; cache cleanup never deletes projects; storage pressure warns before writes/downloads; pressure handling preserves data integrity.
