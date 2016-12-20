DESCRIPTION = "FPGA bitstream image loader, loads fpga.bin early at boot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
# Package is machine independent (shell script only)
inherit allarch

PACKAGES = "${PN}"

SRC_URI = "file://init file://fpga-image-load.service file://fpga-image-load.sh"

S = "${WORKDIR}"

inherit update-rc.d systemd

# Set to start at 03, which is before modutils
# so you can autoload modules which use FPGA logic.
INITSCRIPT_NAME = "${PN}.sh"
INITSCRIPT_PARAMS = "start 03 S ."

SYSTEMD_SERVICE_${PN} = "fpga-image-load.service"

do_compile() {
	true
}

FILES_${PN} = "${bindir} ${sysconfdir} ${systemd_unitdir}"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/fpga-image-load.sh ${D}${bindir}/${PN}.sh
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}.sh
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/fpga-image-load.service ${D}${systemd_unitdir}/system/
	sed -i -e 's,@BINDIR@,${bindir},g' ${D}${systemd_unitdir}/system/fpga-image-load.service
}
