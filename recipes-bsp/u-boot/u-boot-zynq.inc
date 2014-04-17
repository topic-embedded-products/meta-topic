require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

SRCREV = "85525d938cf3afa4ef1b6258d87c628c41c63b9d"

PV = "xilinx-zynq"
PR = "r7"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git;branch=master-next \
	file://0001-Use-bootscript-to-boot-no-ramdisk-smaller-enviroment.patch \
	file://0002-Disable-CONFIG_SPL_OS_BOOT.patch \
	file://0003-Set-SPL-offset-in-qspi-flash.patch \
	file://0004-Add-zynq-miami-board-support.patch \
	file://0005-Allow-overriding-flash-offsets.patch \
	file://0006-Load-FPGA-from-big-u-boot-instead-of-SPL.patch \
	file://0007-zynq-ddrc.c-Do-not-change-the-board-memory-size.patch \
	file://0001-zynq_zc70x.h-Add-missing-CONFIG_SYS_I2C_ZYNQ.patch \
	"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

