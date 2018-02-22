DESCRIPTION = "Mainline Linux Zynq kernel for Topic boards"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRCREV = "29dcea88779c856c7dc92040a0c01233263101d4"
LINUX_VERSION = "4.17"

inherit kernel
require recipes-kernel/linux/linux-dtb.inc

S = "${WORKDIR}/git"

# Using LZO compression in the kernel requires "lzop"
DEPENDS += "lzop-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-mainline:"

EXTRA_PATCHES = "\
	file://0001-regulator-Add-ltc3562-voltage-regulator-driver.patch \
	file://0001-Add-topic-miami-devicetrees.patch \
	"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git \
	file://defconfig \
	${EXTRA_PATCHES} \
	"

KERNEL_IMAGETYPE = "uImage"
KERNEL_IMAGETYPE_topic-miamimp = "Image"
KERNEL_DEVICETREE = "${MACHINE}.dtb"
KERNEL_DEVICETREE_topic-miami = "\
	topic-miami-dyplo.dtb \
	topic-miami-dyplo-acp.dtb \
	topic-miami-florida-gen.dtb \
	topic-miami-florida-gen-pt.dtb \
	topic-miami-florida-gen-nand.dtb \
	topic-miami-florida-gen-amp.dtb \
	topic-miami-florida-mio.dtb \
	topic-miami-florida-mio-dyplo.dtb \
	topic-miami-florida-mio-nand-dyplo.dtb \
	topic-miami-florida-pci.dtb \
	topic-miami-florida-pci-pt.dtb \
	topic-miami-florida-test.dtb \
	topic-miamilite-florida-test.dtb \
	topic-miamiplus-florida-test.dtb \
	"
KERNEL_DEVICETREE_topic-miamimp = "\
	xilinx/zynqmp-topic-miamimp.dtb \
	xilinx/zynqmp-topic-miamimp-florida-gen.dtb \
	xilinx/zynqmp-topic-miamimp-florida-test.dtb \
	"

# See http://permalink.gmane.org/gmane.linux.kernel.commits.head/371588
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"
KERNEL_IMAGEDEST = "tmp/boot"

KERNEL_EXTRA_ARGS_topic-miamimp = ""

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}*"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "topic-miami"

KERNEL_FLASH_DEVICE = "/dev/mtd4"

pkg_postinst_kernel-image () {
	if [ "x$D" == "x" ]; then
		if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ] ; then
			if grep -q "ubi0:qspi-rootfs" /proc/mounts
			then
				flashcp -v /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ${KERNEL_FLASH_DEVICE}
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

# Workaround: Enforce using "our" defconfig and not some stale version from a
# previous build. Pending real fix in OE-core.
do_configure_prepend() {
	cp "${WORKDIR}/defconfig" "${B}/.config"
}
