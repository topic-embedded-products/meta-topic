SUMMARY = "FPGA bitstream for XDP board reference design"
COMPATIBLE_MACHINE = "^xdpzu7"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "2"

BOARD_DESIGN_PATH = "${BPN}-${MACHINE}"
TOPICDOWNLOADS_URI ?= "http://topic-downloads.fra1.digitaloceanspaces.com"
BOARD_DESIGN_URI = "${TOPICDOWNLOADS_URI}/files/${BOARD_DESIGN_PATH}-${PV}.tar.xz;name=${MACHINE}"

PKGV = "${PV}"
S = "${WORKDIR}"
B = "${S}"

# Nothing to build
do_compile() {
    true
}

SRC_URI[xdpzu7.md5sum] = "973cc3a607565fcd06276d12ba39b5b5"
