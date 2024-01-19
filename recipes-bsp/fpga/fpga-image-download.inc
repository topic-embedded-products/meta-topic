# Download binary bitstream instead of compiling it
SUMMARY = "FPGA reference image"
require fpga-image.inc
LICENSE = "CLOSED"

# Expect to get PV and checksums from bb file that includes this file

TOPICDOWNLOADS_URI ?= "http://topic-downloads.fra1.digitaloceanspaces.com"
PACKAGENAME ?= "${BPN}-${MACHINE}-${PV}"
BOARD_DESIGN_URI = "http://fpga-server.local/downloads/fpga/${PACKAGENAME}.bit.xz;name=${MACHINE}"

PKGV = "${PV}"
S = "${WORKDIR}"
B = "${S}"

# Nothing to build
do_compile() {
    rm -f fpga.bit
    ln ${PACKAGENAME}.bit fpga.bit
}
