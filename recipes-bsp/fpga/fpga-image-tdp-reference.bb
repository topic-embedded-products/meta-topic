SUMMARY = "FPGA bitstream for tdpzu9"
COMPATIBLE_MACHINE = "^tdpzu9"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "106+65e019d"

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

SRC_URI[tdpzu9.sha256sum] = "6985c3e50a385bb54e131228c1ef710269131df82f3e3175e575aef55404045e"
