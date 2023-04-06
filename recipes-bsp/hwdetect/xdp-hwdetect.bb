SUMMARY = "Hardware detection script for XDP"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

PV = "3"
SRC_URI = "file://init file://${BPN}.service file://${BPN}.sh"
S = "${WORKDIR}"

inherit allarch update-rc.d systemd

INITSCRIPT_NAME = "${BPN}.sh"
INITSCRIPT_PARAMS = "start 08 S ."

SYSTEMD_SERVICE:${PN} = "${BPN}.service"

RDEPENDS:${PN} += "yavta media-ctl"

do_compile() {
	true
}

FILES:${PN} = "${bindir} ${sysconfdir} ${systemd_unitdir}"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/${BPN}.sh ${D}${bindir}/${PN}.sh
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${BPN}.sh
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/${BPN}.service ${D}${systemd_unitdir}/system/
	sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/${BPN}.service
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}
	then
		# Create symlink that blocks ttyS0 (it's the bluetooth port, cannot run getty)
		install -d ${D}${sysconfdir}/systemd/system
		ln -s /dev/null ${D}${sysconfdir}/systemd/system/getty@ttyS0.service
	fi
}
