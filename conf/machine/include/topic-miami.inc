#@TYPE: Machine
#@DESCRIPTION: Machine configuration for the Topic Miami SOM.
#
# Machine names are expected to have this form:
#  topic-miami-<carrier>-<fpga>
# For example, "topic-miami-florida-gen-xc7z015"

# The system-on-module contains flash, CPU+FPGA, RAM and various power supply
# and clock sources.
SOM_FAMILY = "topic-miami"

# All software packages run on the ARM part, hence strip of the FPGA type from
# the machine same in order to create the MACHINE_ARCH.
MACHINE_BOARD := "${@'-'.join(d.getVar('MACHINE', True).split('-')[:-1])}"
MACHINE_ARCH = "${@d.getVar('MACHINE_BOARD', True).replace('-','_')}"
# The kernel also only depends on the board, not the FPGA
KMACHINE = "${MACHINE_BOARD}"

# The FPGA bitstreams will use FPGA_FAMILY as their package-arch, because they
# are compatible with any FPGA of the same type, regardless of the board.
FPGA_FAMILY := "${@d.getVar('MACHINE', True).split('-')[-1:][0]}"
FPGA_FAMILY_ARCH = "${@d.getVar('FPGA_FAMILY', True).replace('-','_')}"

include conf/machine/include/zynq-base.inc

# Use hard-float ABI and allow thumb2 instructions
DEFAULTTUNE ?= "cortexa9thf-neon"
include conf/machine/include/tune-cortexa9.inc

MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"
PACKAGE_EXTRA_ARCHS_append = " ${FPGA_FAMILY_ARCH}"

PREFERRED_PROVIDER_virtual/xilinx-sdk = "miami-xilinx-sdk"

UBOOT_MACHINE = "topic_miami_config"

KERNEL_MODULE_AUTOLOAD ?= ""
KERNEL_MODULE_AUTOLOAD += "macb g_ether spi-cadence"

MACHINE_EXTRA_RRECOMMENDS += "\
	topic-miami-monitor-lib \
	"

# Use QSPI flash with 64k sector size
MKUBIFS_ARGS = "-m 1 -e 65408 -c 440"
UBINIZE_ARGS = "-m 1 -p 64KiB"
UBI_VOLNAME = "qspi-rootfs"

