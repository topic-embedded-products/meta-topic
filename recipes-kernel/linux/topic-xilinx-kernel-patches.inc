FILESEXTRAPATHS_prepend := "${THISDIR}/linux-xlnx:"

# Brings the Xilinx kernel up to date with our fork at:
#   https://github.com/topic-embedded-products/linux
# branch: topic-miami-xlnx-rebase-5.4-x2020.1
# To start development:
#   git clone -b topic-miami-xlnx-rebase-5.4-x2020.1 git@github.com:topic-embedded-products/linux.git
#
# Patches created using this command:
#   git format-patch -o /tmp/ xlnx_rebase_v5.4_2020.1..HEAD

# When using mdev, this solves the following error on new kernels:
#   can't create /proc/sys/kernel/hotplug: nonexistent directory
MDEVSUPPORT = "${@bb.utils.contains('VIRTUAL-RUNTIME_dev_manager', 'busybox-mdev', 'file://enable-mdev-hotplug.cfg', '', d)}"

# These patches are cherry-picked from the ADI Linux kernel, branch "adi-5.4.0"
# The "-topic" patches are topic specific
ADIHDMISUPPORT = "\
	file://adi/0001-axi-hdmi-driver-add-driver.patch \
	file://adi/0002-drm-adi_axi_hdmi-add-includes-required-to-build-in-n.patch \
	file://adi/0003-drm-adi_axi_hdmi-remove-drm_plane_helper_disable-cal.patch \
	file://adi/0004-drm-adi_axi_hdmi-update-driver-to-newer-framework-WI.patch \
	file://adi/0005-drm-bridge-adv7511-Enable-SPDIF-support.patch \
	file://adi/0006-drm-adv7511-Fix-low-refresh-rate-register-for-ADV753.patch \
	file://adi/0007-drm-adv7511-Add-suppot-for-ADV7535.patch \
	file://adi/0008-drm-bridge-adv7511-Implement-regmap-for-EDID-memory-.patch \
	file://adi/0009-drm-adv7511-Add-debugfs-interface.patch \
	file://adi/0010-drm-adv7511-add-includes-for-struct-types.patch \
	file://adi/0011-drm-adv7511-check-return-code-for-regmap_bulk_read.patch \
	file://adi/0012-drm-adv7511-do-4-regmap_bulk_read-calls-for-EDID.patch \
	file://adi-topic/0001-drm-axi_hdmi_crtc.c-Skip-DMA_INTERLEAVE-check.patch \
	file://adi-topic/0002-drm-axi_hdmi_encoder.c-Add-missing-drm_print.h-inclu.patch \
	file://adi-topic/0003-sound-soc-adi-axi-spdif.c-Support-programmable-maste.patch \
	file://adi-topic/0004-drm-adi_axi_hdmi-Fix-fbdev-interface-not-working.patch \
	file://adi-hdmi-rx/0001-media-Add-axi-hdmi-rx-driver.patch \
	file://adi-hdmi-rx/0002-media-axi-hdmi-rx-fix-implicit-fall-through-statemen.patch \
	file://adi-hdmi-rx/0003-media-axi-hdmi-rx-use-common-AXI-ADI-regs.patch \
	file://adi-hdmi-rx/0004-media-axi-hdmi-rx-Honor-pclk-sample-from-devicetree.patch \
	file://adi-hdmi-rx/0005-media-axi-hdmi-rx-move-axi_hdmi_rx_read-under-VIDEO_.patch \
	file://adi-hdmi-rx/0006-media-axi-hdmi-rx-rework-bridge-notifier-to-new-form.patch \
	file://adi-hdmi-rx/0007-media-platform-axi-hdmi-rx-Announce-GPLv2-module-lic.patch \
	file://adi-hdmi-rx/0008-media-axi-hdmi-rx-Provide-device_caps-in-struct-vide.patch \
	file://adi-hdmi-rx/0009-drivers-media-i2c-adv7604-Add-Free-Running-Mode-v4l-.patch \
	file://adi-hdmi-rx/0010-media-adv7604-Add-new-predefined-video-timings-for-a.patch \
	file://adi-hdmi-rx/0011-media-adv7604-Adjust-Pixel-Clock-depending-on-Deep-C.patch \
	file://adi-hdmi-rx/0012-drivers-media-i2c-adv7604-Enable-Video-Adjustment-co.patch \
	file://adi-hdmi-rx/0013-media-adv7604-Fix-HDMI-mode-detection-for-the-ADV761.patch \
	file://adi-hdmi-rx/0014-drivers-media-i2c-adv7604-Deliver-events-notificatio.patch \
	file://adi-hdmi-rx/0015-media-adv7604-fix-deep-color-mode.patch \
	file://adi-hdmi-rx/0016-media-axi-hdmi-rx-Initialize-bus_type.patch \
	"

SRC_URI_append = "\
	${MDEVSUPPORT} \
	file://enable-kexec-support.cfg \
	file://0001-iio-accel-Add-support-for-the-Bosch-Sensortec-BMI088.patch \
	file://0002-iio-gyro-bmg160-Add-support-for-BMI088-chip.patch \
	file://0003-clk-clk-si5341-Support-multiple-input-ports.patch \
	file://0004-clk-clk-si5341-Add-support-for-the-Si5345-series.patch \
	file://0005-Add-ltc3562-voltage-regulator-driver.patch \
	file://0006-gpio-poweroff-Implement-force-pm-power-off-flag.patch \
	file://0008-usb-dwc3-Add-support-for-VBUS-power-control.patch \
	file://0009-drm-introduce-helper-for-accessing-EDID-blob-in-drm_.patch \
	file://0012-gpio-pca953x-Add-support-for-the-NXP-PCAL9554B-C.patch \
	file://mcp23s08/0004-pinctrl-mcp23s08-Reset-all-pins-to-input-at-probe.patch \
	${ADIHDMISUPPORT} \
	"
#file://0001-mmc-sdhci-of-arasan-Resolve-failed-sd-mmc-tuning-due.patch 
#file://0007-drm-xlnx-pl_disp-fix-missing-xlnx_crtc_unregister.patch

# Compile overlay-capable devicetrees
EXTRA_OEMAKE_append = " DTC_FLAGS="-p 1024 -@""
