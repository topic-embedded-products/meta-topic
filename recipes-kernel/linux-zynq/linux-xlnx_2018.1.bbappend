# Kernel patch based approach. This takes a standard kernel and adds patches
# to add support for the Miami modules. Most patches are backports from newer
# kernels, so they're in mainline but not yet in Xilinx' tree. The only
# exceptions to that are the ltc3562 driver and the devicetrees.
#
# TODO:
#  HDMI in/out is not available in this build
#

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-xlnx:"

COMPATIBLE_MACHINE_topic-miami = "topic-miami"

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
	file://0011-Add-topic-miami-devicetrees.patch \
	"

SRC_URI_append_topic-miami = "file://defconfig"

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
