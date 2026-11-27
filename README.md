# iNWEB-Agent Android

**Project:** Native Android Local-First AI Development Workstation  
**Repository:** [iNWEB-Official/iNWEB-Agent-Android](https://github.com/iNWEB-Official/iNWEB-Agent-Android)

## Purpose

Build a native Android application that provides an AI-assisted development workspace using the phone's own storage, memory, CPU/GPU capabilities, and locally executed tools wherever feasible.

The product goal is to avoid relying on Arena's hosted workspace. Project files and supported execution workflows should remain on the user's device by default.

## Product principles

- Local-first files and execution.
- Offline operation for supported local workflows.
- Explicit consent before any project data is sent to external AI services.
- Safe, permissioned AI tool execution.
- Transparent memory, storage, thermal, and model limitations.
- Modular runtimes and model providers.

## Target device baseline

Owner-provided device screenshot and clarification:
- Xiaomi Redmi 15
- Model identifier: 25062RN2DA
- Snapdragon 685 4G Mobile Platform
- Octa-core CPU, up to 2.80 GHz
- 8 GB physical RAM + 8 GB virtual memory extension
- 128 GB internal storage
- Android 16
- MIUI/HyperOS version string shown: 3.0.305.0.WBOMIXM.C07

The screenshot showed approximately 92.6 GB available storage at capture time. This is not a live value. Exact GPU/driver capabilities and runtime performance must be measured on-device.

## Documentation

The `docs/` directory contains product foundation, requirements, architecture, device/resource planning, security, engineering roadmap, quality, release, and project governance documents.

## Scope boundary

This is a new native Android project. The separate web application repository [iNWEB-Agent](https://github.com/iNWEB-Official/iNWEB-Agent) is not this Android project and must not be modified as part of Android documentation or implementation.

## Status

Documentation and feasibility planning. No APK or native Android implementation is claimed yet.
