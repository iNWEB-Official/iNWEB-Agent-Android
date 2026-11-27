# DOC-13 — Release and Operations

## Release package

Signed APK, version/build metadata, release notes, supported Android versions, install/upgrade instructions, checksum, known limitations, and permission/privacy summary.

## Signing

Release keys must never be committed or printed in logs. Use secure key custody and document recovery without exposing secrets.

## Channels

- Debug: developer diagnostics.
- Internal: controlled test distribution.
- Release: signed and validated artifact.

## Validation

Test fresh install, upgrade, project data retention, interrupted upgrade recovery, and uninstall/reinstall behavior. Document Android backup behavior accurately.

## Diagnostics

Redact tokens, credentials, private keys, and sensitive project content. User controls diagnostic export.

## Release gate

Do not call a release production-ready until P0 requirements and security acceptance criteria pass on the target device and APK signature is verified.
