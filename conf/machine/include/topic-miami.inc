#@TYPE: Machine
#@DESCRIPTION: Machine configuration for the Topic Miami SOM.

include conf/machine/include/zynq-base.inc
include conf/machine/include/tune-cortexa9.inc
SOC_FAMILY = "topic-miami"
require conf/machine/include/soc-family.inc

PREFERRED_PROVIDER_virtual/xilinx-sdk = "miami-xilinx-sdk"

UBOOT_MACHINE = "topic_miami_config"

KERNEL_MODULE_AUTOLOAD ?= ""
KERNEL_MODULE_AUTOLOAD += "spi-cadence xilinx-emacps"

# ubiattach /dev/ubi_ctrl -m 5
# From dmesg:
# UBI: attached mtd5 (name "qspi-rootfs", size 27 MiB) to ubi0
# UBI: PEB size: 4096 bytes (4 KiB), LEB size: 3968 bytes
# UBI: min./max. I/O unit sizes: 1/256, sub-page size 1
# UBI: VID header offset: 64 (aligned 64), data offset: 128
# UBI: good PEBs: 7040, bad PEBs: 0, corrupted PEBs: 0
#
# MKUBIFS_ARGS = "-m 1 -e 3968 -c 7040"
# UBINIZE_ARGS = "-m 1 -p 4096"
#
# But using those values does not work and results in messages like:
# "too small LEBs (3968 bytes), min. is 15360 bytes"
# and mkfs.ubifs fails to create images. So we use the 64k version:

MKUBIFS_ARGS = "-m 1 -e 65408 -c 440"
UBINIZE_ARGS = "-m 1 -p 64KiB"
UBI_VOLNAME = "qspi-rootfs"

# This however requires to patch something in the kernel...
