# The meta-xilinx provided linux-xlnx kernel recipe adds things that we want
# to reverse, which is rather impossible to do. So this recipe just shadows
# the Xilinx one and offers similar functionality as linux-topic did.
SUMMARY = "Xilinx/Topic Kernel"

# We'll share the patches
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-xlnx:"

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

require recipes-kernel/linux/linux-yocto.inc

SRC_URI_append = " \
	file://0001-usb-hub-Cycle-HUB-power-when-initialization-fails.patch \
	file://0002-power-ltc2941-battery-gauge-Disable-continuous-monit.patch \
	file://0003-usb-phy-generic-Use-gpiod_set_value_cansleep.patch \
	file://0004-clk-clk-gpio-Allow-GPIO-to-sleep-in-set-get_parent.patch \
	file://0005-clk-Add-driver-for-the-si544-clock-generator-chip.patch \
	file://0006-clk-si544-Properly-round-requested-frequency-to-near.patch \
	file://0007-of_net-Implement-of_get_nvmem_mac_address-helper.patch \
	file://0008-net-macb-Try-to-retrieve-MAC-addess-from-nvmem-provi.patch \
	file://0009-Add-ltc3562-voltage-regulator-driver.patch \
	file://0010-USB-Gadget-Ethernet-Re-enable-Jumbo-frames.patch \
	file://0011-drm-introduce-helper-for-accessing-EDID-blob-in-drm_.patch \
	file://0012-Add-ADI-AXI-HDMI-module-adi_axi_hdmi.patch \
	file://0013-drm-axi_hdmi_crtc.c-Skip-DMA_INTERLEAVE-check.patch \
	file://0014-drm-axi_hdmi_encoder-Expand-colorspace-range-for-RGB.patch \
	file://0015-Add-topic-miami-devicetrees.patch \
	file://0001-zynq-fpga-Only-route-PR-via-PCAP-when-required.patch \
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

