# DOC-10 — Security and Privacy

## Privacy modes

**Local-only:** no external AI provider calls, no project upload, no cloud workspace dependency, and network access denied to agent tools unless a specifically approved local workflow requires it.

**External AI:** provider selection, data disclosure, and explicit consent before project content is transmitted. Minimize data and redact secrets.

**Hybrid:** local by default; remote routing visible and consented.

## Threats

Prompt injection in repository files, malicious generated commands, path traversal, symlink escape, credential leakage, malicious dependencies/models, unsafe archives, resource exhaustion, and accidental external data transfer.

## Required controls

1. Typed tool schemas and validation.
2. Workspace path confinement.
3. Confirmation for destructive actions.
4. Supervised process execution with timeout/output/concurrency caps.
5. Android Keystore-backed secret protection where applicable.
6. Secret redaction in logs.
7. Integrity checks for downloaded artifacts.
8. Explicit network and provider permissions.
9. Treat project content as untrusted data, not policy instructions.
10. Audit tool actions without recording secrets.

## Android posture

No root requirement. Prefer app-private storage. Use Storage Access Framework for user-selected files. Respect Android 16 permission and background execution rules.

## Acceptance tests

Verify Local-only network blocking, path traversal rejection, unauthorized file denial, destructive command confirmation, secret redaction, archive validation, and external transfer disclosure.
