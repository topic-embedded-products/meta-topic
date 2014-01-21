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
MACHINE_FEATURES = "apm vfat ethernet"

# Specify rootfs image filesystem types
IMAGE_FSTYPES = "tar.gz ubi"

# Specify serial console settings
SERIAL_CONSOLE = "115200 ttyPS0"

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
