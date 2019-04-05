#@TYPE: Machine
#@DESCRIPTION: Machine support for Topic Miami MPSoC.
#
# Machine names are expected to have this form:
#  topic-miamimp-<carrier>-<fpga>

# The system-on-module contains flash, CPU+FPGA, RAM and various power supply
# and clock sources.
SOM_FAMILY = "topic-miamimp"

# All software packages run on the ARM part, hence strip of the FPGA type from
# the machine same in order to create the MACHINE_ARCH.
MACHINE_BOARD := "${@'-'.join(d.getVar('MACHINE', True).split('-')[:-1])}"
# The kernel also only depends on the board, not the FPGA
KMACHINE = "${MACHINE_BOARD}"

# The FPGA bitstreams will use FPGA_FAMILY as their package-arch, because they
# are compatible with any FPGA of the same type, regardless of the board.
FPGA_FAMILY := "${@d.getVar('MACHINE', True).split('-')[-1:][0]}"
FPGA_FAMILY_ARCH = "${@d.getVar('FPGA_FAMILY', True).replace('-','_')}"
# Default is not to have a bitstream, but machines will recommend their own,
# or images may choose to use another application-specific bitstream provider.
MACHINE_FPGA_BITSTREAM ??= ""
# The board adds extra packages, usually kernel modules, that depend on the
# FPGA to function properly. Changing the bitstream usually alters this list
# too.
BOARD_FPGA_PACKAGES ??= ""
MACHINE_EXTRA_RRECOMMENDS = "${MACHINE_FPGA_BITSTREAM} ${BOARD_FPGA_PACKAGES}"

# XILINX tool locations (for in your site.conf) and input files
# Override them in local.conf to suit your system
# Use the "32" or "64" toolchain
XILINX_TOOL_ARCH ?= "64"
# The base install directory for Vivado
XILINX_VIVADO_VERSION ??= "2017.2"
XILINX_VIVADO_PATH ?= "/opt/Xilinx/Vivado/${XILINX_VIVADO_VERSION}"
# Your LM_LICENSE_FILE is usually removed by Bitbake
XILINX_LM_LICENSE_FILE ?= "${XILINX_VIVADO_PATH}/data/ip/core_licenses/Xilinx.lic"

KERNEL_MODULE_AUTOLOAD ?= ""

SOC_VARIANT ?= "eg"

require conf/machine/include/tune-zynqmp.inc
require conf/machine/include/machine-xilinx-overrides.inc
require conf/machine/include/machine-xilinx-default.inc

# The SOM_FAMILY is more specific than the SOC_FAMILY (=zynqmp) in the MACHINEOVERRIDES
SOC_FAMILY .= ":${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}"
PACKAGE_EXTRA_ARCHS_append = " ${FPGA_FAMILY_ARCH}"

MACHINE_FEATURES = "rtc ext2 ext3 vfat usbhost mali fpgamgr"

UBOOT_MACHINE = "topic_miamimp_config"
SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"
UBOOT_SUFFIX = "img"
UBOOT_LOADADDRESS = "0x8000000"
UBOOT_ENTRYPOINT = "0x8000000"
HAS_PLATFORM_INIT_append = " topic_miamimp_config"

SERIAL_CONSOLE = "115200 ttyPS0"

KERNEL_DEVICETREE = "xilinx/zynqmp-topic-miamimp.dtb"

PREFERRED_PROVIDER_virtual/kernel = "linux-xlnx"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-xlnx"

EXTRA_IMAGEDEPENDS += " \
		virtual/bootloader \
		arm-trusted-firmware \
		"

# fpga-image-load / fpga-firmware-load does not work on MPSoC
BAD_RECOMMENDATIONS += "fpga-image-load \
			fpga-firmware-load \
			"

# Use QSPI flash with 128k sector size, 128MB total size
MKUBIFS_ARGS = "-m 1 -e 130944 -c 1008"
UBINIZE_ARGS = "-m 1 -p 128KiB"
UBI_VOLNAME = "qspi-rootfs"

# WIC image support.
DEVICETREE ??= "zynqmp-topic-miamimp.dtb"
WKS_FILE = "sdimage-bootpart.wks"
IMAGE_BOOT_FILES = "boot.bin u-boot.bin Image atf-uboot.ub ${DEVICETREE};system.dtb"
do_image_wic[depends] += "mtools-native:do_populate_sysroot dosfstools-native:do_populate_sysroot virtual/bootloader:do_deploy"
