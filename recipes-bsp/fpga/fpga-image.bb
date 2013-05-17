DESCRIPTION = "FPGA bit image loader from userspace and tools"
# We don't need libc or gcc or whatever
INHIBIT_DEFAULT_DEPS = "1"
# But we do need Python on the build machine
DEPENDS = "python-native"

require fpga-design.inc

PV = "3.${SRCPV}"
PR = "r0"

PACKAGES = "${PN}"

S="${WORKDIR}/git"

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
