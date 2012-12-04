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

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-adi-git:"

SRC_URI = "\
	git://github.com/analogdevicesinc/linux.git;branch=${KBRANCH};protocol=git \
	file://spi-xilinx-qps-use-initcall.patch \
	file://0001-Move-virtual-mappings-into-vmalloc-space.patch \
	file://zedboard-qspi-flash-hack.patch \
	file://defconfig \
	file://devicetree.dts \
	"

KERNEL_IMAGETYPE = "uImage"
KERNEL_DEVICETREE = "${WORKDIR}/devicetree.dts"

LINUX_VERSION ?= "3.5"
LINUX_VERSION_EXTENSION ?= "-adi"

SRCREV="a97300abf508f4b5622a1d8b53fb454c57ea94e9"

PR = "r0.14"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(zynq-zc702|zedboard)"
