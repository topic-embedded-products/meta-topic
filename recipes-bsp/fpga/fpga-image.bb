DESCRIPTION = "FPGA bit image loader from userspace and tools"
DEPENDS = "python-native"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
# Package is machine specific
PACKAGE_ARCH = "${MACHINE}"

# Workaround: XPS fails to build when there's a "+" in the path.
PV = "1.${@d.getVar('SRCPV',True).replace('+','-')}"
PR = "r0"

PACKAGES = "${PN}"

S="${WORKDIR}/git"

BOARD_DESIGN_URI ?= ""

BOARD_DESIGN_URI_zedboard = "git://github.com/milosoftware/zynq-zedboard-logic.git"
SRCREV_board-zedboard = "63e317164357bf87bba7dd0adc429d4c91a78cff"

BOARD_DESIGN_URI_zynq-zc702 = "git://github.com/milosoftware/zynq-zc702-logic.git"
SRCREV_board-zynq-zc702 = "8095d780aab8d110298d655852f8f31053f5f124"

SRC_URI = "\
	${BOARD_DESIGN_URI};name=board-${MACHINE} \
	http://wiki.analog.com/_media/resources/fpga/xilinx/kc705/cf_adv7511_zed_edk_14_4_2013_02_05.tar.gz;name=vhdl \
	file://fpga-bit-to-bin.py \
	file://init \
	"

SRC_URI[vhdl.md5sum] = "7bfa99f576e0f4a40d6fc30c2e111470"
SRC_URI[vhdl.sha256sum] = "4dea2dc0c32da8556c88e299350964816b67593d23eb590fb62393ce62229f09"

inherit update-rc.d

# Set to start at 03, which is before modutils
# so you can autoload modules which use FPGA logic.
INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "start 03 S ."

# Use the "32" or "64" toolchain
XILINX_TOOL_ARCH ?= "64"
# The base install directory for XPS and other tools
XILINX_TOOL_PATH ?= "/opt/Xilinx/14.4"
XILINX_LM_LICENSE_FILE ?= "${XILINX_TOOL_PATH}/ISE_DS/ISE/coregen/core_licenses/Xilinx.lic"

do_compile() {
	export LM_LICENSE_FILE="${XILINX_LM_LICENSE_FILE}"
	source ${XILINX_TOOL_PATH}/ISE_DS/settings${XILINX_TOOL_ARCH}.sh
	xps -nw system.xmp << EOF
run bits
EOF
	python ${WORKDIR}/fpga-bit-to-bin.py --flip ${S}/implementation/system.bit ${WORKDIR}/fpga.bin
}

do_install() {
	install -d ${D}/etc
	install -d ${D}/etc/init.d
	install -m 755 ${WORKDIR}/init ${D}/etc/init.d/${PN}
	install -m 644 ${WORKDIR}/fpga.bin ${D}/etc/fpga.bin
}

