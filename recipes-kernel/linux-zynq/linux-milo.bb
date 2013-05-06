DESCRIPTION = "Xilinx Zynq kernel with ADI extensions"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
S = "${WORKDIR}/git"

KBRANCH = "xcomm_zynq"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

# Using LZO compression in the kernel requires "lzop"
DEPENDS += "lzop-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-milo:"

# If you have a local repository, you can set this variable to point to
# another kernel repo. Or to another kernel entirely.
KERNEL_GIT_REPO ?= "git://github.com/milosoftware/linux.git;protocol=git"

SRC_URI = "\
	${KERNEL_GIT_REPO};branch=${KBRANCH} \
	file://zedboard-qspi-flash-hack.patch \
	file://defconfig \
	file://devicetree.dts \
	"

KERNEL_IMAGETYPE = "uImage"
KERNEL_DEVICETREE = "${WORKDIR}/devicetree.dts"

LINUX_VERSION ?= "3.6"
LINUX_VERSION_EXTENSION ?= "-milo"

SRCREV="ba8ffcfe2a16f44816b607bfc6a8fc8d082d151d"

PR = "r0"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(zynq-zc702|zedboard)"
