inherit gitpkgv

PV = "${XLNX_HDMI_VERSION}+${SRCPV}"
PKGV = "${XLNX_HDMI_VERSION}+${GITPKGV}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

RPROVIDES:${PN} = "dp159"

SRC_URI:append = "\
	file://0001-Add-support-for-manual-control-of-tx-refclk-rdy-sign.patch \
	file://0002-dp159-enable-output-termination.patch \
	file://0003-xilinx_drm_hdmi-Do-not-floor-clock-rate.patch \
	file://0004-dp159-Cleanup-allow-400kHz-I2C-and-toggle-output.patch \
	file://0005-hdmi-Proper-clock-management.patch \
	"

# The original recipe sets PACKAGE_ARCH = "${SOC_FAMILY_ARCH}" which is broken
# because kernel modules depend on the kernel which is MACHINE specific. Fix
# it here by reverting what "inherit module" would have done.
PACKAGE_ARCH = "${MACHINE_ARCH}"
