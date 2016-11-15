# Provides the Xilinx Zynq common settings

include conf/machine/include/zynq-default-providers.inc

# Define a machine class for common zynq platforms
MACHINE_CLASS = "zynq"

# Add u-boot image to rootfs
EXTRA_IMAGEDEPENDS = "virtual/bootloader sd-bootscript"

# Specify kernel image type
KERNEL_IMAGETYPE = "uImage"

# If we have an rtc, we'll tell it
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

# Specify machine features
MACHINE_FEATURES = "vfat"

# Specify rootfs image filesystem types
IMAGE_FSTYPES = "tar.gz"

# Specify serial console settings
SERIAL_CONSOLE = "115200 ttyPS0"

# Default FGPA architecture is the machine
FPGA_FAMILY ??= "${MACHINE}"
FPGA_FAMILY_ARCH ??= "${MACHINE_ARCH}"

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
# The base install directory for XPS and other tools
XILINX_TOOL_PATH ?= "/opt/Xilinx/14.6"
# The base install directory for Vivado
XILINX_VIVADO_PATH ?= "/opt/Xilinx/Vivado/2013.4"
# Your LM_LICENSE_FILE is usually removed by Bitbake
XILINX_LM_LICENSE_FILE ?= "${XILINX_TOOL_PATH}/ISE_DS/ISE/coregen/core_licenses/Xilinx.lic"
# The bootgen program is usually here
ZYNQ_BOOTGEN ?= "${XILINX_TOOL_PATH}/ISE_DS/ISE/bin/lin64/bootgen"
# The logic is usually placed in the rootfs, but if you insist you can put a
# binary bitfile into the BOOT.bin and waste time and space.
ZYNQ_BITFILE ?= ""

# WIC image support.
DEVICETREE ??= "zynq-7000.dtb"
WKS_FILE = "sdimage-bootpart.wks"
IMAGE_BOOT_FILES = "boot.bin u-boot.img uImage ${DEVICETREE};devicetree.dtb"
do_image_wic[depends] += "mtools-native:do_populate_sysroot dosfstools-native:do_populate_sysroot"
