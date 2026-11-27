# DOC-02 — Product Vision

## Product

**iNWEB-Agent Android — Local-First AI Development Workstation**

## Vision

Turn a capable Android phone into a practical, portable AI-assisted software development environment. The phone should own the project files and execute supported development operations locally rather than depending on Arena's cloud workspace.

## Owner requirements

- Provide a native Android application rather than a browser wrapper.
- Use the device's own RAM, internal storage, CPU/GPU, and local execution capabilities.
- Keep generated and edited project files on the phone by default.
- Run supported commands and development tools locally.
- Avoid Arena cloud workspace dependency.
- Support local AI inference where device capability permits.

## Product principles

1. Local-first storage and execution.
2. Offline-capable core workflows.
3. Explicit consent for external AI/data transfer.
4. Safe, bounded execution of AI-generated actions.
5. User control over files, models, backups, and deletion.
6. Transparent capability and resource reporting.
7. Modular model, runtime, and tool adapters.
8. Graceful behavior under memory, storage, and thermal pressure.

## Product boundaries

This is not a website WebView, an unrestricted root shell, or a promise that every desktop Linux tool runs unchanged on Android. External AI may be offered as an optional mode, but must never be confused with local inference.

## Success outcomes

- Projects are created and stored locally.
- Project files persist across restarts.
- Supported commands run on-device.
- Local-only workflows work with network disabled.
- Model inference can be performed offline on a validated configuration.
- A signed APK installs and passes device acceptance tests.

## Status

Product vision is a proposed baseline derived from owner requirements. Implementation has not started.
