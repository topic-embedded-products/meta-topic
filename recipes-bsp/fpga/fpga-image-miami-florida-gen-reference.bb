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

SRC_URI[topic-miami-florida-gen-xc7z015.md5sum] = "64abdc1d71e2e97a2f4210ff6293e87b"
SRC_URI[topic-miami-florida-gen-xc7z015.sha256sum] = "becc5d7ba7954817b2ebc107442aeb72e4c938249235ad3a2ed3acbdea11ef8d"
SRC_URI[topic-miami-florida-gen-xc7z030.md5sum] = "b92e1acaed3ed6eb1687a5b51396bedd"
SRC_URI[topic-miami-florida-gen-xc7z030.sha256sum] = "9b9ab6f528113d397c3106c4fafad79001aca614906df24bfbca1dd90a93e2e3"
