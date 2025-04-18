FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI:append:class-target = "\
	file://0001-DRI_Add_xlnx_dri.patch \
	file://0001-DRI-Add-axi_hdmi_drm.patch \
	"
PACKAGECONFIG += "${@bb.utils.contains('MACHINE_FEATURES', 'mali400', 'dri3 lima kmsro', '', d)}"
