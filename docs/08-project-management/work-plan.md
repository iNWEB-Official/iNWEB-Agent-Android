# DOC-14 — Project Work Plan

## Work packages

| ID | Work | Depends on | Evidence |
|---|---|---|---|
| WP-00 | Repository audit | None | Audit record |
| WP-01 | Documentation architecture | WP-00 | Index and structure |
| WP-02 | Product vision and requirements | WP-01 | Approved baseline |
| WP-03 | System/component architecture | WP-02 | Architecture docs |
| WP-04 | Device and resource feasibility | WP-03 | Device measurements |
| WP-05 | Security and privacy | WP-03 | Threat model/tests |
| WP-06 | Development roadmap | WP-02–05 | Phase plan |
| WP-07 | QA and release strategy | WP-03–06 | Acceptance matrix |
| WP-08 | Documentation audit | All docs | Link/consistency report |
| WP-09 | Android implementation | Approved baseline | Tested increments |

## Per-document workflow

Scope and evidence review; draft; technical consistency check; link/format validation; independent commit; fetch-back verification; record commit SHA.

## Git policy

Preserve `main`; use `docs/local-first-android-foundation` for documentation; one commit per document; no force-push; do not modify the separate web repository.

## Roles

Project Owner: scope, privacy, budget, legal, and release authority. Engineering/documentation agent: research, drafting, implementation, tests, reporting. Independent verification: inspect actual repository state and test evidence rather than trusting claims.

## Current state

Repository README initialized. Documentation branch contains the initial documentation baseline. Android implementation and APK build have not started.
