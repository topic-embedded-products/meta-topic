SUMMARY = "FPGA bitstream for XDP board reference design"
COMPATIBLE_MACHINE = "^xdpzu7"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "0"
S = "${WORKDIR}/${BPN}"

PLNAME = "${BPN}"
BASEFILENAME = "${PLNAME}-${PV}"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images/"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BASEFILENAME}.tar.xz;name=${PLNAME}"

# Precompiled bitstream
do_compile() {
	true
}

SRC_URI[fpga-image-xdp-reference.md5sum] = "01b09682c1fd78f4b62c23dc5e88c1f4"
SRC_URI[fpga-image-xdp-reference.sha256sum] = "72e39660c6365885436c48f835bd18bda49a954cae9d3d650ca2704c2d2eb24d"
