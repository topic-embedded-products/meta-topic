SUMMARY = "Example and/or library for reading SOM monitor devices"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=9eef91148a9b14ec7f9df333daebc746"


GITHUB_TOPIC_URI ?= "git://github.com/topic-embedded-products"
SRC_URI = "${GITHUB_TOPIC_URI}/${BPN}"

SRCREV = "ef7db896aa7282626431f7ab6723a9a9d62aea93"

inherit gitpkgv

PV = "0+${SRCPV}"
PKGV = "0+${GITPKGV}"
S = "${WORKDIR}/git"


do_install() {
	install -d ${D}${bindir}
	install -m 755 ${B}/topic-miami-monitor-example ${D}${bindir}
}
