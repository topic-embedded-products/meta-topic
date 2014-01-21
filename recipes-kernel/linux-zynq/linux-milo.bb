DESCRIPTION = "Xilinx Zynq kernel with ADI extensions"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
S = "${WORKDIR}/git"

KBRANCH = "xcomm_zynq_new_pcore_regmap"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

# Using LZO compression in the kernel requires "lzop"
DEPENDS += "lzop-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-milo:"

# If you have a local repository, you can set this variable to point to
# another kernel repo. Or to another kernel entirely.
KERNEL_GIT_REPO ?= "git://github.com/topic-embedded-products/linux"

SRC_URI = "\
	${KERNEL_GIT_REPO};branch=${KBRANCH} \
	file://defconfig \
	"

KERNEL_IMAGETYPE = "uImage"
KERNEL_DEVICETREE = "${WORKDIR}/devicetree.dts"
KERNEL_DEVICETREE_zynq-zc702 = "arch/arm/boot/dts/${MACHINE}-adv7511.dts"
KERNEL_DEVICETREE_zedboard = "arch/arm/boot/dts/zynq-zed-adv7511.dts"
# See http://permalink.gmane.org/gmane.linux.kernel.commits.head/371588
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"
KERNEL_IMAGEDEST = "tmp/boot"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}*"

LINUX_VERSION ?= "3.12"
LINUX_VERSION_EXTENSION ?= "-milo"

SRCREV = "b0f1cdb587674084e412847d0fbe452e38040c3d"

PR = "r0"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(zynq-zc702|zedboard)"

pkg_postinst_kernel-image () {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ] ; then
			if grep -q "ubi0:qspi-rootfs" /proc/mounts
			then
				flashcp -v /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} /dev/mtd3
			else
				if [ -f /media/mmcblk0p1/${KERNEL_IMAGETYPE} ]; then
					cp /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} /media/mmcblk0p1/${KERNEL_IMAGETYPE}
				fi
			fi
			rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
		fi
	fi
	true
}
