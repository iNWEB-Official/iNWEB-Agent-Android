# DOC-21 — Network and Privacy Modes

**Status:** Proposed; Local-only is the default.

## Modes

### Local-only (default)
- No external AI provider calls.
- No source-code upload, telemetry upload, cloud sync or remote execution.
- Local operations continue offline where supported.
- Dependency installation/network tools are denied.
- Enforce at the network adapter/policy layer, not merely by prompt wording.

### Explicit network session
- User chooses the provider/action and sees what data categories will leave the device.
- Consent is scoped to the session or action, not blanket consent.
- Show destination/provider, purpose and data summary before transfer.
- Secrets and unrelated project files are excluded by default.
- Record consent and transfer metadata locally without storing credentials in logs.

## Network policy

Default-deny outbound access. Permit only registered adapters and destinations. No arbitrary model-directed URL fetch. DNS/IP handling, TLS validation, redirects, certificate errors and timeouts are controlled by the adapter. Disable cleartext traffic unless a documented local-development exception applies.

## Secrets

Use Android Keystore-backed encryption for locally stored credentials where supported. Never embed provider keys in source, APK resources, logs, crash reports or exported project archives. Provide revoke/clear controls.

## Telemetry

No analytics by default in the initial release. Any future diagnostic sharing must be opt-in, previewable and redact project content and secrets.

## Verification

Automated network-denial tests in Local-only mode; proxy/packet-level test on target device; consent-scope tests; secret scanning; redirect and certificate validation tests.
