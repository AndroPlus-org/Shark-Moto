# HONOR WIN Pad (HES-W00 / HNHES-Q) 6.12.81 port

Preliminary support for the HONOR WIN Pad, an SM8850 (`canoe`) tablet on
MagicOS 11 / Android 17.

| Field | Value |
|---|---|
| Model | `HES-W00` (`HNHES-Q`) |
| Platform | Qualcomm `canoe` / SM8850 |
| Android | 17 (SDK 37), patch 2026-11-01 |
| Kernel | `6.12.81-android16-6-g52d15df5db87-abogki576042287-4k` |
| KMI | `android16-6.12` |
| SELinux | Enforcing (unrooted) |

## Why this profile is preliminary

HONOR builds its own Qualcomm vendor kernel and does not publish that exact
boot image as a GKI zip. `g52d15df5db87` / `abogki576042287` is a vendor-tree
commit, so it also does not exist in `kernel/common`.

The constants in `exploit/src/K61281_plant.c` were therefore recovered from
the closest public build with the same base version:

- `gki-certified-boot-android16-6.12-2026-06_r38.zip`
  - zip SHA-256 `6448888e...96aa71`, `boot-6.12.img` SHA-256
    `da044bb1...114abf`, unpacked `kernel` SHA-256
    `3438fbf1...58c2de`
  - kernel banner: `6.12.81-android16-6-g2be551ddd78c-ab16741175-4k`,
    `#1 SMP PREEMPT Tue Oct 27 17:43:04 UTC 2026`
  - `alloc_posix_timer+0x28 -> kmem_cache_alloc_noprof`
  - `copy_process+0x144 -> kmem_cache_alloc_node_noprof`
  - BTF: `k_itimer=0x100 it=0x78`, `cred=0xb8 uid=0x8`,
    `task_struct=0x1440 cred=0x900`, `selinux_state=0x80 enforcing=0x0`,
    `task_security_struct=0x18 exec_sid=0x8 create_sid=0xc`,
    `tracepoint=0x50 funcs=0x48`
  - static data: `root_user (usage=1 uid=0)`, `panic_on_warn=0`

All addresses are link-time KASLR bases (`0xffffffc080000000` text,
`0xffffffc082...` data). The normal KASLR slide is discovered at runtime, as
with every other port; the constants themselves do not encode a boot-time
slide.

Note that 6.12.81 moved the timer allocation into `alloc_posix_timer`, so the
timer site is **not** inside `do_timer_create` like the 6.12.69 ports.

## Verification sequence

Because the vendor build can differ, run the read-only probe first:

```bash
adb shell "cd /data/local/tmp && ./libk61281.so probe"
```

Expected:

```text
PROBE_TIMER made=... recs=... site=... expected_low21=0xcadac
PROBE_SLIDE=... aligned=1
PROBE_LINEAR_MAP=0xffffff8...
PROBE_TASK=0x... expected_low21=0xc9fa4
PROBE_CRED=0x... req=0xb8 alloc=0xc0 uid_off=0x8
```

If `PROBE_TIMER made` is greater than 0 but `recs=0`, the vendor build moved
the allocation site. Pull the device's `boot` partition, recover the symbols
with `ports/v2505a/recover.py`, and replace `timer_site` / `timer_low21` /
`copy_low21` plus the data addresses in `K61281_plant.c` and both
`profiles.json` files. `recover.py` already scans `alloc_posix_timer`'s
callee and `copy_process+0x140`; on a vendor image use its printed
`bl->kmem_cache_alloc_noprof` line inside `alloc_posix_timer`.

Once `PROBE_TIMER` and `PROBE_TASK` match, run the plant:

```bash
adb shell "cd /data/local/tmp && \
  PYB110_MANAGER=kernelsu PYB110_KMI=android16-6.12 \
  PYB110_MANAGER_PKG=me.weishu.kernelsu \
  PYB110_KSUD=<manager-apk-dir>/lib/arm64/libksud.so \
  ./libk61281.so fake"
```

## Changes

1. `exploit/src/K61281_plant.c` — profile table and `profile_check`.
2. `exploit/rust-kplant/Cargo.toml` / `build.rs` / `exploit/build-all.sh` —
   `k61281` feature and runner build.
3. `exploit/rust-kplant/build.rs` — selects the NDK `windows-x86_64`
   prebuilt and the `.cmd` clang wrapper when building from Windows.
4. `JailbreakCore.kt` — release mapping, `libk61281.so`, universal-manager
   eligibility.
5. `MainActivity.kt` — `ExploitKind.CVE61281`, runner command, HONOR hides the
   ColorOS/vivo-only toggles.
6. `profiles.json` (repo root and app assets) — dynamic profile so beta
   profile sync can select the engine without an app update.

There is no `hooks_defend` gate on this kernel, so the ordinary KernelSU
late-load path and SUSFS (6.12 KO) are used.
