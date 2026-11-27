# DOC-20 — Agent and Tool API Contract

**Status:** Proposed.

## Design

Use versioned, typed tool contracts. Model-generated arguments are untrusted input. A tool call is a proposal, never authorization.

## Envelope

```json
{
  "schema_version": "1",
  "call_id": "uuid",
  "task_id": "uuid",
  "tool": "workspace.read_file",
  "arguments": {
    "project_id": "uuid",
    "relative_path": "README.md"
  }
}
```

The actual implementation must validate JSON schema, field types, lengths, allowed enum values and unknown fields. The example is illustrative, not an implemented API.

## Initial local tools

| Tool | Capability | Default |
|---|---|---|
| workspace.list | Read | Allow within active project |
| workspace.read_file | Read | Allow within active project and size cap |
| workspace.search | Read | Allow bounded search |
| workspace.write_file | Write | Approval according to scope/risk |
| workspace.create_directory | Write | Approval according to scope/risk |
| workspace.delete | Destructive | Always confirm |
| runtime.run | Execute | Policy allowlist and limits |
| git.status/diff/log | Read | Allow in project |
| git.add/commit | Write | Explicit approval policy |
| model.list/load/unload | Resource | User-visible and resource-gated |

## Response envelope

Return call_id, status, structured result or stable error code, duration, truncation flags and audit reference. Do not return raw secrets or unbounded process output.

## Error categories

INVALID_ARGUMENT, NOT_FOUND, PERMISSION_DENIED, APPROVAL_REQUIRED, RESOURCE_LIMIT, TIMEOUT, CANCELLED, UNSUPPORTED, IO_ERROR, RUNTIME_ERROR, POLICY_BLOCKED.

## Versioning

Breaking changes increment the contract major version. Tool schemas are stored locally with the app version and covered by compatibility tests. Unknown tools are denied, not guessed.

## Prompt-injection defense

File contents, terminal output, repository text and retrieved documents are data, not trusted instructions. The orchestrator must preserve the instruction hierarchy and apply policy independently of model reasoning.
