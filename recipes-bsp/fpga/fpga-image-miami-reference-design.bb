SUMMARY = "FPGA bitstream for reference image of Miami-7015/7030 SoM"
XILINX_VIVADO_VERSION = "2014.1"
require recipes-bsp/fpga/fpga-image.inc
LICENSE = "internal"
LIC_FILES_CHKSUM = "file://${METATOPIC_BASE}/LICENSE;md5=cf85de037de7ae12cc2d0059741fdbae"

COMPATIBLE_MACHINE = "topic-miami"

GITHUB_TEP_URI ?= "git://github.com/topic-embedded-products"
BOARD_DESIGN_URI = "${GITHUB_TEP_URI}/${BPN}"
BOARD_DESIGN_PATH = ""

inherit gitpkgv
SRCREV = "4c97f5f3de034d09a606a15354a3ff219721a5ca"
PV = "0.${SRCPV}"
PKGV = "0.${GITPKGV}"

do_compile:prepend() {
	export MACHINE="${MACHINE}"
	chmod a+x *.sh
}
