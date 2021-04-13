inherit gitpkgv

PV = "${XLNX_HDMI_VERSION}+${SRCPV}"
PKGV = "${XLNX_HDMI_VERSION}+${GITPKGV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

RPROVIDES_${PN} = "dp159"

SRC_URI_append = "\
	file://0001-Add-support-for-manual-control-of-tx-refclk-rdy-sign.patch \
	"

#TODO:
#	file://0002-TOPIC-4_HDP_mechanism_fix.patch \
#	file://0001-Changed-the-structure-to-now-longer-check-using-the-.patch \
#       file://0003-xiling-drm-hdmi-do-not-floor-tx-refclock.patch \
#

# The original recipe sets PACKAGE_ARCH = "${SOC_FAMILY_ARCH}" which is broken
# because kernel modules depend on the kernel which is MACHINE specific. Fix
# it here by reverting what "inherit module" would have done.
PACKAGE_ARCH = "${MACHINE_ARCH}"
