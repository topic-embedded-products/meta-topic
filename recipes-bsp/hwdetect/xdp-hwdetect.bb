SUMMARY = "Hardware detection script for XDP"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PV = "3"
SRC_URI = "file://init file://${BPN}.service file://${BPN}.sh"
SRC_URI_petalinux = "file://init file://${BPN}.service file://${BPN}-peta.sh"
S = "${WORKDIR}"

inherit allarch update-rc.d systemd

INITSCRIPT_NAME = "${BPN}.sh"
INITSCRIPT_PARAMS = "start 08 S ."

SYSTEMD_SERVICE_${PN} = "${BPN}.service"

RDEPENDS_${PN} += "yavta media-ctl"

do_compile() {
	true
}

FILES_${PN} = "${bindir} ${sysconfdir} ${systemd_unitdir}"

do_install_prepend_petalinux() {
	mv ${WORKDIR}/${BPN}-peta.sh ${WORKDIR}/${BPN}.sh
}

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
