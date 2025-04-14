FILESEXTRAPATHS:prepend := "${THISDIR}/linux-xlnx:"

# petalinux distro does not explicitly set this, so have a sane default here
VIRTUAL-RUNTIME_dev_manager ??= 'busybox-mdev'
# When using mdev, this solves the following error on new kernels:
#   can't create /proc/sys/kernel/hotplug: nonexistent directory
MDEVSUPPORT = "${@bb.utils.contains('VIRTUAL-RUNTIME_dev_manager', 'busybox-mdev', 'file://enable-mdev-hotplug.cfg', '', d)}"
EXFATSUPPORT = "file://enable-exfat-fs.cfg"
KEXECSUPPORT = "file://enable-kexec-support.cfg"

HDMI_OUT_PATCHES = "\
	file://adi/0001-axi-hdmi-driver-add-driver.patch \
	file://adi-topic/0001-drm-axi_hdmi_crtc.c-Skip-DMA_INTERLEAVE-check.patch \
	file://adi-topic/0001-adv7511_audio-Only-register-the-SPDIF-output.patch \
	"

SRC_URI:append = "\
	${MDEVSUPPORT} \
	${EXFATSUPPORT} \
	${KEXECSUPPORT} \
	${HDMI_OUT_PATCHES} \
	file://0001-net-phy-xilinx_phy-Reset-PHY-to-resume-from-shutdown.patch \
	file://0001-pcie-xilinx-Support-reset-GPIO-and-wait-for-link-up-.patch \
	file://0001-usb-hub-Ignore-OTG-negotiation-problems-during-enume.patch \
	file://0001-usb-dwc3-Disable-hibernation-by-default.patch \
	file://0002-usb-dwc3-core-add-transceiver-delay-for-required-UTM.patch \
	file://0003-usb-dwc3-xilinx-Prevent-spike-in-reset-signal.patch \
	file://0004-usb-dwc3-Add-support-for-VBUS-power-control.patch \
	file://0004-phy-zynqmp-Postpone-getting-clock-rate-until-it-s-ac.patch \
	file://0005-phy-zynqmp-Longer-PLL-timeout.patch \
	file://0006-wireless-brcmfmac-Disable-power-save-mode.patch \
	file://0007-clk-si5341-Support-NVM-programming-through-sysfs.patch \
	file://0008-clk-si5341-Add-output_mask-attribute.patch \
	file://0009-clk-Add-fixed-clock-nvmem-provider.patch \
	file://0010-Add-ltc3562-voltage-regulator-driver.patch \
	file://0011-overlay-Reduce-memory-leak-warning-to-debug-level.patch \
	file://0012-gpio-poweroff-Implement-force-pm-power-off-flag.patch \
	file://0013-spi-nor-Skip-4BAIT-parsing-on-Xilinx-Zynq-QSPI-contr.patch \
	file://0014-pinctrl-mcp23s08-Reset-all-pins-to-input-at-probe.patch \
	"


# Compile overlay-capable devicetrees
EXTRA_OEMAKE:append = " DTC_FLAGS="-p 1024 -@""
