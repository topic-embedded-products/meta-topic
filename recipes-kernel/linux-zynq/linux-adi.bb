# linux-yocto-custom.bb:
#
#   Provides an example/minimal kernel recipe that uses the linux-yocto
#   and oe-core kernel classes to apply a subset of yocto kernel
#   management to git managed kernel repositories.
#
# Notes:
#
#   kconfig(s): the kernel must be configured with a defconfig, or via
#               configuration fragment(s). Either of these can be added
#               via bbappend.
#   patches: patches can be merged into to the source git tree itself, added
#            using standard bbappend syntax or controlled via .scc feature
#            descriptions (also via bbappends)
#
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition (for kernel v3.4 only):
#            SRC_URI += "file://0001-linux-version-tweak.patch
#   example feature addition (for kernel v3.4 only):
#            SRC_URI += "file://feature.scc"
#
# Warning:
#
#   Building the sample kernel tree (kernel.org) without providing any
#   configuration will result in build or boot errors. This is not a bug
#   it is a required element for creating a valid kernel.
#

KBRANCH = "xcomm_zynq"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Using LZO compression in the kernel requires "lzop"
DEPENDS += "lzop-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-adi-git:"

SRC_URI = "git://github.com/analogdevicesinc/linux.git;branch=${KBRANCH};protocol=git"

SRC_URI += "\
	file://spi-xilinx-qps-use-initcall.patch \
	file://0001-Move-virtual-mappings-into-vmalloc-space.patch \
	file://defconfig \
	file://devicetree.dts \
	"

KERNEL_IMAGETYPE = "uImage"
KERNEL_DEVICETREE = "${WORKDIR}/devicetree.dts"

LINUX_VERSION ?= "3.5"
LINUX_VERSION_EXTENSION ?= "-adi"

SRCREV="ce2f3b7c7d7f530df74e300094c6c7fdeac06239"

PR = "r0.11"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(zynq-zc702|zedboard)"
