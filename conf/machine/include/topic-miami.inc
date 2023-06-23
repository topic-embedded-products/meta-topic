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

# Prefer to use Yocto's QEMU over Xilinx'
PREFERRED_PROVIDER_qemu-helper-native = "qemu-helper-native"
PREFERRED_PROVIDER_qemu = "qemu"
PREFERRED_PROVIDER_qemu-native = "qemu-native"
PREFERRED_PROVIDER_qemu-system-native = "qemu-system-native"
PREFERRED_PROVIDER_nativesdk-qemu = "nativesdk-qemu"

# Make Xilinx happy, we don't use it...
HDF_MACHINE = "${MACHINE}"

# tune-zynq is adding an unused zImage
KERNEL_IMAGETYPES:remove = "zImage"
# Specify LOADADDR on the commandline to build an uImage
KERNEL_EXTRA_ARGS += "UIMAGE_LOADADDR=0x8000"

MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"

# Workaround for broken check on IMAGE_FEATURES in kernel recipe
FPGA_MNGR_RECONFIG_ENABLE = "1"

# There's no entropy source in the SoM, so use 'haveged' to at least make the
# ssh server start up in reasonable time.
MACHINE_ENTROPY = "haveged-once"
MACHINE_EXTRA_RDEPENDS += "${MACHINE_ENTROPY}"

# u-boot configuration
UBOOT_MACHINE = "topic_miami_config"
UBOOT_SUFFIX = "img"
SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"
HAS_PLATFORM_INIT:append = "\
	topic_miami_config \
	topic_miamilite_config \
	topic_miamiplus_config"
# u-boot-xlnx replaces any devicetree settings with its own, so we have
# to provide the devicetree config explicitly here.
UBOOT_USER_SPECIFIED_DTS = "zynq-topic-miami"

KERNEL_MODULE_AUTOLOAD ?= ""
KERNEL_MODULE_AUTOLOAD += "g_ether spi-cadence"

MACHINE_EXTRA_RRECOMMENDS += "\
	topic-miami-monitor-lib \
	"

# Use QSPI flash with 64k sector size
MKUBIFS_ARGS = "-m 1 -e 65408 -c 496 -x zlib"
UBINIZE_ARGS = "-m 1 -p 64KiB"
UBI_VOLNAME = "qspi-rootfs"

# Xilinx borks this, "fix" that by making it empty again
MACHINE_ESSENTIAL_EXTRA_RDEPENDS = ""

# Out of kernel devicetree
PREFERRED_PROVIDER_virtual/dtb = "device-tree-static"
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "device-tree-static"

DEVICETREE ??= "devicetree/topic-miami.dtb"

# We want to use u-boot SPL
PREFERRED_PROVIDER_virtual/boot-bin = "u-boot-xlnx"

# Xilinx sets WKS_FILE_DEPENDS:append which we'll undo here. We only want boot.bin there anyway...
WKS_FILE_DEPENDS:remove = "xilinx-bootbin virtual/dtb u-boot-xlnx-scr u-boot-xlnx-uenv arm-trusted-firmware"

