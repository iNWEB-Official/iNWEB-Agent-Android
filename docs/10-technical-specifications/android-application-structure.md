# DOC-16 — Android Application Structure

**Status:** Proposed technical baseline  
**Scope:** Native Android app; no WebView wrapper and no Arena cloud workspace dependency.

## Platform baseline

- Primary language: Kotlin.
- UI: Jetpack Compose with Material 3.
- Build: Gradle Kotlin DSL and Android Gradle Plugin; exact versions pinned after a clean reproducible build.
- Native components: Android NDK/CMake only where a validated runtime requires C/C++.
- Minimum SDK: undecided; select from the required API surface and support policy, not by guess.
- Target SDK: current Play/Android requirements at release time.

## Proposed module boundaries

| Module | Responsibility |
|---|---|
| :app | Application entry, navigation, dependency composition |
| :core:common | Shared result types, IDs, clocks, error model |
| :core:database | Room persistence, migrations, DAOs |
| :core:storage | App-private workspace and SAF adapters |
| :core:policy | Authorization decisions and capability model |
| :core:execution | Process supervisor and command lifecycle |
| :core:agent | Task orchestration and typed tool contracts |
| :core:inference-api | Engine-neutral inference interfaces |
| :core:inference-llamacpp | Optional llama.cpp adapter, only after validation |
| :feature:projects | Project list and lifecycle |
| :feature:editor | File viewing and editing |
| :feature:terminal | Process output and controls |
| :feature:chat | Agent conversation and task history |
| :feature:settings | Privacy, runtime, model and resource settings |

The initial implementation may combine modules to avoid premature build complexity, but must preserve these dependency boundaries.

## Dependency direction

Feature modules depend on core interfaces. Core policy must not depend on UI. The orchestrator can request actions but cannot directly access filesystem, process, or network implementations. Concrete adapters are injected behind interfaces.

## Main-thread rule

Disk I/O, database work, model inference, process management and parsing must not block the UI thread. Use structured concurrency, bounded dispatchers and cancellation propagation.

## Version governance

- Pin Gradle wrapper, AGP, Kotlin, Compose BOM and NDK/CMake versions.
- Prefer stable releases for production.
- Record every dependency, license, version and reason in a software bill of materials.
- No dynamic versions or unreviewed repositories.
- Review Android/Compose release notes before upgrades.

## Evidence required

A clean checkout must sync and build reproducibly; module dependency checks and UI responsiveness tests must pass before architecture is marked implemented.

## References

- Android updates: https://developer.android.com/latest-updates
- Compose setup: https://developer.android.com/develop/ui/compose/setup-compose-dependencies-and-compiler
- NDK concepts: https://developer.android.com/ndk/guides/concepts
