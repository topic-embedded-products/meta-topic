require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

SRCREV = "664820b231b129552e963e1a96b45ac7196ccc81"

PV = "xilinx-zynq"
PR = "r7"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git;branch=master-next \
	file://0001-Use-bootscript-to-boot-use-fast-XIP-load-address-no-.patch \
	file://0002-Disable-CONFIG_SPL_OS_BOOT.patch \
	file://0003-Set-SPL-offset-in-qspi-flash.patch \
	file://0004-Add-zynq-miami-board-support.patch \
	file://0005-Allow-overriding-flash-offsets.patch \
	file://0006-Load-FPGA-from-big-u-boot-instead-of-SPL.patch \
	"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

