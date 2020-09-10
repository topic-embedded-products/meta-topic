SUMMARY = "FPGA bitstream for ttpzu9"
COMPATIBLE_MACHINE = "^ttpzu9"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "9f2686c7e07bbe873f08b4e5be7879d4fd049397"

BOARD_DESIGN_PATH = "${BPN}-${MACHINE}"
TOPICDOWNLOADS_URI ?= "http://topic-downloads.fra1.digitaloceanspaces.com"
BOARD_DESIGN_URI = "${TOPICDOWNLOADS_URI}/files/${BOARD_DESIGN_PATH}-${PV}.tar.xz;name=${MACHINE}"

PKGV = "${PV}"
S = "${WORKDIR}/${BOARD_DESIGN_PATH}"
B = "${S}"

# Nothing to build
do_compile() {
    true
}

SRC_URI[ttpzu9.sha256sum] = "e2a23b0e1d9ac0fb2b8a7671f263310b919dcb9601b81af72d71b8ff2ccce4d3"
