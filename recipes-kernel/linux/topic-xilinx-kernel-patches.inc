FILESEXTRAPATHS_prepend := "${THISDIR}/linux-xlnx:"

ADIHDMIPATCHES = "\
	file://0001-dmaengine-virt-dma-store-result-on-dma-descriptor.patch \
	file://0002-Add-include-linux-fpga-adi-axi-common.h-header.patch \
	file://0003-dma-axi-dmac-Update-to-adi-xcomm_zynq-version.patch \
	file://0004-media-platform-Add-ADI-drivers.patch \
	file://0005-media-platform-axi-hdmi-rx-Announce-GPLv2-module-lic.patch \
	file://0006-sound-soc-dai.h-Add-SPDIF-format-constants.patch \
	file://0007-ASoC-snd_soc_of_parse_daifmt-Handle-SND_SOC_DAIFMT_S.patch \
	file://0008-drm-bridge-adv7511-Enable-SPDIF-support.patch \
	"

SRC_URI_append = "\
	${ADIHDMIPATCHES}\
	file://0001-Add-ltc3562-voltage-regulator-driver.patch \
	file://0002-drm-introduce-helper-for-accessing-EDID-blob-in-drm_.patch \
	file://0003-Add-ADI-AXI-HDMI-module-adi_axi_hdmi.patch \
	file://0004-drm-axi_hdmi_crtc.c-Skip-DMA_INTERLEAVE-check.patch \
	file://0005-drm-axi_hdmi_encoder-Expand-colorspace-range-for-RGB.patch \
	file://0006-sound-soc-adi-axi-spdif.c-Support-programmable-maste.patch \
	file://0007-zynq-fpga-Only-route-PR-via-PCAP-when-required.patch \
	file://0008-iio-chemical-bme680-Report-temperature-in-millidegre.patch \
	file://0009-iio-gyro-bmg160-Use-millidegrees-for-temperature-sca.patch \
	file://0010-iio-gyro-bmg160-Add-support-for-BMI088-chip.patch \
	file://0011-iio-chemical-bme680-Fix-SPI-read-interface.patch \
	file://0012-dt-bindings-Add-silabs-si5341.patch \
	file://0013-clk-Add-Si5341-Si5340-driver.patch \
	file://0014-drm-adi_axi_hdmi-Fix-fbdev-interface-not-working.patch \
	file://0015-gpio-poweroff-Implement-force-pm-power-off-flag.patch \
	file://0016-Add-topic-miami-devicetrees.patch \
	file://0017-spi-nor-Fix-spi-nor-read-in-edge-cases.patch \
	file://0018-zynqmp-topic-miamimp-Enable-multi-die-support-for-QS.patch \
	"

# Compile overlay-capable devicetrees
EXTRA_OEMAKE_append = " DTC_FLAGS="-p 1024 -@""
