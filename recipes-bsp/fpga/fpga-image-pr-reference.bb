SUMMARY = "FPGA image for Partial Reconfiguration demo"
XILINX_VIVADO_VERSION = "2019.2"
require recipes-bsp/fpga/fpga-image.inc
LICENSE = "CLOSED"

# Only for Florida gen with a ZU9 or 7030
COMPATIBLE_MACHINE = "^tdkzu9|^tdkz30"

PV = "7"

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

PACKAGES =+ "${PN}-partials"
RPROVIDES_${PN}-partials = "pr-demo-partials"
FILES_${PN}-partials = "${FPGA_BITSTREAM_PATH}"

SRC_URI[tdkzu9.md5sum] = "bf85875460f3b32536ebf39075fd8896"
SRC_URI[tdkz30.md5sum] = "7dee9ff9c4e7176fd7df2158cb77b5c5"

