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
TOPICBSPCONFIG_tdpzu9 = "file://topic-miamimp-standard.cfg file://topic-miamiplusmp-extra.cfg"
TOPICBSPCONFIG_ttpzu9 = "file://topic-miamimp-standard.cfg file://topic-miamiplusmp-extra.cfg file://ttpzu9-extra.cfg file://usb-wired-network-adapters.cfg"
TOPICBSPCONFIG_xdpzu7 = "file://topic-xdpzu7-standard.cfg"

SRC_URI_append = "\
	${TOPICBSPCONFIG} \
	${@bb.utils.contains("MACHINE_FEATURES", "rtc", "file://zynqmp-rtc.cfg", "", d)} \
	"

KERNEL_DEVICETREE_topic-miamilite = "\
	topic-miamilite.dtb \
	"
KERNEL_DEVICETREE_topic-miami = "\
	topic-miami.dtb \
	topic-miami-florida-mio.dtb \
	"
KERNEL_DEVICETREE_topic-miamiplus = "\
	topic-miamiplus.dtb \
	"
KERNEL_DEVICETREE_topic-miamimp = "\
	xilinx/zynqmp-topic-miamimp.dtb \
	xilinx/zynqmp-topic-miamiplusmp.dtb \
	xilinx/zynqmp-topic-miamimp-florida-gen.dtb \
	xilinx/zynqmp-topic-miamimp-florida-test.dtb \
	"
