# DOC-27 — Test and Acceptance Matrix

**Status:** Proposed; acceptance gates for the first usable release.

| Area | Required evidence | Release gate |
|---|---|---|
| Native app | Installs and launches on target Android 16 phone | P0 |
| Local workspace | Create/edit/reopen project after force-stop/reboot | P0 |
| File safety | Traversal and unauthorized path tests denied | P0 |
| Execution | Allowlisted command, timeout and cancellation verified | P0 |
| Privacy | Local-only network test proves no external requests | P0 |
| Persistence | Database migrations and interrupted writes recover | P0 |
| Resource | Low-memory/storage behavior does not corrupt data | P0 |
| Security | No secrets in source, APK assets or logs | P0 |
| UI | Core workflows usable with accessibility checks | P1 |
| Git | Local status/diff/commit workflows verified | P1 |
| Inference | Offline model load/generation on actual device | P1 |
| Import/export | Round-trip and malicious archive tests pass | P1 |
| Release | Signed APK installs/upgrades and checksum recorded | P0 |

## Definition of Done

A feature is done only when:
- requirements and acceptance criteria are linked;
- implementation is merged to the intended branch;
- automated tests pass;
- target-device checks are complete where required;
- security/privacy impact is reviewed;
- documentation and known limitations are updated;
- evidence is retained and independently verified.

## Severity

P0 blocks release; P1 blocks the corresponding feature; P2 may be deferred with a documented issue and owner-visible limitation.

## Reporting

Report verified facts separately from proposed design and agent-reported claims. Include test command/device/build identifiers and failures; do not report only "passed".
