#@TYPE: Machine
#@DESCRIPTION: Machine support for Topic Miami MPSoC.
#
# Machine config is expected to set MACHINE_BOARD and FPGA_FAMILY

# The system-on-module contains flash, CPU+FPGA, RAM and various power supply
# and clock sources.
SOM_FAMILY = "topic-miamimp"

# The kernel also only depends on the board, not the FPGA
KMACHINE = "${MACHINE_BOARD}"

# Most boards will have a reference design for the machine. This is usually a
# single package that RDEPENDS on the FPGA image and other needful things.
BOARD_PL_REFERENCE ??= ""

# IMAGE_INSTALL_MACHINE_EXTRAS is used in my-image.bb and will thus by default
# install the reference design package. If you don't want the reference design,
# just set BOARD_PL_REFERENCE="" in your my-image derived image recipe.
IMAGE_INSTALL_MACHINE_EXTRAS = "packagegroup-machine-base ${BOARD_PL_REFERENCE}"

# XILINX tool locations (for in your site.conf) and input files
# Override them in local.conf to suit your system
# Use the "32" or "64" toolchain
XILINX_TOOL_ARCH ?= "64"
# The base install directory for Vivado
XILINX_VIVADO_VERSION ??= "2019.1"
XILINX_VIVADO_PATH ?= "/opt/Xilinx/Vivado/${XILINX_VIVADO_VERSION}"
# Your LM_LICENSE_FILE is usually removed by Bitbake
XILINX_LM_LICENSE_FILE ?= "${XILINX_VIVADO_PATH}/data/ip/core_licenses/Xilinx.lic"

KERNEL_MODULE_AUTOLOAD ?= ""

# Default a platform without GPU/VCU because the MALI GPU support is broken in
# Xilinx' warrior branch.
# SOC_VARIANT = "eg" causes X11 programs to depend on libmali
SOC_VARIANT ?= ""

# The SOM_FAMILY is more specific than the SOC_FAMILY (=zynqmp) in the MACHINEOVERRIDES
MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"

require conf/machine/include/tune-zynqmp.inc
require conf/machine/include/machine-xilinx-overrides.inc
require conf/machine/include/machine-xilinx-default.inc

# The RTC only works if a battery is connected, which most boards do not have
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"
MACHINE_FEATURES = "ext2 ext3 vfat usbhost mali fpgamgr"

UBOOT_MACHINE = "topic_miamimp_config"
SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"
UBOOT_SUFFIX = "itb"
# Since Xilinx defines UBOOT_SUFFIX_zynqmp somewhere, fix that here
UBOOT_SUFFIX_zynqmp = "itb"
UBOOT_LOADADDRESS = "0x8000000"
UBOOT_ENTRYPOINT = "0x8000000"
HAS_PLATFORM_INIT_append = "\
    topic_miamimp_config \
    topic_miamiplusmp_config \
    topic_miamimp_xilinx_xdp_config\
    "
# We provide the firmware through the URI, u-boot-xlnx overrides CONFIG_PMUFW_INIT_FILE
PMU_FIRMWARE_DEPLOY_DIR = "${WORKDIR}"
PMU_FIRMWARE_IMAGE_NAME = "pmu-firmware-zynqmp-pmu"

SERIAL_CONSOLE = "115200 ttyPS0"

KERNEL_DEVICETREE = "xilinx/zynqmp-topic-miamimp.dtb"

PREFERRED_PROVIDER_virtual/kernel = "linux-xlnx"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-xlnx"

# Workaround for broken check on IMAGE_FEATURES in kernel recipe
FPGA_MNGR_RECONFIG_ENABLE = "1"

EXTRA_IMAGEDEPENDS += " \
		virtual/bootloader \
		arm-trusted-firmware \
		"

# Use QSPI flash with 128k sector size, 128MB total size
MKUBIFS_ARGS = "-m 1 -e 130944 -c 1008"
UBINIZE_ARGS = "-m 1 -p 128KiB"
UBI_VOLNAME = "qspi-rootfs"

# WIC image support.
DEVICETREE ??= "zynqmp-topic-miamimp.dtb"
WKS_FILE = "sdimage-bootpart.wks"
IMAGE_BOOT_FILES = "boot.bin u-boot.itb boot.scr Image ${DEVICETREE};system.dtb"
do_image_wic[depends] += "mtools-native:do_populate_sysroot dosfstools-native:do_populate_sysroot virtual/bootloader:do_deploy"
