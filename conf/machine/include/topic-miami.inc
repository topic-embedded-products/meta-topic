#@TYPE: Machine
#@DESCRIPTION: Machine configuration for the Topic Miami SOM.
#
# Machine names are expected to have this form:
#  topic-miami-<carrier>-<fpga>
# For example, "topic-miami-florida-med-xc7z015"

# The system-on-module contains flash, CPU+FPGA, RAM and various power supply
# and clock sources.
SOM_FAMILY = "topic-miami"

# All software packages run on the ARM part, hence strip of the FPGA type from
# the machine same in order to create the MACHINE_ARCH.
MACHINE_BOARD = "${@'-'.join(d.getVar('MACHINE', True).split('-')[:-1])}"
MACHINE_ARCH = "${@d.getVar('MACHINE_BOARD', True).replace('-','_')}"

# The FPGA bitstreams will use FPGA_FAMILY as their package-arch, because they
# are compatible with any FPGA of the same type, regardless of the board.
FPGA_FAMILY = "${@d.getVar('MACHINE', True).split('-')[-1:][0]}"
FPGA_FAMILY_ARCH = "${@d.getVar('FPGA_FAMILY', True).replace('-','_')}"

include conf/machine/include/zynq-base.inc
include conf/machine/include/tune-cortexa9.inc

MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"
PACKAGE_EXTRA_ARCHS_append = " ${FPGA_FAMILY_ARCH}"

PREFERRED_PROVIDER_virtual/xilinx-sdk = "miami-xilinx-sdk"

UBOOT_MACHINE = "topic_miami_config"

KERNEL_MODULE_AUTOLOAD ?= ""
KERNEL_MODULE_AUTOLOAD += "xilinx_emacps rsi_sdio g_ether"

MACHINE_EXTRA_RRECOMMENDS = "\
	kernel-module-ltc2990 \
	"
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
