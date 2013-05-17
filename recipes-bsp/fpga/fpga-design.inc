LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
# Package is machine specific
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Workaround: XPS fails to build when there's a "+" in the path.
SRCPV = "${@bb.fetch2.get_srcrev(d).replace('+','-')}"

S="${WORKDIR}/git"

BOARD_DESIGN_URI ?= ""

BOARD_DESIGN_URI_zedboard = "git://github.com/milosoftware/zynq-zedboard-logic.git"
SRCREV_board-zedboard = "63e317164357bf87bba7dd0adc429d4c91a78cff"

BOARD_DESIGN_URI_zynq-zc702 = "git://github.com/milosoftware/zynq-zc702-logic.git"
SRCREV_board-zynq-zc702 = "8095d780aab8d110298d655852f8f31053f5f124"

SRC_URI = "\
	${BOARD_DESIGN_URI};name=board-${MACHINE} \
	http://wiki.analog.com/_media/resources/fpga/xilinx/kc705/cf_adv7511_zed_edk_14_4_2013_02_05.tar.gz;name=vhdl \
	"

SRC_URI[vhdl.md5sum] = "7bfa99f576e0f4a40d6fc30c2e111470"
SRC_URI[vhdl.sha256sum] = "4dea2dc0c32da8556c88e299350964816b67593d23eb590fb62393ce62229f09"

