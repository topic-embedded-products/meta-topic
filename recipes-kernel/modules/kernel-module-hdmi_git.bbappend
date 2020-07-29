inherit gitpkgv

PV = "${XLNX_HDMI_VERSION}+${SRCPV}"
PKGV = "${XLNX_HDMI_VERSION}+${GITPKGV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

RPROVIDES_${PN} = "dp159"

SRC_URI_append = "\
	file://0001-Add-support-for-manual-control-of-tx-refclk-rdy-sign.patch \
	file://0002-TOPIC-4_HDP_mechanism_fix.patch \
	file://0003-xiling-drm-hdmi-do-not-floor-tx-refclock.patch \
	file://0004-xilinx-drm-hdmi-add-support-for-RGBA.patch \
	file://0001-Changed-the-structure-to-now-longer-check-using-the-.patch \
	"
