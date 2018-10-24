# The meta-xilinx provided linux-xlnx kernel recipe adds things that we want
# to reverse, which is rather impossible to do. So this recipe just shadows
# the Xilinx one and offers similar functionality as linux-topic did.
SUMMARY = "Xilinx/Topic Kernel"

LINUX_VERSION = "4.14"
LINUX_VERSION_EXTENSION ?= ""
KBRANCH = "xlnx_rebase_v4.14"
PV = "${LINUX_VERSION}${LINUX_VERSION_EXTENSION}+git${SRCPV}"

# There's quite a few bugs in the Xilinx kernel '2018.1' state which were fixed
# later on in the branch, and they were causing nasty crashes. Grab the newer
# version to get the fixes.
SRCREV = "ad4cd988ba86ab0fb306d57f244b7eaa6cce79a4"

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

