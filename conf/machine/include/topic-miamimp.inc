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

KERNEL_MODULE_AUTOLOAD ?= ""

# Default a platform without GPU/VCU because the MALI GPU support is broken in
# Xilinx' warrior branch.
# SOC_VARIANT = "eg" causes X11 programs to depend on libmali
SOC_VARIANT ?= "cg"

# The SOM_FAMILY is more specific than the SOC_FAMILY (=zynqmp) in the MACHINEOVERRIDES
MACHINEOVERRIDES =. "${SOM_FAMILY}:${MACHINE_BOARD}:${FPGA_FAMILY}:"

# The zynqmp is a CortexA53, no need to be binary compatible with other CPUs
DEFAULTTUNE = "cortexa53"

require conf/machine/zynqmp-generic.conf

# Add board compatibility override
MACHINEOVERRIDES .= ":${MACHINE}"

# The RTC only works if a battery is connected, which most boards do not have
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"
MACHINE_FEATURES = "ext2 ext3 vfat usbhost mali fpgamgr"

UBOOT_MACHINE = "topic_miamimp_config"
SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"
UBOOT_SUFFIX = "itb"
# Since Xilinx defines UBOOT_SUFFIX:zynqmp somewhere, fix that here
UBOOT_SUFFIX:zynqmp = "itb"
UBOOT_LOADADDRESS = "0x8000000"
UBOOT_ENTRYPOINT = "0x8000000"
HAS_PLATFORM_INIT:append = "\
    topic_miamimp_config \
    topic_miamiplusmp_config \
    topic_miamimp_xilinx_xdp_config\
    "

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
# IMAGE_BOOT_FILES = "boot.bin u-boot.itb"
IMAGE_OVERHEAD_FACTOR="1.1"
