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

# Ship kernel modules
MACHINE_EXTRA_RRECOMMENDS = "kernel-modules"

# Specify rootfs image filesystem types
IMAGE_FSTYPES = "tar.gz ubi"

# Specify serial console settings
SERIAL_CONSOLE = "115200 ttyPS0"

# XILINX tool locations (for in your site.conf) and input files
ZYNQ_FSBL ?= "${HOME}/workspace/zynq_fsbl_0/Release/zynq_fsbl_0.elf"
# By default, no bitfile.
ZYNQ_BITFILE ?= ""
# Assume "bootgen" is in the PATH (it usually isn't)
ZYNQ_BOOTGEN ?= "bootgen"
