SUMMARY = "FPGA bitstream for tdpzu9"
COMPATIBLE_MACHINE = "^tdpzu9"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "137+6ddb002"

BOARD_DESIGN_PATH = "${BPN}"
TOPICDOWNLOADS_URI ?= "http://topic-downloads.fra1.digitaloceanspaces.com"
BOARD_DESIGN_URI = "${TOPICDOWNLOADS_URI}/files/${BOARD_DESIGN_PATH}-${PV}.xz;name=${MACHINE}"

PKGV = "${PV}"
S = "${WORKDIR}"
B = "${S}"

# Nothing to build
do_compile() {
   cp ${BOARD_DESIGN_PATH}-${PV} fpga.bit
}

SRC_URI[tdpzu9.sha256sum] = "474625df1f83e4bbda44a403be786bf5b74331b35cabb007d89fc9b27a08ea09"
