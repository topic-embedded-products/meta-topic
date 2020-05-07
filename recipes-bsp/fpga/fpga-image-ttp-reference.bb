SUMMARY = "FPGA bitstream for ttpzu9"
COMPATIBLE_MACHINE = "^ttpzu9"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "0+10a7d0b4c"

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

SRC_URI[ttpzu9.sha256sum] = "2f69e271d3ccef0bb4e35d5a4fe1ff8b26e8aa91eb76c5597ea266e38e123562"
