SUMMARY = "FPGA referene design bitstream"
COMPATIBLE_MACHINE = "^tdkz[0-9]"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "174+94c73c4"

BOARD_DESIGN_PATH = "${BPN}"
TOPICDOWNLOADS_URI ?= "http://topic-downloads.fra1.digitaloceanspaces.com"
BOARD_DESIGN_URI = "${TOPICDOWNLOADS_URI}/files/fpga-image-${MACHINE}-reference-${PV}.bit.xz;name=${MACHINE}"

PKGV = "${PV}"
S = "${WORKDIR}"
B = "${S}"

# Nothing to build
do_compile() {
    cp fpga-image-${MACHINE}-reference-${PV}.bit fpga.bit
}

SRC_URI[tdkz10.sha256sum] = "466018fec93f6e1e3a01a1af97695d63ba1011185ebd130353cedcd15c420924"
SRC_URI[tdkz07.sha256sum] = "b4c82e37a3e5c8f04432aea1da69c5c7a3681f57421480026a59719569af3add"
