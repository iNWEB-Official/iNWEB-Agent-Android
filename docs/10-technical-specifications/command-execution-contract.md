# DOC-19 — Command Execution Contract

**Status:** Proposed; high-risk subsystem requiring security review.

## Non-negotiable boundary

The agent is not a shell with unrestricted authority. Every action is a typed request evaluated by the policy gateway and executed by a supervisor. No root access, hidden shell, or unrestricted command execution is assumed.

## Execution request

A request contains:
- request_id and task_id;
- executable/runtime identifier from an allowlisted registry;
- argument array (never a concatenated shell string);
- authorized working-directory project ID and relative path;
- environment allowlist;
- timeout, output-byte limit and resource budget;
- requested capabilities and user-approval token, if needed.

## Execution lifecycle

`QUEUED → VALIDATING → AWAITING_APPROVAL (if needed) → RUNNING → SUCCEEDED | FAILED | CANCELLED | TIMED_OUT | DENIED`

Terminal states are immutable. Cancellation is idempotent. Every transition is timestamped.

## Supervisor responsibilities

- Resolve executable only from app-managed runtime locations.
- Pass arguments as an array; do not invoke a shell parser by default.
- Use a minimal environment; redact secrets.
- Set working directory inside the authorized project.
- Enforce wall-clock timeout, output caps and concurrency limits.
- Stream stdout/stderr with backpressure.
- Terminate child process on cancellation or app shutdown according to policy.
- Record exit code, duration, truncation and termination reason.
- Never execute commands embedded in model output without validation.

## Permission tiers

| Tier | Example | Rule |
|---|---|---|
| Read | List/read project files | Project-scoped |
| Write | Create/edit project files | Project-scoped; destructive writes confirm |
| Execute | Run allowlisted local tool | Explicit runtime policy |
| Network | Fetch dependencies/API | Disabled in Local-only mode; explicit approval otherwise |
| Destructive | Delete project, overwrite broad paths | Per-action confirmation |

## Android constraints

A process started by an app remains subject to Android app sandbox, lifecycle and background execution restrictions. Do not promise desktop Linux compatibility. Validate each runtime on target ABI and Android release.

## Acceptance tests

Argument injection, path traversal, symlink escape, timeout, cancellation, output flood, process leak, background kill, concurrent-run limit, permission revocation and local-only network denial.
