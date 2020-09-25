SUMMARY = "FPGA bitstream for tdpzu9"
COMPATIBLE_MACHINE = "^tdpzu9"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "129+430df4c"

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

SRC_URI[tdpzu9.sha256sum] = "4b1b70f519e27094678a61180ab00192b8f8e6188d03b27fafc2ba12b1bc502c"
