# DOC-15 — Technical Decision Log

## ADR-001: Separate Android repository

**Status:** Accepted  
**Decision:** Native Android application and its documentation live in `iNWEB-Official/iNWEB-Agent-Android`. The existing `iNWEB-Official/iNWEB-Agent` remains the separate web application.  
**Reason:** Prevent scope and source-code mixing.

## ADR-002: Local-first workspace

**Status:** Accepted as product requirement  
**Decision:** Local device storage and local execution are default. External AI/data transfer requires explicit consent.  
**Reason:** Owner explicitly wants project files and execution to remain on-device.

## ADR-003: Physical RAM accounting

**Status:** Accepted  
**Decision:** Plan against 8 GB physical RAM. Do not count 8 GB virtual extension as equivalent physical memory.  
**Reason:** Virtual memory has different performance and availability characteristics.

## ADR-004: CPU fallback

**Status:** Proposed  
**Decision:** CPU inference is mandatory fallback; GPU/Vulkan acceleration is experimental until device verification.  
**Reason:** Exact GPU/driver capabilities are not yet established.

## Open decisions

- Minimum Android version.
- Kotlin/Gradle module structure and dependency policy.
- Local process runtime and isolation approach.
- Inference engine and model license/selection.
- Database and migration strategy.
- External AI providers and disclosure UX.
- Backup encryption and recovery policy.
- Release signing and distribution policy.

Record each future decision with context, options, consequences, status, and validation evidence.
