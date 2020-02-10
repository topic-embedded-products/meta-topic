# Kernel patch based approach. This takes a standard kernel and adds patches
# to add support for the Miami modules. Most patches are backports from newer
# kernels, so they're in mainline but not yet in Xilinx' tree. The exceptions
# to that are the ltc3562 driver, HDMI drivers and the devicetrees.
#
# TODO:
#  HDMI in and HDMI audio

require topic-xilinx-kernel-patches.inc

COMPATIBLE_MACHINE_topic-miami = "topic-miami"

# Kernel configuration
TOPICBSPCONFIG ?= ""
TOPICBSPCONFIG_topic-miami = "file://topic-miami-standard.cfg"
TOPICBSPCONFIG_topic-miamimp = "file://topic-miamimp-standard.cfg"
TOPICBSPCONFIG_xdpzu7 = "file://topic-xdpzu7-standard.cfg"

SRC_URI_append = " ${TOPICBSPCONFIG}"

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
