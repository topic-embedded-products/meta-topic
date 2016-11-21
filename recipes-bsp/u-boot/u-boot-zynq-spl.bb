# U-boot SPL boots without FSBL.
FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

#  "v2016.11"
SRCREV = "c2cbd164ea5b5f564fcf03447c7bf9ec4a9f5699"

SRC_URI += "file://0001-board-topic-Detect-RAM-size-at-boot.patch"

PV = "v2016.11+git${SRCPV}"

SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"

UBOOT_SUFFIX = "img"
