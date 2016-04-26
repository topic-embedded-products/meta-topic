SUMMARY = "FPGA bitstream for Miami Florida GEN boards"
COMPATIBLE_MACHINE = "topic-miami-florida-gen"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"
PV = "v3r0"

BITSTREAM_FILENAME = "${MACHINE}-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images/"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME};name=${MACHINE}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[topic-miami-florida-gen-xc7z015.md5sum] = "c3d6ed361fbd5fca26273531564c6cd5"
SRC_URI[topic-miami-florida-gen-xc7z015.sha256sum] = "6d63cef1dfe9709d65e0bcd5da38c060503bb066069b516680e711e3e8475f10"
SRC_URI[topic-miami-florida-gen-xc7z030.md5sum] = "1137ed5ea193c0a41f7048cc22773190"
SRC_URI[topic-miami-florida-gen-xc7z030.sha256sum] = "99f27ca82fd52235186231325b9365f4adc4131df824e176e296c4afbf8a4b6c"
