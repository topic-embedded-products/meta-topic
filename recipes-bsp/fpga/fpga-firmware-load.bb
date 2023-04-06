DESCRIPTION = "FPGA bitstream image loader, loads fpga.bin early at boot"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
# Package is machine independent (shell script only)
inherit allarch

PACKAGES = "${PN}"

SRC_URI = "file://init file://${BPN}.service file://${BPN}.sh"

S = "${WORKDIR}"

inherit update-rc.d systemd

# Set to start at 03, which is before modutils
# so you can autoload modules which use FPGA logic.
INITSCRIPT_NAME = "${BPN}.sh"
INITSCRIPT_PARAMS = "start 03 S ."

SYSTEMD_SERVICE:${PN} = "${BPN}.service"

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
}
