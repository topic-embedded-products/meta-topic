SUMMARY = "Run haveged --once at boot"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit allarch systemd update-rc.d

SRC_URI = "file://${BPN}.sh file://${BPN}.service"

INITSCRIPT_NAME = "${BPN}.sh"
INITSCRIPT_PARAMS = "start 10 S ."
SYSTEMD_SERVICE:${PN} = "${BPN}.service"

do_compile() {
	true
}

FILES:${PN} = "${sysconfdir} ${systemd_unitdir}"

RDEPENDS:${PN} += "haveged"

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/${BPN}.sh ${D}${sysconfdir}/init.d/${BPN}.sh
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/${BPN}.service ${D}${systemd_unitdir}/system/
}
