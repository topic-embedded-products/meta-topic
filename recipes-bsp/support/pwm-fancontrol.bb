DESCRIPTION = "Temperature controlled fans"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://../COPYING;md5=9eef91148a9b14ec7f9df333daebc746"

inherit gitpkgv update-rc.d systemd

GITHUB_TOPIC_URI ?= "git://github.com/topic-embedded-products"
GITHUB_TOPIC_URI_SUFFIX ?= ";protocol=https"
SRC_URI = "${GITHUB_TOPIC_URI}/kernel-module-topic-pl-fanctrl${GITHUB_TOPIC_URI_SUFFIX}"

PV = "0+${SRCPV}"
PKGV = "0+${GITPKGV}"
S = "${WORKDIR}/git/app"
SRCREV = "041f3745f3eff6ff89c834d3b883dc4f0df265d1"

INITSCRIPT_NAME = "${BPN}.sh"
INITSCRIPT_PARAMS = "start 9 S ."
SYSTEMD_SERVICE:${PN} = "${BPN}.service"

do_install() {
	install -d ${D}${bindir}
	install -m 755 ${B}/${BPN} ${B}/${BPN}.sh ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${S}/init.sh ${D}${sysconfdir}/init.d/${BPN}.sh
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${S}/${BPN}.service ${D}${systemd_unitdir}/system/
}
