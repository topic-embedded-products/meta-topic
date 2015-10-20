SUMMARY = "FPGA bitstream for Miami Florida GEN boards"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"
PV = "v2r3"

BITSTREAM_FILENAME ?= ""
BITSTREAM_FILENAME_xc7z015 = "flo-med-7z15-ref-${PV}.bit"
BITSTREAM_FILENAME_xc7z030 = "flo-med-7z30-ref-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME};name=${FPGA_FAMILY}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[xc7z015.md5sum] = "872f962e2021136e9737eabdbd880878"
SRC_URI[xc7z015.sha256sum] = "5341b7d82520be868a0c8426fc4065b45ab90593dfefef7e1fefeea2a9d678f1"
SRC_URI[xc7z030.md5sum] = "563c1178c562eabdce6ae4cc78137b83"
SRC_URI[xc7z030.sha256sum] = "02db22e9d0aed5b3d68620665ec5aa30bc926354b5dac5dc985d392cf6ee2364"
