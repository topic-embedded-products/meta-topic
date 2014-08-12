DESCRIPTION = "Commandline example application for DYPLO."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=9eef91148a9b14ec7f9df333daebc746"
DEPENDS = "libdyplo"

include dyplo-stable-revisions.inc
inherit autotools pkgconfig gitpkgv

PV = "0+${SRCPV}"
PKGV = "0+${GITPKGV}"
PR = "r0"
S = "${WORKDIR}/git"

SRC_URI = "git://github.com/topic-embedded-products/${PN}.git"
