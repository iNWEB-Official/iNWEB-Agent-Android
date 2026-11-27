# DOC-26 — Build and Release Pipeline

**Status:** Proposed.

## Build requirements

- Reproducible Gradle wrapper and pinned plugin/dependency versions.
- Debug and release build types with separate application IDs where appropriate.
- Release minification/optimization tested with R8 rules.
- Native libraries built for explicitly supported ABIs only.
- Dependency lockfiles and software bill of materials.
- No signing secrets or provider API keys in Git.
- Build metadata records commit SHA, version, SDK, toolchain and dependency manifest.

## Pipeline stages

1. Checkout exact commit.
2. Verify wrapper and dependency integrity.
3. Static analysis, formatting and unit tests.
4. Android lint and manifest/permission audit.
5. Instrumented tests on emulator/device.
6. Native ABI/package inspection when NDK code is present.
7. Build APK/AAB.
8. Verify artifact checksum and signing certificate.
9. Install, launch, upgrade and offline smoke test.
10. Publish artifact and release notes only after acceptance.

## Signing

Use protected signing infrastructure or Android-supported secure local signing. Never commit keystore files, passwords or signing tokens. Define key backup and rotation/recovery procedures before public release.

## Release channels

Internal test → owner-approved beta → stable release. Each release includes known limitations, supported Android versions, supported ABIs, model/runtime licenses and data/network behavior.

## Rollback

Keep previous signed release and migration compatibility plan. Database migrations must be backward-safe or provide a tested recovery/backup path. Never delete user workspace during downgrade.

## Acceptance evidence

Build logs, test report, artifact SHA-256, signing certificate fingerprint, installation test and release manifest.
