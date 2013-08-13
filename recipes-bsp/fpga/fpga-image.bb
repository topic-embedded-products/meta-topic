DESCRIPTION = "FPGA bit image loader from userspace and tools"
# We don't need libc or gcc or whatever
INHIBIT_DEFAULT_DEPS = "1"
# But we do need Python on the build machine
DEPENDS = "python-native"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
# Package is machine specific
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Workaround: XPS fails to build when there's a "+" in the path.
SRCPV = "${@bb.fetch2.get_srcrev(d).replace('+','-')}"

BOARD_DESIGN_URI ?= "git://github.com/analogdevicesinc/fpgahdl_xilinx.git"
SRCREV = "0a90b0d42efa2c571e1dd3a038a00f974d6b3e5f"

BOARD_DESIGN_PATH ?= ""
BOARD_DESIGN_PATH_zedboard = "cf_adv7511_zed"
BOARD_DESIGN_PATH_zynq-zc702 = "cf_adv7511_zc702"
BOARD_DESIGN_PATH_zynq-zc706 = "cf_adv7511_zc706"

SRC_URI = "${BOARD_DESIGN_URI}"

S = "${WORKDIR}/git/${BOARD_DESIGN_PATH}"

inherit gitpkgv

PV = "5.${SRCPV}"
PR = "r0"
PKGV = "5.${GITPKGV}"

PACKAGES = "${PN}"

SRC_URI += "\
	file://fpga-bit-to-bin.py \
	file://init \
	"

inherit update-rc.d

# Set to start at 03, which is before modutils
# so you can autoload modules which use FPGA logic.
INITSCRIPT_NAME = "${PN}.sh"
INITSCRIPT_PARAMS = "start 03 S ."

do_compile() {
	export LM_LICENSE_FILE="${XILINX_LM_LICENSE_FILE}"
	source ${XILINX_TOOL_PATH}/ISE_DS/settings${XILINX_TOOL_ARCH}.sh
	xps -nw system.xmp << EOF
run bits
run exporttosdk
EOF
	python ${WORKDIR}/fpga-bit-to-bin.py --flip ${S}/implementation/system.bit ${WORKDIR}/fpga.bin
}

FILES_${PN} = "${sysconfdir} ${datadir}"
do_install() {
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}.sh
	install -d ${D}${datadir}
	install -m 644 ${WORKDIR}/fpga.bin ${D}${datadir}/fpga.bin
}

# Store the SDK files into the sysroot for other packages
SYSROOT_PREPROCESS_FUNCS += "fpga_sysroot_preprocess"
fpga_sysroot_preprocess() {
	install -d ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk
	cp -r ${S}/SDK/SDK_Export/hw/* ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk/
}
