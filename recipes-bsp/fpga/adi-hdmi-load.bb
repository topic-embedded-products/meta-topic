DESCRIPTION = "HDMI framebuffer module loader to load in correct order"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
inherit allarch

PV = "8"
PR = "r0"

PACKAGES = "${PN}"

S="${WORKDIR}"

SRC_URI = "file://modules.conf"

do_compile() {
}

do_install() {
	install -d ${D}/etc/modules-load.d
	install -m 644 ${S}/modules.conf ${D}/etc/modules-load.d/adi-hdmi.conf
}

