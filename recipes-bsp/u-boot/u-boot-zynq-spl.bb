require recipes-bsp/u-boot/u-boot.inc

# Copied from OE-core recipes-bsp/u-boot/u-boot-common_2017.01.inc since this
# part was removed from u-boot.inc
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
SRC_URI = "git://git.denx.de/u-boot.git"
S = "${WORKDIR}/git"

DEPENDS += "dtc-native"

#  "v2017.01"
SRCREV = "a705ebc81b7f91bbd0ef7c634284208342901149"

# The "plus" and "lite" boards have dual-QSPI which isn't supported in mainline
# (yet). So fetch u-boot-xlnx instead.
SRC_URI_topic-miamiplus = "git://github.com/Xilinx/u-boot-xlnx.git"
SRCREV_topic-miamiplus = "c623f127e4c82885ab0d936616d628d37fb081c7"
SRC_URI_topic-miamilite = "git://github.com/Xilinx/u-boot-xlnx.git"
SRCREV_topic-miamilite = "c623f127e4c82885ab0d936616d628d37fb081c7"

# Patches for all boards. Use _append so it applies to everything.
SRC_URI_append = "\
	file://0001-board-topic-Detect-RAM-size-at-boot.patch \
	file://0002-topic_miami_defconfig-Remove-NFS-and-NET-support.patch \
	file://0003-topic_miami-plus-defconfig-Enable-DFU-RAM-support.patch \
	file://0004-configs-topic_miami.h-Correct-kernel_size-in-default.patch \
	file://0005-zynq-topic-miami.dts-Add-usbotg0-alias-to-make-USB-a.patch \
	file://0001-ARM-zynq-Add-support-for-the-topic-miamilite-system-.patch \
	file://0001-topic-miamiplus-Run-CPU-at-800MHz-for-speedgrade-2.patch \
	"
# Patch only for mainline u-boot. Using += makes this only apply to SRC_URI without
# overrides.
SRC_URI += "\
	file://0001-ARM-zynq-Setup-modeboot-variable-based-on-boot-mode.patch \
	"

PV = "v2017.01+git${SRCPV}"

SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"

UBOOT_SUFFIX = "img"
