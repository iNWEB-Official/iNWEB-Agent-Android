# DOC-05 — Target Device Profile

## Evidence source

Owner-provided screenshot of the Android device information page, plus owner's clarification that 8+8 GB represents physical RAM plus virtual memory extension.

| Field | Observed value |
|---|---|
| Device | Xiaomi Redmi 15 |
| Model | 25062RN2DA |
| SoC | Snapdragon 685 4G Mobile Platform |
| CPU | Octa-core, up to 2.80 GHz |
| Physical RAM | 8 GB |
| Virtual RAM extension | 8 GB |
| Internal storage | 128 GB |
| Available storage at screenshot time | About 92.6 GB |
| Android | 16 |
| OS version string | 3.0.305.0.WBOMIXM.C07 |
| Build | BP2A.250605.031.A3 |
| Security update shown | 2026-09-01 |
| Kernel | 5.15.194-android13-8-00019-gf4321180a397-ab15212794 |

## Unverified

Exact GPU model, graphics driver, Vulkan support, neural acceleration availability, memory bandwidth, sustained thermal performance, and inference engine compatibility.

## Device validation checklist

- Query Build.MODEL, SDK, ABI list.
- Inspect GPU renderer and driver.
- Check Vulkan feature support.
- Measure app memory and free storage.
- Benchmark CPU inference.
- Test thermal throttling and battery behavior.
- Test Android 16 background execution constraints.

Screenshot values are not live device telemetry.
