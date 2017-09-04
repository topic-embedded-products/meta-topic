SUMMARY = "FPGA bitstream for Miami Florida GEN boards"
COMPATIBLE_MACHINE = "topic-miamimp-florida-gen"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"
PV = "v3r0"

BITSTREAM_FILENAME = "${MACHINE}-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images/"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME}.xz;name=${MACHINE}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[topic-miamimp-florida-gen-xczu9eg.sha256sum] = "a03b811cf25b3571c0ba59364fae79f6b9bb3f8823ed2194819366cc19ca2ff3"
