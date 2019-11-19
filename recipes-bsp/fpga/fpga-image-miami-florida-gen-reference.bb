SUMMARY = "FPGA bitstream for Miami Florida GEN boards"
COMPATIBLE_MACHINE = "topic-miami-florida-gen"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"
PV = "v3r0.1"

PLNAME = "topic-miami-florida-gen-${FPGA_FAMILY}"
BITSTREAM_FILENAME = "${PLNAME}-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images/"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME};name=${PLNAME}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[topic-miami-florida-gen-xc7z015.md5sum] = "54c27f024fafb513856c1721eedcb215"
SRC_URI[topic-miami-florida-gen-xc7z015.sha256sum] = "84127ebe7e0c939b61025b572fa7feea69355c6934b596053935c376fbac66d2"
SRC_URI[topic-miami-florida-gen-xc7z030.md5sum] = "24cc4aad21d452d164f877f398a9e9c5"
SRC_URI[topic-miami-florida-gen-xc7z030.sha256sum] = "de540256c9ae4f05b2128225f2209a692fe518b94eb2589f3317ae6cee4d74e7"
