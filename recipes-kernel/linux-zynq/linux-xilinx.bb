# The meta-xilinx provided linux-xlnx kernel recipe adds things that we want
# to reverse, which is rather impossible to do. So this recipe just shadows
# the Xilinx one and offers similar functionality as linux-topic did.
SUMMARY = "Xilinx/Topic Kernel"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION = "4.19"
XILINX_RELEASE_VERSION = "v2019.1"
LINUX_VERSION_EXTENSION = "-${XILINX_RELEASE_VERSION}"
KBRANCH = "xlnx_rebase_v4.19"
SRCREV = "9811303824b66a8db9a8ec61b570879336a9fde5"

PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "topic-miami"

KERNELURI ?= "git://github.com/Xilinx/linux-xlnx.git;protocol=https"
SRC_URI = "${KERNELURI};branch=${KBRANCH}"
SRCREV_machine ?= "${SRCREV}"

require topic-xilinx-kernel-patches.inc

require recipes-kernel/linux/linux-yocto.inc

SRC_URI_append = " \
	file://defconfig \
	"

KERNEL_DEVICETREE ?= ""
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

