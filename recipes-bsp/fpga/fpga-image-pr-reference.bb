SUMMARY = "FPGA image for Partial Reconfiguration demo"
XILINX_VIVADO_VERSION = "2019.2"
require recipes-bsp/fpga/fpga-image.inc
LICENSE = "CLOSED"

# Only for Florida gen with a ZU6, ZU9, ZU15 or 7030
COMPATIBLE_MACHINE = "^tdkzu|^tdkz30"

PV = "38+8838cd5"
PV_tdkzu15 = "36+02419a0"

BOARD_DESIGN_PATH = "${BPN}-${MACHINE}"
TOPICDOWNLOADS_URI ?= "https://topic-downloads.fra1.digitaloceanspaces.com"
BOARD_DESIGN_URI = "${TOPICDOWNLOADS_URI}/files/${BOARD_DESIGN_PATH}-${PV}.tar.xz;name=${MACHINE}"

PKGV = "${PV}"
S = "${WORKDIR}/${BOARD_DESIGN_PATH}"
B = "${S}"

# Nothing to build
do_compile() {
    true
}

# Create symlinks to be compatible with PR demo
do_install_append() {
    for f in contrast grayscale treshold
    do
        if [ -d ${D}${FPGA_BITSTREAM_PATH}/bitstreams/yuv_$f ]
        then
            ln -s yuv_$f ${D}${FPGA_BITSTREAM_PATH}/bitstreams/$f
        fi
    done
    if [ -f ${S}/static.dcp ]
    then
        install -m 644 ${S}/static.dcp ${D}${FPGA_BITSTREAM_PATH}/bitstreams/${BPN}-${MACHINE}.dcp
    fi
}

# Package partials separately
PACKAGES =+ "${PN}-partials"
RPROVIDES_${PN}-partials = "pr-demo-partials"
FILES_${PN}-partials = "${FPGA_BITSTREAM_PATH}/bitstreams ${FPGA_BITSTREAM_PATH}/bitstreams.*"

# Any design checkpoint goes into the "dev" package
PACKAGES =+ "${PN}-dev"
FILES_${PN}-dev = "${FPGA_BITSTREAM_PATH}/bitstreams*/${BPN}-${MACHINE}.dcp"

SRC_URI[tdkzu6.md5sum] = "e06f16961b8e5cc21ea0dcc55d7251a5"
SRC_URI[tdkzu9.md5sum] = "e17e5efc3745062d0cdc2bf3e6004820"
SRC_URI[tdkzu15.md5sum] = "58ae0ad0ae1473a552ff7ae1511f23e9"
SRC_URI[tdkz30.md5sum] = "cd6f694c1715ad39c0c14a773946ff73"
