require recipes-bsp/u-boot/u-boot.inc

# Copied from OE-core recipes-bsp/u-boot/u-boot-common_2017.01.inc since this
# part was removed from u-boot.inc
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git"
S = "${WORKDIR}/git"

DEPENDS += "dtc-native"

SRCREV = "078231750fcf486e9fca79b4ca6a30424846b8e8"

SRC_URI_append = "\
	file://0001-board-topic-Detect-RAM-size-at-boot.patch \
	file://0002-topic_miami_defconfig-Remove-NFS-and-NET-support.patch \
	file://0003-topic_miami-plus-defconfig-Enable-DFU-RAM-support.patch \
	file://0004-configs-topic_miami.h-Correct-kernel_size-in-default.patch \
	file://0005-zynq-topic-miami.dts-Add-usbotg0-alias-to-make-USB-a.patch \
	file://0006-ARM-zynq-Add-support-for-the-topic-miamilite-system-.patch \
	file://0007-topic-miamiplus-Run-CPU-at-800MHz-for-speedgrade-2.patch \
	file://0008-ARM-zynqmp-Add-support-for-the-topic-miamimp-system-.patch \
	file://0009-mmc-sdhci-Add-card-detect-method.patch \
	file://0010-topic-miamimp-Support-final-silicon.patch \
	file://pmufw.bin.xz \
	"

do_compile_prepend() {
	cp ${WORKDIR}/pmufw.bin ${S}/board/topic/zynqmp/
}

PV = "v2017.01+git${SRCPV}"

SPL_BINARY = "spl/boot.bin"
SPL_SYMLINK = "BOOT.bin"

UBOOT_SUFFIX = "img"
