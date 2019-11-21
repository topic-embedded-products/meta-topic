FILESEXTRAPATHS_prepend := "${THISDIR}/linux-xlnx:"

SRC_URI_append = " \
	file://0001-Add-ltc3562-voltage-regulator-driver.patch \
	file://0002-drm-introduce-helper-for-accessing-EDID-blob-in-drm_.patch \
	file://0003-Add-ADI-AXI-HDMI-module-adi_axi_hdmi.patch \
	file://0004-drm-axi_hdmi_crtc.c-Skip-DMA_INTERLEAVE-check.patch \
	file://0005-drm-axi_hdmi_encoder-Expand-colorspace-range-for-RGB.patch \
	file://0001-drm-adi_axi_hdmi-Fix-fbdev-interface-not-working.patch \
	file://0006-Add-topic-miami-devicetrees.patch \
	file://0007-sound-soc-adi-axi-spdif.c-Support-programmable-maste.patch \
	file://0008-zynq-fpga-Only-route-PR-via-PCAP-when-required.patch \
	file://0009-iio-chemical-bme680-Report-temperature-in-millidegre.patch \
	file://0010-iio-gyro-bmg160-Use-millidegrees-for-temperature-sca.patch \
	file://0011-iio-gyro-bmg160-Add-support-for-BMI088-chip.patch \
	file://0012-dt-bindings-Add-silabs-si5341.patch \
	file://0013-clk-Add-Si5341-Si5340-driver.patch \
	file://0001-zynqmp-topic-miamimp-fixup-devicetree-for-4.19-kerne.patch \
	file://0001-topic-miami.dtsi-Fix-ltc2990-settings.patch \
	file://0001-gpio-poweroff-Implement-force-pm-power-off-flag.patch \
	"

# Compile overlay-capable devicetrees
EXTRA_OEMAKE_append = " DTC_FLAGS="-p 1024 -@""
