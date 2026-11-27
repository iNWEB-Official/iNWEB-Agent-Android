# DOC-28 — Implementation Backlog

**Status:** Proposed sequencing; no implementation implied by this document.

## Epic A — Android skeleton
- A1 Create native Kotlin/Compose project.
- A2 Pin toolchain and dependency versions.
- A3 Add navigation, theme and app lifecycle.
- A4 Add CI build and lint.
**Exit:** clean reproducible APK installs on target device.

## Epic B — Local workspace
- B1 App-private project storage.
- B2 Project metadata and Room schema.
- B3 Project create/open/rename/archive/delete.
- B4 File browser, editor and atomic save.
- B5 SAF import/export.
**Exit:** persistence and file-safety tests pass.

## Epic C — Execution supervisor
- C1 Runtime registry and capability model.
- C2 Typed execution request/response.
- C3 Process supervisor, output streaming and cancellation.
- C4 Limits, audit log and approval UI.
**Exit:** safe representative local command executes on device.

## Epic D — Agent orchestration
- D1 Conversation/task persistence.
- D2 Typed tool schema registry.
- D3 Policy gateway.
- D4 Planning and tool-result loop.
- D5 Prompt-injection and permission tests.
**Exit:** approved local file task completes with auditable changes.

## Epic E — Developer workflows
- E1 Git capability assessment and local Git adapter.
- E2 Runtime packaging feasibility (Python/Node/other candidates).
- E3 Build/test workflow feasibility.
**Exit:** one representative project can be edited, versioned and tested locally.

## Epic F — On-device inference
- F1 Benchmark candidate engine.
- F2 Validate model formats/licenses.
- F3 Model import/management.
- F4 CPU inference integration.
- F5 Optional accelerator experiment.
**Exit:** candidate passes quality, memory, thermal and stability gates.

## Epic G — Hardening and release
- G1 Threat and permission review.
- G2 Offline/network isolation tests.
- G3 Backup/restore and upgrade tests.
- G4 Signed APK and release evidence.
**Exit:** P0 acceptance matrix passes.

## Dependency policy

Do not start high-risk inference or arbitrary runtime execution before workspace, policy and resource controls exist. Parallelize independent documentation and tests, not changes that share mutable files.
