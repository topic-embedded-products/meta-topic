SUMMARY = "FPGA bitstream for Miami Florida PCIe boards"
COMPATIBLE_MACHINE = "topic-miami-florida-pci"

# Downloads a precompiled bitstream from the TOPIC website. 

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"
PV = "v1r2"

BITSTREAM_FILENAME = "${MACHINE}-${PV}.bit"
TOPICEMBEDDED_URIBASE ?= "http://www.topicembeddedproducts.com/support/download/public/reference-images"
SRC_URI = "${TOPICEMBEDDED_URIBASE}/${BITSTREAM_FILENAME};name=${MACHINE}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[topic-miami-florida-pci-xc7z015.md5sum] = "06417b941d8907f701b62e6d19111674"
SRC_URI[topic-miami-florida-pci-xc7z015.sha256sum] = "33707a5b7311f947e834dba39d2970a47b4f59b677ece2c27a1d5b10cc494e81"
SRC_URI[topic-miami-florida-pci-xc7z030.md5sum] = "4c55bfff6e5828c03b2c4dbd37f235fd"
SRC_URI[topic-miami-florida-pci-xc7z030.sha256sum] = "fdbc9b340c7e6c0a7a28a91c60826d0ab25a963594d51ca0ef1457cd8aa9270f"
