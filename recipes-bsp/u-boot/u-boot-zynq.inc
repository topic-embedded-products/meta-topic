require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"
SRCREV = "a38e1e365d2cb11d1886a460af0d312a08d14ce3"
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
	file://0009-zynq_qspi.c-Set-correct-frequency-regardless-of-hard.patch \
	file://0010-spi_spl_load.c-Respect-CONFIG_SF_DEFAULT_MODE-settin.patch \
	file://0001-topic_miami-Do-not-load-environment.patch \
	"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

