# Provides the Xilinx Zynq common settings

include conf/machine/include/zynq-default-providers.inc

# Define a machine class for common zynq platforms
MACHINE_CLASS = "zynq"

# Add u-boot image to rootfs
EXTRA_IMAGEDEPENDS = "virtual/bootloader sd-bootscript"

# Most boards will have a reference design for the machine. This is usually a
# single package that RDEPENDS on the FPGA image and other needful things.
BOARD_PL_REFERENCE ??= ""

# IMAGE_INSTALL_MACHINE_EXTRAS is used in my-image.bb and will thus by default
# install the reference design package. If you don't want the reference design,
# just set BOARD_PL_REFERENCE="" in your my-image derived image recipe.
IMAGE_INSTALL_MACHINE_EXTRAS = "packagegroup-machine-base ${BOARD_PL_REFERENCE}"

# Specify kernel image type
KERNEL_IMAGETYPE = "uImage"

# If we have an rtc, we'll tell it
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

# Specify machine features
MACHINE_FEATURES = "vfat fpgamgr"

# Specify rootfs image filesystem types
IMAGE_FSTYPES = "tar.gz"

# Specify serial console settings
SERIAL_CONSOLE = "115200 ttyPS0"

# Default FGPA architecture is the machine
FPGA_FAMILY ??= "${MACHINE}"

# XILINX tool locations (for in your site.conf) and input files
# Override them in local.conf to suit your system

# Use the "32" or "64" toolchain
XILINX_TOOL_ARCH ?= "64"
# The base install directory for Vivado
XILINX_VIVADO_VERSION ??= "2019.1"
XILINX_VIVADO_PATH ?= "/opt/Xilinx/Vivado/${XILINX_VIVADO_VERSION}"
# Your LM_LICENSE_FILE is usually removed by Bitbake
XILINX_LM_LICENSE_FILE ?= "${XILINX_VIVADO_PATH}/data/ip/core_licenses/Xilinx.lic"

# WIC image support.
DEVICETREE ??= "zynq-7000.dtb"
WKS_FILE = "sdimage-bootpart.wks"
IMAGE_BOOT_FILES = "boot.bin u-boot.img uImage boot.scr ${DEVICETREE};devicetree.dtb"
do_image_wic[depends] += "mtools-native:do_populate_sysroot dosfstools-native:do_populate_sysroot"
