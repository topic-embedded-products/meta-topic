require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"
SRCREV = "95e11f6eb4bc07bbee12a4217c58146bddac61b2"
PV = "z2016.07+git${SRCPV}"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git \
    file://0001-mach-zynq-Kconfig-Make-SYS_VENDOR-configurable.patch \
    file://0002-Add-support-for-the-topic-miami-system-on-modules-an.patch \
    file://0001-topic-miami-Enable-second-ethernet-controller.patch \
    "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
