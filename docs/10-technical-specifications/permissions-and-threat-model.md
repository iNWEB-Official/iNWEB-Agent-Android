# DOC-25 — Permissions and Threat Model

**Status:** Proposed security baseline; requires formal review before release.

## Assets

Project source, user documents, credentials, model files, execution outputs, task history, app signing keys and device resources.

## Threat actors and risks

- Malicious or prompt-injected project files.
- Model-generated unsafe commands or paths.
- Compromised/malicious runtime package.
- Malformed import archive or model file.
- Accidental user approval of destructive/network action.
- Other apps attempting to access private workspace.
- Dependency compromise or update tampering.
- Resource-exhaustion input causing app crash or data loss.

## Controls

- Android app sandbox; no root requirement.
- App-private workspace and SAF user-selected access.
- Strict path canonicalization and archive validation.
- Typed tool contracts and default-deny policy gateway.
- Allowlisted executables, argument arrays, bounded environment.
- Process timeouts, output caps, concurrency and cancellation.
- Network disabled in Local-only mode.
- Keystore-backed secret protection.
- Dependency/license review and checksums.
- Redacted audit logs; no secrets in diagnostics.
- Explicit confirmation for destructive operations.
- Signed release artifacts and documented update channel.

## Permission minimization

Request Android permissions only when a feature needs them. Avoid broad file/media access when SAF is sufficient. Microphone, camera, location and contacts are not required by the initial local development workstation baseline.

## Security verification

Abuse-case tests, dependency scan, secret scan, archive fuzzing, path traversal suite, prompt injection tests, local-only network tests, process escape attempts, backup/restore tests and release signature verification.

## Residual risks

Android app sandbox is not a full container for arbitrary native code. Running untrusted binaries inside the app process boundary is high risk. Runtime support must be constrained and threat-reviewed; do not claim strong isolation without a separately validated sandbox.
