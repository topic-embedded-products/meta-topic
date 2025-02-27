# Kernel patch based approach. This takes a standard kernel and adds patches
# to add support for the Miami modules. Most patches are backports from newer
# kernels, so they're in mainline but not yet in Xilinx' tree. The exceptions
# to that are the ltc3562 driver, HDMI drivers and the devicetrees.

require topic-xilinx-kernel-patches.inc

COMPATIBLE_MACHINE:topic-miami = "topic-miami"

# Kernel configuration
TOPICBSPCONFIG ?= ""
TOPICBSPCONFIG:topic-miami = "file://topic-miami-standard.cfg file://usb-network-cdc.cfg"
TOPICBSPCONFIG:topic-miamimp = "file://topic-miamimp-standard.cfg file://tdk-peta.cfg file://usb-network-cdc.cfg"
TOPICBSPCONFIG:tspzu = "file://topic-miamimp-standard.cfg file://topic-miamiplusmp-extra.cfg file://tdpzu-extra.cfg file://usb-wired-network-adapters.cfg"
TOPICBSPCONFIG:tepzu = "file://topic-miamimp-standard.cfg file://topic-miamiplusmp-extra.cfg file://usb-wired-network-adapters.cfg file://tepzu.cfg file://wifi-iwl.cfg"
TOPICBSPCONFIG:ttpzu9 = "file://topic-miamimp-standard.cfg file://topic-miamiplusmp-extra.cfg file://ttpzu9-extra.cfg file://usb-wired-network-adapters.cfg"
TOPICBSPCONFIG:xdpzu7 = "file://topic-xdpzu7-standard.cfg file://usb-wired-network-adapters.cfg"

SRC_URI:append = "\
	${TOPICBSPCONFIG} \
	${@bb.utils.contains("MACHINE_FEATURES", "rtc", "file://zynqmp-rtc.cfg", "", d)} \
	"
