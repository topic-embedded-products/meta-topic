#@TYPE: Machine
#@DESCRIPTION: Machine configuration for the Topic Miami SOM.
#
# Machine config is expected to set MACHINE_BOARD and FPGA_FAMILY

# The system-on-module contains flash, CPU+FPGA, RAM and various power supply
# and clock sources.
SOM_FAMILY = "topic-miami"

# The kernel only depends on the board, not the FPGA
KMACHINE = "${MACHINE_BOARD}"

include conf/machine/include/zynq-base.inc
require conf/machine/include/soc-zynq.inc
require conf/machine/include/machine-xilinx-default.inc

# tune-zynq is adding an unused zImage
KERNEL_IMAGETYPES_remove = "zImage"

MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"

# Workaround for broken check on IMAGE_FEATURES in kernel recipe
FPGA_MNGR_RECONFIG_ENABLE = "1"

# u-boot configuration
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-xlnx"
UBOOT_MACHINE = "topic_miami_config"
UBOOT_SUFFIX = "img"
SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"
HAS_PLATFORM_INIT_append = "\
	topic_miami_config \
	topic_miamilite_config \
	topic_miamiplus_config"

KERNEL_MODULE_AUTOLOAD ?= ""
KERNEL_MODULE_AUTOLOAD += "g_ether spi-cadence"

MACHINE_EXTRA_RRECOMMENDS += "\
	topic-miami-monitor-lib \
	"
	
EXTRA_IMAGEDEPENDS += " \
		u-boot-zynq-uenv \
		virtual/boot-bin \
		virtual/bootloader \
		u-boot-zynq-scr \
		"

# Use QSPI flash with 64k sector size
MKUBIFS_ARGS = "-m 1 -e 65408 -c 496 -x zlib"
UBINIZE_ARGS = "-m 1 -p 64KiB"
UBI_VOLNAME = "qspi-rootfs"

# Out of kernel devicetree
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "device-tree"

DEVICETREE ??= "topic-miami.dtb"

DEVICETREELINKS = "\
	system.dtb  devicetree/${DEVICETREE} \n \
	"
