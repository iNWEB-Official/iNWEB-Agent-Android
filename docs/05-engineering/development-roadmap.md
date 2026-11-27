# DOC-11 — Development Roadmap

## Phase 0: Documentation and feasibility
Complete requirements, architecture, device checks, runtime/legal review. Exit: approved first-release scope and feasibility evidence.

## Phase 1: Native Android foundation
Kotlin application, Jetpack Compose shell, app-private workspace, database, project lifecycle, file browser. Exit: project persistence across restart/reboot.

## Phase 2: Safe local execution
Execution supervisor, terminal UI, command policy, cancellation, first validated scripting runtime. Exit: supported local command execution on target phone.

## Phase 3: Agent orchestration
Conversation/task persistence, typed tool contracts, policy gateway, filesystem tools, action logs. Exit: agent completes approved local file tasks.

## Phase 4: Developer workflows
Git, runtime expansion, local build/test, import/export and backup. Exit: representative project edited, versioned, built, and tested locally.

## Phase 5: On-device AI
Integrate candidate engine, benchmark small quantized models, CPU fallback, experimental Vulkan, model manager. Exit: a model passes quality, stability, memory, and thermal criteria.

## Phase 6: Hardening
Threat tests, network isolation, permission review, resource pressure, recovery. Exit: P0 and security acceptance tests pass.

## Phase 7: Release
Signed APK, install/upgrade validation, release notes, limitations, reproducible build guidance. Exit: signed APK verified on target device.

Do not claim APK completion until a real APK has been built, installed, and tested.
