# DOC-18 — Workspace and Filesystem Specification

**Status:** Proposed.

## Storage layout

Use app-internal files directory as the default private workspace. Example logical layout:

```text
files/
  workspace/
    projects/<project-id>/
      project.json
      src/
      assets/
      .agent/
        task-logs/
        checkpoints/
  models/
    <model-id>/
  runtimes/
    <runtime-id>/
  exports/
  recovery/
```

The exact Android filesystem path is runtime-derived and must never be hard-coded or shown as a portable path.

## Storage rules

1. Project files remain on-device by default.
2. Model weights and runtimes are stored separately from project source.
3. Cache and regenerable indexes are separated from durable user data.
4. Check available bytes before large writes or model downloads.
5. Use atomic temp-write, fsync where supported, and rename for critical metadata.
6. Maintain a recovery journal for multi-step destructive operations.
7. Do not follow symbolic links outside the authorized workspace boundary.
8. Canonicalize and validate every relative path; reject traversal, absolute paths and NUL bytes.

## Import/export

Use Android Storage Access Framework document pickers for user-selected external files. The user chooses source/destination; do not request broad storage access for ordinary project operations. Import archives are extracted into a staging directory after validating entry count, uncompressed size, path traversal, duplicate paths and compression ratio. Promote staging only after successful validation.

## File editing

- Save through temporary file and atomic replacement when supported.
- Detect external modification before overwriting.
- Keep bounded undo/recovery snapshots.
- Large files use streaming I/O and explicit size limits.
- Binary files must not be silently decoded as text.

## Capacity policy

Show actual free storage from Android APIs. Warn before model installation or export when space is low. Keep user data, caches and models independently clearable. Never delete project files automatically to reclaim space.

## Acceptance tests

Traversal rejection, permission boundary tests, interrupted writes, full-storage behavior, SAF cancellation, archive-bomb defenses, import/export round-trip and process restart recovery.
