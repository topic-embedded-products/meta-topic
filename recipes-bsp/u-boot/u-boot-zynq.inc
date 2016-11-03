require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"
SRCREV = "5c8a4060c902e45c26a49533a0837e72f7903c31"
PV = "z2016.07.1+git${SRCPV}"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git \
    file://0001-mach-zynq-Kconfig-Make-SYS_VENDOR-configurable.patch \
    file://0002-Add-support-for-the-topic-miami-system-on-modules-an.patch \
    "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
