DESCRIPTION = "Application that will be build along with the dyplo-wizard-image for DYPLO."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=9eef91148a9b14ec7f9df333daebc746"
DEPENDS = "libdyplo"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

