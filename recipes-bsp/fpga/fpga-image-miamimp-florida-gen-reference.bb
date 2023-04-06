SUMMARY = "FPGA bitstream for Miami Florida GEN boards"
COMPATIBLE_MACHINE = "^tdkzu"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"

PV = "v3r0.4"
SRC_URI[topic-miamimp-florida-gen-xczu9eg.sha256sum] = "6e9e72f52d2383108a50335366a8a289122a306fbc6aebbf470264e75a2713ac"
SRC_URI[topic-miamimp-florida-gen-xczu6eg.sha256sum] = "d328e8a8f01a700d4971cf74dacd5fa530c36ec10b7e2054a58c9bbbc31e7244"
SRC_URI[topic-miamimp-florida-gen-xczu15eg.sha256sum] = "a1d96dcadb9f74b0bd8ed437915a94945cb4edab3bc832d4db63c06aaf184f4c"

PLNAME = "topic-miamimp-florida-gen-${FPGA_FAMILY}"
BITSTREAM_FILENAME = "${PLNAME}-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME}.xz;name=${PLNAME}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}
