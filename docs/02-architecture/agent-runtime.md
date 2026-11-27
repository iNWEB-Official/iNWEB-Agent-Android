# DOC-08 — Local Agent Runtime

## Goal

Allow an AI agent to inspect and modify project files and run supported local development tasks without unrestricted device access.

## Initial tool interface

- list/read/search files
- create/edit/delete files
- create directories
- run approved command/script
- run tests/build
- inspect Git status and create commits
- monitor resource use

Each tool must have typed arguments, permission policy, bounded output, timeout, cancellation, and audit metadata.

## Execution supervisor

Enforce approved working directory, argument validation, environment allowlist, execution timeout, output cap, process cancellation, concurrency limits, and resource-pressure handling.

## Android feasibility

Android is not a desktop Linux distribution. Node.js, Python, Git, package managers, compilers, and Linux userlands each require a separately validated packaging and execution strategy. Do not label a runtime supported until tested on the target device.

## Security boundary

The model must never directly execute arbitrary shell text. Model-generated requests pass through the policy gateway and supervisor. No root access is required for baseline functionality.

## Background work

Long-running tasks must comply with Android foreground-service and background execution rules for the selected target SDK. Unrestricted persistent background operation is not promised.
