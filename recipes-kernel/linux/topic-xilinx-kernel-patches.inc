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

SRC_URI_append = "\
	${MDEVSUPPORT} \
	file://enable-kexec-support.cfg \
	file://0001-iio-accel-Add-support-for-the-Bosch-Sensortec-BMI088.patch \
	file://0002-iio-gyro-bmg160-Add-support-for-BMI088-chip.patch \
	file://0003-clk-clk-si5341-Support-multiple-input-ports.patch \
	file://0004-clk-clk-si5341-Add-support-for-the-Si5345-series.patch \
	file://0005-Add-ltc3562-voltage-regulator-driver.patch \
	file://0006-gpio-poweroff-Implement-force-pm-power-off-flag.patch \
	file://0007-drm-xlnx-pl_disp-fix-missing-xlnx_crtc_unregister.patch \
	file://0008-usb-dwc3-Add-support-for-VBUS-power-control.patch \
	file://0009-drm-introduce-helper-for-accessing-EDID-blob-in-drm_.patch \
	file://0010-Add-topic-miami-devicetrees.patch \
	"

# Compile overlay-capable devicetrees
EXTRA_OEMAKE_append = " DTC_FLAGS="-p 1024 -@""
