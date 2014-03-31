# TODO: Convert fpga-image to a bbclass
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = "https://github.com/topic-embedded-products/fpga-image-example.git"

inherit gitpkgv
PV = "1.${SRCPV}"
PKGV = "1.${GITPKGV}"

DEPENDS += "dyplo-ip-native"
export DYPLO_DIR="${STAGING_DATADIR_NATIVE}/dyplo"
