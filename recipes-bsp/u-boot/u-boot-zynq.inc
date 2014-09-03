require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"
SRCREV = "f30aefb347f08a7a34d1c02be98af12794b67fd6"
PV = "z2014.7+git${SRCPV}"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git;branch=master-next \
	file://0001-Use-bootscript-to-boot-no-ramdisk-smaller-enviroment.patch \
	file://0002-Disable-CONFIG_SPL_OS_BOOT.patch \
	file://0003-Set-SPL-offset-in-qspi-flash.patch \
	file://0004-Allow-overriding-flash-offsets.patch \
	file://0005-Load-FPGA-from-big-u-boot-instead-of-SPL.patch \
	file://0006-zynq_zc70x.h-Add-missing-CONFIG_SYS_I2C_ZYNQ.patch \
	file://0007-configs-zynq-common-Don-t-add-parameters-to-sf-probe.patch \
	file://0008-Add-topic-miami-board-support.patch \
	"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

