LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=9eef91148a9b14ec7f9df333daebc746"

MODULE = "${@d.getVar('PN', True).split('-', 2)[2]}"

inherit module gitpkgv

PV = "3+${SRCPV}"
PKGV = "3+${GITPKGV}"
S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"

GITHUB_TOPIC_URI ?= "git://github.com/topic-embedded-products"
GITHUB_TOPIC_URI_SUFFIX ?= ";protocol=https"
BRANCH = "master"
SRC_URI = "${GITHUB_TOPIC_URI}/${BPN}${GITHUB_TOPIC_URI_SUFFIX};branch=${BRANCH}"
