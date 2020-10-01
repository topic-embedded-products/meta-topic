FILESEXTRAPATHS_prepend := "${THISDIR}/linux-xlnx:"

# Brings the Xilinx kernel up to date with our fork at:
#   https://github.com/topic-embedded-products/linux
# branch: topic-miami-xlnx-rebase-4.19-x2019.2
# To start development, you'd type this:
#   git clone -b topic-miami-xlnx-rebase-4.19-x2019.2 git@github.com:topic-embedded-products/linux.git
#
# Patches created using this command:
#   git format-patch -o /tmp/ xlnx_rebase_v4.19_2019.2..HEAD


# For the ZynqMP, revert the commits that break the MMC high-speed modes. 
EXTRAPATCHES = ""
EXTRAPATCHES_zynqmp = "\
	file://0001-Revert-mmc-host-sdhci-of-arasan-Use-SDHCI-framework-.patch \
	file://0002-Revert-mmc-sdhci-of-arasan-Update-Tap-Delays-for-Ver.patch \
	file://0003-Revert-sdhci-arasan-Add-support-for-Versal-Tap-Delay.patch \
	"

SRC_URI_append = "\
	file://enable-kexec-support.cfg \
	${EXTRAPATCHES} \
	file://0001-iio-accel-Add-support-for-the-Bosch-Sensortec-BMI088.patch \
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
	file://0012-dt-bindings-clock-Add-silabs-si5341.patch \
	file://0013-clk-Add-Si5341-Si5340-driver.patch \
	file://0014-clk-Si5341-Si5340-remove-redundant-assignment-to-n_d.patch \
	file://0015-clk-clk-si5341-Support-multiple-input-ports.patch \
	file://0016-drm-adi_axi_hdmi-Fix-fbdev-interface-not-working.patch \
	file://0017-gpio-poweroff-Implement-force-pm-power-off-flag.patch \
	file://0018-spi-nor-Fix-spi-nor-read-and-write-in-edge-cases.patch \
	file://0019-Add-topic-miami-devicetrees.patch \
	file://0020-topic-miami-update-partition-layout.patch \
	file://0021-zynqmp-topic-miamimp-Enable-multi-die-support-for-QS.patch \
	file://0022-dmaengine-virt-dma-store-result-on-dma-descriptor.patch \
	file://0023-Add-include-linux-fpga-adi-axi-common.h-header.patch \
	file://0024-dma-axi-dmac-Update-to-adi-xcomm_zynq-version.patch \
	file://0025-media-platform-Add-ADI-drivers.patch \
	file://0026-media-platform-axi-hdmi-rx-Announce-GPLv2-module-lic.patch \
	file://0027-sound-soc-dai.h-Add-SPDIF-format-constants.patch \
	file://0028-ASoC-snd_soc_of_parse_daifmt-Handle-SND_SOC_DAIFMT_S.patch \
	file://0029-drm-bridge-adv7511-Enable-SPDIF-support.patch \
	file://0030-devicetree-zynqmp.dtsi-Add-bootmode-selection-suppor.patch \
	file://0031-media-axi-hdmi-rx-Honor-pclk-sample-from-devicetree.patch \
	file://0032-xlnx_pl_disp-fix-missing-xlnx_crtc_unregister.patch \
	file://0001-clk-clk-si5341-Add-support-for-the-Si5345-series.patch \
	file://0001-net-mdio_device-Reset-GPIO-is-allowed-to-sleep.patch \
	file://0001-miamiplusmp-update-board-model-correct-qspi-size.patch \
	file://0001-devicetree-miamimp-control-VBUS-power.patch \
	file://0001-usb-dwc3-Add-support-for-VBUS-power-control.patch \
	file://0001-zynqmp-topic-miamiplusmp.dts-Support-hardware-revisi.patch \
	file://0001-gpio-pca953x-Add-support-for-the-NXP-PCAL9554B-C.patch \
	file://0001-zynqmp-topic-miamiplusmp.dts-enable-interrupt-and-Sy.patch \
	"

# Compile overlay-capable devicetrees
EXTRA_OEMAKE_append = " DTC_FLAGS="-p 1024 -@""
