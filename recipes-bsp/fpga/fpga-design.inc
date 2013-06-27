LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
# Package is machine specific
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Workaround: XPS fails to build when there's a "+" in the path.
SRCPV = "${@bb.fetch2.get_srcrev(d).replace('+','-')}"

BOARD_DESIGN_URI ?= "git://github.com/analogdevicesinc/fpgahdl_xilinx.git"
SRCREV_board = "13694820ae59af30b898d4804b808ccaa5ff6842"

BOARD_DESIGN_PATH ?= ""
BOARD_DESIGN_PATH_zedboard = "cf_adv7511_zed"
BOARD_DESIGN_PATH_zynq-zc702 = "cf_adv7511_zc702"
BOARD_DESIGN_PATH_zynq-zc706 = "cf_adv7511_zc706"

SRC_URI = "${BOARD_DESIGN_URI};name=board"

S = "${WORKDIR}/git/${BOARD_DESIGN_PATH}"
