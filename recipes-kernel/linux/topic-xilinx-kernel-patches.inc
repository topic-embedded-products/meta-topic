FILESEXTRAPATHS:prepend := "${THISDIR}/linux-xlnx:"

# Brings the Xilinx kernel up to date with our fork at:
#   https://github.com/topic-embedded-products/linux
# branch: topic-miami-xlnx-rebase-5.4-x2020.1
# To start development:
#   git clone -b topic-miami-xlnx-rebase-5.4-x2020.1 git@github.com:topic-embedded-products/linux.git
#
# Patches created using this command:
#   git format-patch -o /tmp/ xlnx_rebase_v5.4_2020.1..HEAD

# petalinux distro does not explicitly set this, so have a sane default here
VIRTUAL-RUNTIME_dev_manager ??= 'busybox-mdev'
# When using mdev, this solves the following error on new kernels:
#   can't create /proc/sys/kernel/hotplug: nonexistent directory
MDEVSUPPORT = "${@bb.utils.contains('VIRTUAL-RUNTIME_dev_manager', 'busybox-mdev', 'file://enable-mdev-hotplug.cfg', '', d)}"
EXFATSUPPORT = "file://enable-exfat-fs.cfg"
KEXECSUPPORT = "file://enable-kexec-support.cfg"


# TODO: These patches need updating for the 5.15 kernel
#

# These patches are cherry-picked from the ADI Linux kernel, branch "adi-5.4.0"
# The "-topic" patches are topic specific
ADIHDMISUPPORT = "\
	file://adi/0001-axi-hdmi-driver-add-driver.patch \
	file://adi/0002-drm-adi_axi_hdmi-add-includes-required-to-build-in-n.patch \
	file://adi/0003-drm-adi_axi_hdmi-remove-drm_plane_helper_disable-cal.patch \
	file://adi/0004-drm-adi_axi_hdmi-update-driver-to-newer-framework-WI.patch \
	file://adi/0005-drm-adv7511-Fix-low-refresh-rate-register-for-ADV753.patch \
	file://adi/0006-drm-bridge-adv7511-Implement-regmap-for-EDID-memory-.patch \
	file://adi/0007-drm-adv7511-check-return-code-for-regmap_bulk_read.patch \
	file://adi/0008-drm-adv7511-do-4-regmap_bulk_read-calls-for-EDID.patch \
	file://adi-topic/0001-drm-axi_hdmi_crtc.c-Skip-DMA_INTERLEAVE-check.patch \
	file://adi-topic/0002-drm-axi_hdmi_encoder.c-Add-missing-drm_print.h-inclu.patch \
	file://adi-topic/0003-sound-soc-adi-axi-spdif.c-Support-programmable-maste.patch \
	file://adi-topic/0004-drm-adi_axi_hdmi-Fix-fbdev-interface-not-working.patch \
	file://adi-topic/0005-drm-axi_hdmi_encoder.c-Fix-adv7511.h-include-path.patch \
	file://adi-topic/0006-drm-axi_hdmi_drv.c-Replace-drmP.h.patch \
	file://adi-topic/0007-drm-axi_hdmi_encoder-Fix-drm-bridge-methods.patch \
	file://adi-topic/0001-adv7511_audio-Only-register-the-SPDIF-output.patch \
"

ADIHDMIINSUPPORT = "\
	file://adi-hdmi-rx/0001-media-platform-Add-axi-hdmi-rx-driver.patch \
	"

XILINX_V4L2_FIXES = "\
	file://v4l2/0001-Support-DMA-resolution-adjustment.patch \
	file://v4l2/0002-media-imx274-update-to-5.12-kernel-version.patch \
	file://v4l2/0003-media-imx274-remove-dev_pm_ops-to-avoid-register-wri.patch \
	file://v4l2/0004-media-imx274-improve-v4l2-compliance.patch \
	file://v4l2/0005-media-imx274-remove-imx274_load_default.patch \
	file://v4l2/0006-Improve-resolution-control.patch \
	file://v4l2/0007-Fix-colors-resetting-after-format-adjust.patch \
	"

SRC_URI:append = "\
	${MDEVSUPPORT} \
	${EXFATSUPPORT} \
	${KEXECSUPPORT} \
	file://0001-net-phy-xilinx_phy-Reset-PHY-to-resume-from-shutdown.patch \
	file://0001-pcie-xilinx-Support-reset-GPIO-and-wait-for-link-up-.patch \
	file://0001-usb-hub-Ignore-OTG-negotiation-problems-during-enume.patch \
	file://0001-usb-dwc3-Disable-hibernation-by-default.patch \
	file://0002-usb-dwc3-xilinx-Fix-sequence-and-propagate-error-cod.patch \
	file://0003-usb-dwc3-core-add-transceiver-delay-for-required-UTM.patch \
	file://0004-phy-zynqmp-Postpone-getting-clock-rate-until-it-s-ac.patch \
	file://0005-phy-zynqmp-Longer-PLL-timeout.patch \
	file://0006-wireless-brcmfmac-Disable-power-save-mode.patch \
	file://0007-clk-si5341-Support-NVM-programming-through-sysfs.patch \
	file://0008-clk-si5341-Add-output_mask-attribute.patch \
	file://0009-clk-clk-gpio-Implement-input-only-mode.patch \
	file://0010-Add-ltc3562-voltage-regulator-driver.patch \
	file://0011-overlay-Reduce-memory-leak-warning-to-debug-level.patch \
	file://0012-gpio-poweroff-Implement-force-pm-power-off-flag.patch \
	file://0013-spi-nor-Skip-4BAIT-parsing-on-Xilinx-Zynq-QSPI-contr.patch \
	file://0014-pinctrl-mcp23s08-Reset-all-pins-to-input-at-probe.patch \
	"

# Compile overlay-capable devicetrees
EXTRA_OEMAKE:append = " DTC_FLAGS="-p 1024 -@""
