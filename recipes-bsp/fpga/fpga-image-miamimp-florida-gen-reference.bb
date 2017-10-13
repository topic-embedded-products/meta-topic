SUMMARY = "FPGA bitstream for Miami Florida GEN boards"
COMPATIBLE_MACHINE = "topic-miamimp-florida-gen"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"

PV = "v3r0.3"
SRC_URI[topic-miamimp-florida-gen-xczu9eg.sha256sum] = "8274312b9402d0e49683d15d69f6a0c77d7a6d81ad5694d99abc5716b4bcfd57"
SRC_URI[topic-miamimp-florida-gen-xczu6eg.sha256sum] = "1efc0ee27410d2dd9934903f60d7161e393f08fdc2342c727dd5ce08373e2e34"
SRC_URI[topic-miamimp-florida-gen-xczu15eg.sha256sum] = "ec2125a068ab67124331b70a9268b57fe786e60e5343710ca882f99a120da3cd"

BITSTREAM_FILENAME = "${MACHINE}-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME}.xz;name=${MACHINE}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}
