SUMMARY = "FPGA bitstream for Miami Florida GEN boards"
COMPATIBLE_MACHINE = "topic-miamimp-florida-gen"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"
PV = "v3r0.2"

BITSTREAM_FILENAME = "${MACHINE}-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME}.xz;name=${MACHINE}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[topic-miamimp-florida-gen-xczu6eg.sha256sum] = "4b88f395600993ce99b0526dd6f1a7af16ba4ddedfa1e0b3072f4ac314521877"
SRC_URI[topic-miamimp-florida-gen-xczu9eg.sha256sum] = "10a290160ddbb07adfe0b502313e375c1ea0892e8e59ad6f00a80d03a883b6ad"
SRC_URI[topic-miamimp-florida-gen-xczu15eg.sha256sum] = "890033215a8d00d7af00a82fa47e9221c53f4ffc01d81ad055c301b92db95ad8"
