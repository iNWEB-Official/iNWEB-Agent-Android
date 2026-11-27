# DOC-24 — UI Navigation and Core Workflows

**Status:** Proposed.

## Primary navigation

- Home / Recent projects
- Projects
- Agent / Chat
- Files / Editor
- Terminal / Runs
- Models / Runtime
- Settings / Privacy

Use adaptive layouts while prioritizing one-handed phone use. Keep active project and privacy mode visible.

## Project lifecycle

Create project → choose template or empty workspace → create local project directory and metadata transactionally → open project dashboard. Rename and delete actions show clear consequences. Delete requires explicit confirmation.

## Agent task workflow

User describes task → orchestrator creates plan → each tool proposal is validated → approval is requested when required → supervisor executes → progress/output is streamed → final result lists files changed, commands run, warnings and verification.

## File editing

Open file → detect text/binary and size → edit with bounded memory → save atomically → show unsaved state and conflict warnings. Never silently overwrite external changes.

## Model workflow

Browse local catalog → inspect license, size and requirements → verify available storage → import/download only after user action → checksum → load → run offline test → unload.

## Privacy affordances

Local-only is visibly selected by default. Any remote request presents provider, purpose and data summary. A global emergency stop cancels active tasks and inference.

## Accessibility and usability

Support dynamic font scaling, screen readers, contrast, keyboard navigation and clear error recovery. Long-running operations show cancellable progress; do not imply background continuation unless the platform permits it.
