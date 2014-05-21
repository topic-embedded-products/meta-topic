require recipes-bsp/fpga/fpga-image.inc
SUMMARY = "FPGA reference design for Miami prototype boards"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c16666ae6c159876a0ba63099614381"

BOARD_DESIGN_URI = "git://github.com/topic-embedded-products/fpga-image-miami.git"
SRCREV = "43339c30f29edd21b4786fc2592b4d00a0a9f405"

COMPATIBLE_MACHINE = "zynq-miami"

inherit gitpkgv
PV = "1.${SRCPV}"
PKGV = "1.${GITPKGV}"

do_compile_prepend() {
	export MACHINE="${MACHINE}"
}
