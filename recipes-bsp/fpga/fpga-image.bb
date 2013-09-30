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

PV = "5.${SRCPV}"
PR = "r0"

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

VIVADO_EXTRA_COMMANDS ?= ""

do_compile() {
	export LM_LICENSE_FILE="${XILINX_LM_LICENSE_FILE}"
	if [ -f generate_bitstreams.sh ]
	then
		echo "Executing generate_bitstreams.sh"
		source ${XILINX_VIVADO_PATH}/settings${XILINX_TOOL_ARCH}.sh
		./generate_bitstreams.sh
	else
		for iseprojf in *.xmp
		do
			if [ -f "${iseprojf}" ]
			then
				iseproj=`basename ${iseprojf} .xmp`
				source ${XILINX_TOOL_PATH}/ISE_DS/settings${XILINX_TOOL_ARCH}.sh
				xps -nw ${iseprojf} << EOF
run bits
run exporttosdk
EOF
				python ${WORKDIR}/fpga-bit-to-bin.py --flip ${S}/implementation/${iseproj}.bit ${WORKDIR}/fpga.bin
			fi
		done
		for vivadoprojf in *.xpr
		do
			if [ -f "${vivadoprojf}" ]
			then
			vivadoproj=`basename "${vivadoprojf}" .xpr`
				source ${XILINX_VIVADO_PATH}/settings${XILINX_TOOL_ARCH}.sh
				${XILINX_VIVADO_PATH}/bin/vivado -mode tcl << EOF
open_project {${vivadoprojf}}
reset_target {all} [get_ips]
generate_target {all} [get_ips]
reset_run impl_1
reset_run synth_1
launch_runs synth_1
wait_on_run synth_1
launch_runs impl_1
wait_on_run impl_1
launch_runs impl_1 -to_step write_bitstream
wait_on_run impl_1
close_project
exit
EOF
				python ${WORKDIR}/fpga-bit-to-bin.py --flip ${S}/${vivadoproj}.runs/impl_1/${vivadoproj}*.bit ${WORKDIR}/fpga.bin
			fi
		done
	fi
}

FILES_${PN} = "${sysconfdir} ${datadir}"
do_install() {
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}.sh
	install -d ${D}${datadir}
	install -m 644 ${WORKDIR}/fpga.bin ${D}${datadir}/fpga.bin
}

