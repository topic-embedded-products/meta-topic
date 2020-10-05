# Boot strategy

This is only the _default_ strategy. Many other strategies are possible, each
with their pros and cons. Just to name a few, one could program the FPGA in
the first stage, boot the R5 first, or skip the second stage completely.

# Boot stages

Booting a Zynq MPSoC device consists of several stages. Below is the preferred
order in topic-platform based projects.

First stage: U-boot SPL
- Set up DDR RAM and initialize clocks and low-level peripherals
- Load PMU firmware into power management unit
- Load ATF firmware for A53 cores
- Load and execute u-boot (second stage)

Second stage: U-boot
- Detect available media and select a boot device
- Load boot script from media and execute it
- Fall back to other media if boot fails

Boot script:
- Location is "/boot/boot.scr"
- Located on user partition(s) on eMMC, QSPI NOR 
- Optional extra setup and hardware detection routines
- Load Linux kernel and devicetree and boot Linux

Linux
- Initialize system
- Load drivers
- Program FPGA
- Load and activate additional drivers (implemented in FPGA)
- Load firmware for secondary processor systems (e.g. R5 cores)
- Run userspace programs

# QSPI NOR partitioning

The default QSPI NOR partitioning is:

- 0 - U-boot SPL (first stage loader, including PMU firmware)
- 1 - U-boot (including ATF)
- 2 - U-boot environment (usually not used)
- 3 - Root filesystem volumes

The first three partitions only use about 1.5MB of the available NOR flash. The
root filesystem volumes occupy the remaining space.

By default the loader expects the root filesystems to be formatted with a UBI
filesystem. This allows read and write access, and offers transparent compression
and data integrity. Using UBI volumes allows for atomic software upgrades.

In its default configuration U-boot requires the boot script (and hence kernel
and devicetree) to be stored in a UBIFS filesystem volume called "qspi-rootfs".

# eMMC and SD card partitioning

The default partitioning for SD cards and the eMMC memory is:

- 1 - FAT16 boot partition (64MB)
- 2 - ext4 rootfs A (40%)
- 3 - ext4 rootfs B (40%)
- 4 - ext4 data (20%)

The boot partition only needs to hold the "boot.bin" and "u-boot.itb" files, so
about 1.5 MB is really required. To accomodate alternative boot strategies that
place other components like kernel, initramfs, FPGA bitstream in this partition
it is created somewhat bigger than required.

One of the two rootfs partitions is marked as "active". The bootloader will
search for a bootscript in the active partition and execute that.

The data partition is not required, it is intended to store data that is not
directly software related and should remain untouched when installing a new
image.

Having two root partitions is not mandatory either, but this allows atomic
updates. The complete set of kernel and root filesystem can be replaced as a
single package. It also allows a simple fallback to a previous version.

# Initializing media

Note that [topic-platform](https://github.com/topic-embedded-products/topic-platform)
contains convenient scripts and utilities to perform these steps automatically.
In particular it integrates SWUpdate for these tasks.
It is highly recommended to use the scripts in topic-platform.

## SD card

To bootstrap a new board, usually SD card is the simplest way to start. Partition
the card as described above. Make sure partition #2 is active. Place the
boot.bin and u-boot.itb files on the primary FAT partition. Unpack the root
filesystem (from OE or Yocto) onto the second partition, labeled sd-rootfs-a.

## QSPI NOR flash

To transfer the bootloader to the QSPI NOR flash, boot with an SD card. Then
write the boot files from the first partitions on the SD card to the flash using
the "flashcp" command:
````flashcp -v boot.bin /dev/mtd0 && flashcp -v u-boot.itb /dev/mtd1```
To write a root filesystem, create a ubifs root filesystem from Yocto/OE and
use "ubiformat" to transfer it to the NOR flash.
