# DOC-17 — Local Data Model

**Status:** Proposed; schema must be validated during implementation.

## Persistence principles

- Room/SQLite is the proposed metadata store; files remain ordinary files in the app-private workspace.
- Database records are indexes and metadata, not the sole copy of source files.
- Every user-owned entity has a stable UUID and creation/update timestamps.
- Use explicit schema versions and tested migrations.
- Never persist API secrets, model prompts containing secrets, or raw credentials in ordinary tables/logs.

## Core entities

| Entity | Important fields | Purpose |
|---|---|---|
| Project | id, name, root_uri, created_at, updated_at, archived_at | Project identity |
| WorkspaceEntry | id, project_id, relative_path, kind, size, modified_at, content_hash | Indexed file metadata |
| Conversation | id, project_id, title, created_at, updated_at | Chat container |
| Message | id, conversation_id, role, content, created_at, status | Conversation history |
| TaskRun | id, conversation_id, state, started_at, finished_at, cancellation_reason | Agent task lifecycle |
| ToolInvocation | id, task_run_id, tool_id, arguments_redacted, decision, result_summary, timestamps | Auditable tool calls |
| PermissionGrant | id, scope, capability, granted_at, expires_at, revoked_at | Narrow user approvals |
| ModelAsset | id, display_name, file_path, format, quantization, bytes, checksum, license_ref | Local model inventory |
| RuntimeProfile | id, runtime_id, version, abi, config_json | Installed runtime metadata |
| AppSetting | key, value, updated_at | Non-secret settings |

## Data integrity

- Foreign keys enabled.
- Unique constraints on project-relative paths and model checksums where appropriate.
- Use transactions for multi-record state changes.
- Task state transitions validated in repository/service layer.
- File operations and DB updates must use recoverable ordering; record pending operations if a crash can split them.
- Hashes use SHA-256 for integrity checks, not as a substitute for access control.

## Retention and deletion

Project deletion is a destructive action: show affected file count and size, require confirmation, then delete through a recoverable workflow. Conversation deletion and diagnostic-log retention must be configurable. No hidden retention or remote copy.

## Migrations and backup

Every schema change increments the Room schema version and includes migration tests from every supported prior version. Backup archives include a manifest, schema version, checksums and optional encryption metadata. Restore validates archive paths and sizes before writing.

## Open implementation decisions

Exact column types, FTS strategy, WAL policy, backup format, encryption key lifecycle and retention defaults require implementation/security review.
