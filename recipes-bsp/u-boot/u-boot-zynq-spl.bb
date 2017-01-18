LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

#  "v2017.01"
SRCREV = "a705ebc81b7f91bbd0ef7c634284208342901149"

SRC_URI += "\
	file://0001-board-topic-Detect-RAM-size-at-boot.patch \
	file://0002-topic_miami_defconfig-Remove-NFS-and-NET-support.patch \
	file://0003-topic_miami-plus-defconfig-Enable-DFU-RAM-support.patch \
	file://0004-configs-topic_miami.h-Correct-kernel_size-in-default.patch \
	file://0001-ARM-zynq-Setup-modeboot-variable-based-on-boot-mode.patch \
	"

PV = "v2017.01+git${SRCPV}"

SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"

UBOOT_SUFFIX = "img"
