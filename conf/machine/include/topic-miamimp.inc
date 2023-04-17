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

# There's no entropy source in the SoM, so use 'haveged' to at least make the
# ssh server start up in reasonable time.
MACHINE_ENTROPY = "haveged-once"
MACHINE_EXTRA_RDEPENDS += "${MACHINE_ENTROPY}"

KERNEL_MODULE_AUTOLOAD ?= ""

# Most topic modules carry the "eg" variant (quad-core CPU with MALI GPU)
SOC_VARIANT ?= "eg"

# The SOM_FAMILY is more specific than the SOC_FAMILY (=zynqmp) in the MACHINEOVERRIDES
MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"

# The zynqmp is a CortexA53, no need to be binary compatible with other CPUs
# This is a strange place to do this. Note that you'll have to remove this when
# attempting to build the PMU firmware.
DEFAULTTUNE = "cortexa53"

require conf/machine/zynqmp-generic.conf
# Undo what Xilinx did, there's nothing that we want, so make it empty
MACHINE_ESSENTIAL_EXTRA_RDEPENDS = ""

# Remove the unneeded boot config/script, not needed for topic boards, but they
# get added unconditionally by zynqmp-generic.conf.
EXTRA_IMAGEDEPENDS:remove = "u-boot-xlnx-uenv u-boot-xlnx-scr"
# We have our own bootscript
EXTRA_IMAGEDEPENDS += "bootscript"

# Get rid of QEMU dependency, work around build failures
IMAGE_CLASSES:remove = "image-types-xilinx-qemu"
IMAGE_FSTYPES:remove = "wic.qemu-sd"
EXTRA_IMAGEDEPENDS:remove = "qemu-system-native qemu-helper-native:do_addto_recipe_sysroot"
PREFERRED_PROVIDER_qemu-helper-native ?= "qemu-helper-native"
PREFERRED_PROVIDER_qemu = "qemu"
PREFERRED_PROVIDER_qemu-native = "qemu-native"
PREFERRED_PROVIDER_qemu-system-native = "qemu-system-native"
PREFERRED_PROVIDER_nativesdk-qemu = "nativesdk-qemu"

# Out of kernel devicetree
PREFERRED_PROVIDER_virtual/dtb = "device-tree-static"
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "device-tree-static"

# The RTC only works if a battery is connected, which most boards do not have
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"
# Sanitize the MACHINE_FEATURES (otherwise we get wifi, bluetooth and more)
MACHINE_FEATURES = "ext2 ext3 vfat usbhost fpgamgr"

SERIAL_CONSOLE = "115200 ttyPS0"
# We only want to run getty on the console.
SERIAL_CONSOLES = "115200;ttyPS0"

# Use QSPI flash with 128k sector size, 128MB total size
MKUBIFS_ARGS = "-m 1 -e 130944 -c 1012 -x zlib"
UBINIZE_ARGS = "-m 1 -p 128KiB"
UBI_VOLNAME = "qspi-rootfs"

# WIC image support.
DEVICETREE ??= "devicetree/zynqmp-topic-miamimp.dtb"
WKS_FILE = "sdimage-swu.wks"
# Xilinx sets WKS_FILES instead of WKS_FILE, so we're forced to do that here too
WKS_FILES = "${WKS_FILE}"
IMAGE_BOOT_FILES = "boot.bin"
IMAGE_OVERHEAD_FACTOR="1.1"

# Xilinx sets WKS_FILE_DEPENDS:append which we'll undo here. We only want boot.bin there anyway...
WKS_FILE_DEPENDS:remove = "virtual/dtb u-boot-xlnx-uenv u-boot-xlnx-scr arm-trusted-firmware"

# HACK (xilins prepends file:// and appends .elf
# FSBL_FILE = "${HOME}/projects/topic-platform-langdale/fsbl-${MACHINE}"

# Do not add device-tree or bitstream to boot.bin, they're not needed
BIF_DEVICETREE_ATTR = ""
BIF_BITSTREAM_ATTR = ""

# TLD uses this revision
TOPIC_XSA_DESIGN_VERSION ?= "179+bcff7f6"
TOPIC_XSA_DESIGN_NAME ?= "fpga-hardware-${HDF_MACHINE}-reference-${TOPIC_XSA_DESIGN_VERSION}.xsa"

# Setup things for external-hdf.bb
# Note: Update the checksums in "external-hdf.bbappend" when the reference designs change
HDF_MACHINE = "${MACHINE}"
HDF_BASE = "http://"
HDF_PATH = "fpga-server.local/downloads/fpga/fpga-board-utils/${TOPIC_XSA_DESIGN_NAME}.xz;name=${HDF_MACHINE}"
HDF_NAME = "${S}/${TOPIC_XSA_DESIGN_NAME}"
