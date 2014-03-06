require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

SRCREV = "54fee227ef141214141a226efd17ae0516deaf32"

PV = "xilinx-zynq"
PR = "r7"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git;branch=master-next \
	file://0001-Use-bootscript-to-boot-use-fast-XIP-load-address-no-.patch \
	file://0002-Disable-CONFIG_SPL_OS_BOOT.patch \
	file://0003-arch-zynq-hardware.h-Fix-bootmode-mask.patch \
	file://0004-Set-SPL-offset-in-qspi-flash.patch \
	file://0005-Add-zynq-miami-board-support.patch \
	file://0006-Allow-overriding-flash-offsets.patch \
	file://0007-Correct-sector-size-to-256k-for-Spansion-S25FL512S_2.patch \
	"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

