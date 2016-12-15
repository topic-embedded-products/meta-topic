SUMMARY = "Automatically start and stop network interfaces on link status"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
PV = "1"

inherit allarch update-rc.d

SRC_URI = "file://init.sh file://ifplugd.auto"

S = "${WORKDIR}"
PACKAGES = "${PN}"
FILES_${PN} = "${sysconfdir}"

# Startup similar to init-ifupdown
INITSCRIPT_NAME = "ifplug-auto-net.sh"
INITSCRIPT_PARAMS = "start 01 2 3 4 5 ."

do_compile() {
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${S}/init.sh ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
	install -m 755 ${S}/ifplugd.auto ${D}${sysconfdir}/ifplugd.auto
}
