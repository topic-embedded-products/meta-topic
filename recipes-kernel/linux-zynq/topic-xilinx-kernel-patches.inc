FILESEXTRAPATHS_prepend := "${THISDIR}/linux-xlnx:"

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
	file://0016-sound-soc-adi-axi-spdif.c-Support-programmable-maste.patch \
	file://0017-zynq-fpga-Only-route-PR-via-PCAP-when-required.patch \
	file://0018-Add-devicetree-for-miamimp-florida-test-with-Dyplo.patch \
	"
