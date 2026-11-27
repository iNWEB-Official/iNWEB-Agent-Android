# DOC-03 — Product Requirements

These are initial requirements. Acceptance evidence is required before any item is called complete.

## Functional requirements

| ID | Priority | Requirement | Acceptance evidence |
|---|---|---|---|
| FR-001 | P0 | Create, open, rename, and delete local projects | Project lifecycle tests |
| FR-002 | P0 | Persist project files in app-private storage | Restart/reboot tests |
| FR-003 | P0 | Browse, search, read, and edit project files | Filesystem tests |
| FR-004 | P0 | Run approved local commands through a supervisor | Execution tests |
| FR-005 | P0 | Show process output, exit status, and cancellation | Runtime tests |
| FR-006 | P0 | Provide a Local-only mode with no external AI calls | Network isolation test |
| FR-007 | P0 | Show actual storage and memory metrics available to Android apps | Device test |
| FR-008 | P1 | Support local Git workflows | Git integration tests |
| FR-009 | P1 | Support optional on-device model inference | Offline inference test |
| FR-010 | P1 | Support optional external AI with explicit consent | Privacy tests |
| FR-011 | P1 | Import/export project archives | Round-trip tests |
| FR-012 | P1 | Backup and restore local projects | Recovery tests |
| FR-013 | P2 | Manage multiple local models | Model switching tests |
| FR-014 | P2 | Queue/resume eligible tasks | Task lifecycle tests |

## Non-functional requirements

- NFR-001: Core local operations must not require Arena or a cloud workspace.
- NFR-002: Local-only mode must work offline for supported operations.
- NFR-003: Resource use must adapt to available physical RAM, storage, and thermal state.
- NFR-004: Virtual RAM extension must not be counted as physical RAM.
- NFR-005: Destructive operations require permission and confirmation.
- NFR-006: Secrets must not be stored in plaintext or exposed in logs.
- NFR-007: User must know when data is sent outside the device.
- NFR-008: Android platform restrictions must be respected.
- NFR-009: Project data must be exportable in documented formats.
- NFR-010: Performance claims require repeatable target-device measurements.

## Initial exclusions

- Root access as a baseline requirement.
- Silent cloud synchronization.
- Automatic upload of source files.
- Guaranteed compatibility with all desktop Linux packages.
- Unrestricted always-on background execution.

## Open requirements

Minimum supported Android version, runtime packaging, model selection, external provider scope, and backup encryption policy require technical validation or owner decision.
