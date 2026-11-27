# iNWEB-Agent Android Documentation

This documentation defines the native Android Local-First AI Development Workstation.

## Document map

| ID | Document | Purpose |
|---|---|---|
| DOC-01 | [Repository audit](00-foundation/repository-audit.md) | Establish the clean Android repository baseline |
| DOC-02 | [Product vision](00-foundation/product-vision.md) | Product goals, principles, boundaries |
| DOC-03 | [Requirements](01-requirements/product-requirements.md) | Functional and non-functional requirements |
| DOC-04 | [System architecture](02-architecture/system-architecture.md) | Component boundaries and data flow |
| DOC-05 | [Device profile](03-platform/device-profile.md) | User-supplied hardware/software baseline |
| DOC-06 | [Resource management](03-platform/resource-management.md) | RAM, storage, thermal budgets |
| DOC-07 | [Local workspace](02-architecture/local-workspace.md) | Local file layout and persistence |
| DOC-08 | [Agent runtime](02-architecture/agent-runtime.md) | Safe local tools and process execution |
| DOC-09 | [Local AI inference](02-architecture/local-ai-inference.md) | On-device model strategy |
| DOC-10 | [Security and privacy](04-security/security-and-privacy.md) | Threats, controls, privacy modes |
| DOC-11 | [Development roadmap](05-engineering/development-roadmap.md) | Implementation phases |
| DOC-12 | [Test strategy](06-quality/test-strategy.md) | Verification and benchmarks |
| DOC-13 | [Release operations](07-release/release-and-operations.md) | APK signing and distribution |
| DOC-14 | [Project work plan](08-project-management/work-plan.md) | Execution and change governance |
| DOC-15 | [Technical decisions](09-decisions/decision-log.md) | Decision records and open questions |

## Technical specifications (DOC-16–28)

The detailed engineering baseline is in [10 — Technical Specifications](10-technical-specifications/). It covers Android modules, data model, workspace filesystem, command execution, agent tool contracts, privacy/network modes, on-device inference, resource benchmarks, UI workflows, threat model, build/release, acceptance tests and implementation backlog.

## Feasibility and device validation (DOC-29–31)

- [Device and platform feasibility](11-feasibility/device-and-platform-feasibility.md)
- [Target device validation protocol](11-feasibility/target-device-validation-protocol.md)
- [Technology validation matrix](11-feasibility/technology-validation-matrix.md)

## Evidence labels

- **Observed:** directly shown by the repository or supplied device evidence.
- **Owner requirement:** explicitly requested by the project owner.
- **Proposed:** recommended design, not yet implemented.
- **Unverified:** requires device measurement or implementation evidence.
- **Verified:** tested with recorded evidence.

## Project separation

The web project `iNWEB-Official/iNWEB-Agent` and this native Android project are separate repositories. Do not transfer web-specific assumptions or modify the web repository during Android work.

## Engineering rules

1. Local-only is the default privacy posture.
2. No project content is uploaded without explicit user consent.
3. Virtual RAM is not counted as physical RAM.
4. Hardware acceleration is not assumed from the chipset name.
5. Model file size is not treated as total runtime memory.
6. AI tool calls are always mediated by a permission and execution policy.
7. Do not claim implementation or test success without evidence.
8. Commit documents independently and verify the resulting GitHub file.
