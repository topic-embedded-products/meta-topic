require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

SRCREV = "6742f65365086ded52fa9726c9120de8a6ad541b"

PV = "xilinx-zynq"
PR = "r7"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git;branch=master-next \
	file://0001-Use-bootscript-to-boot-use-fast-XIP-load-address-no-.patch \
	file://0001-spl_mmc.c-Display-correct-message-when-bitstream-fai.patch \
	file://0002-Disable-CONFIG_SPL_OS_BOOT.patch \
	"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

