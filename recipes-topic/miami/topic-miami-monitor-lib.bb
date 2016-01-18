SUMMARY = "Example and/or library for reading SOM monitor devices"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=9eef91148a9b14ec7f9df333daebc746"


GITHUB_TOPIC_URI ?= "git://github.com/topic-embedded-products"
SRC_URI = "${GITHUB_TOPIC_URI}/${PN}"

SRCREV = "317ba8d151e5b56b0cdf36754c21a47c42255882"

inherit gitpkgv

PV = "0+${SRCPV}"
PKGV = "0+${GITPKGV}"
S = "${WORKDIR}/git"


do_install() {
	install -d ${D}${bindir}
	install -m 755 ${B}/topic-miami-monitor-example ${D}${bindir}
}
