# DOC-07 — Local Workspace and Storage

## Default storage

Project files should live in app-private internal storage. Use Android Storage Access Framework for user-selected imports, exports, or shared folders. Do not request broad storage permissions without a justified need.

## Logical layout

```text
iNWEB-Agent/
├── workspace/projects/
├── workspace/imports/
├── runtime/
├── models/
├── database/
├── logs/
├── cache/
└── backups/
```

Actual paths must follow Android storage APIs and sandbox rules.

## Storage planning

The owner's device screenshot reported 128 GB total internal storage and about 92.6 GB available at capture time. This is historical, not live capacity.

Initial planning envelopes (not fixed partitions):
- Projects: up to 25 GB.
- Model assets: up to 15 GB.
- Runtime/dependencies: up to 8 GB.
- Cache/temp: up to 5 GB.
- Backups: up to 10 GB.
- Preserve an adaptive free-space reserve.

Query free space before downloads, builds, and archive extraction. Never assume these budgets are available.

## File safety

- Resolve paths under approved workspace roots.
- Reject traversal and symlink escape.
- Use atomic writes where possible.
- Confirm recursive deletion and destructive replacement.
- Bound archive extraction size and entry count.
- Validate archive paths before extraction.
- Keep recoverable operation metadata for multi-file edits.

## Backup and restore

Use a versioned manifest, file list, and checksums. Validate archives before restore. Backup destinations and encryption behavior must be visible to the user. No automatic cloud upload.

## Privacy

No project content leaves the device in Local-only mode.
