SUMMARY = "Hardware detection script for XDP"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://${BPN}.sh"
S = "${WORKDIR}"

inherit allarch update-rc.d

FILES_${PN} = "${sysconfdir}"

INITSCRIPT_NAME = "${BPN}.sh"
INITSCRIPT_PARAMS = "start 08 S ."

do_compile() {
	true
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${S}/${BPN}.sh ${D}${sysconfdir}/init.d/
}
