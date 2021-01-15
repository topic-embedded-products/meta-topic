SUMMARY = "FPGA bitstream for tdpzu9"
COMPATIBLE_MACHINE = "^tdkz10"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

PV = "173+7916f3b"

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

SRC_URI[tdkz10.sha256sum] = "8000d24547d6d7254e7f5b39ef33d1809c7503d1f13ea9cf575abfc3aad4229d"
