#@TYPE: Machine
#@DESCRIPTION: Machine configuration for the Zynq-Miami SOM.

include conf/machine/include/zynq-base.inc
include conf/machine/include/tune-cortexa9.inc
SOC_FAMILY = "zynq-miami"
require conf/machine/include/soc-family.inc

PREFERRED_PROVIDER_virtual/xilinx-sdk = "miami-xilinx-sdk"

UBOOT_MACHINE = "zynq_miami_config"

# ubiattach /dev/ubi_ctrl -m 5
# From dmesg:
# UBI: attached mtd5 (name "qspi-rootfs", size 58 MiB) to ubi0
# UBI: PEB size: 262144 bytes (256 KiB), LEB size: 262016 bytes
# UBI: min./max. I/O unit sizes: 1/256, sub-page size 1
# UBI: VID header offset: 64 (aligned 64), data offset: 128
# UBI: good PEBs: 234, bad PEBs: 0, corrupted PEBs: 0

MKUBIFS_ARGS = "-m 1 -e 262016 -c 234"
UBINIZE_ARGS = "-m 1 -p 256KiB"
UBI_VOLNAME = "qspi-rootfs"
