SUMMARY = "FPGA bitstream for tdpzu9"
COMPATIBLE_MACHINE = "^tdpzu9"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "115+3f976cb"

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

SRC_URI[tdpzu9.sha256sum] = "5cc71ba82f1d582584fd4caecf476586d1194e11af8b262bb24a0dd0edb429d2"
