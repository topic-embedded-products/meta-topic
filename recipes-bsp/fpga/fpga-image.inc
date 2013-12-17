DESCRIPTION = "FPGA bitstream image from userspace and tools. Requires \
Vivado or XPS on the build host to build."
# We don't need libc or gcc or whatever
INHIBIT_DEFAULT_DEPS = "1"
# Need the bitstream converter on the build machine
DEPENDS = "fpga-bit-to-bin-native"
# Recommend bitstream to be loaded at boot
RRECOMMENDS_${PN} = "fpga-image-load"
# Package is machine specific
PACKAGE_ARCH = "${MACHINE_ARCH}"
# Workaround: XPS fails to build when there's a "+" in the path.
SRCPV = "${@bb.fetch2.get_srcrev(d).replace('+','-')}"

BOARD_DESIGN_URI ?= "git://github.com/analogdevicesinc/fpgahdl_xilinx.git"
SRCREV = "0a90b0d42efa2c571e1dd3a038a00f974d6b3e5f"

BOARD_DESIGN_PATH ?= ""
SRC_URI = "${BOARD_DESIGN_URI}"
S = "${WORKDIR}/git/${BOARD_DESIGN_PATH}"

PACKAGES = "${PN}"

VIVADO_EXTRA_COMMANDS ?= ""

do_compile() {
	export LM_LICENSE_FILE="${XILINX_LM_LICENSE_FILE}"
	if [ -f generate_bitstreams.sh ]
	then
		echo "Executing generate_bitstreams.sh"
		source ${XILINX_VIVADO_PATH}/settings${XILINX_TOOL_ARCH}.sh
		./generate_bitstreams.sh
		test -f ${WORKDIR}/fpga.bin
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
				fpga-bit-to-bin.py --flip ${S}/implementation/${iseproj}.bit ${WORKDIR}/fpga.bin
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
				fpga-bit-to-bin.py --flip ${S}/${vivadoproj}.runs/impl_1/${vivadoproj}*.bit ${WORKDIR}/fpga.bin
			fi
		done
	fi
}

FILES_${PN} = "${datadir}"
do_install() {
	install -d ${D}${datadir}
	install -m 644 ${WORKDIR}/fpga.bin ${D}${datadir}/fpga.bin
	if [ -d ${WORKDIR}/bitstreams ]
	then
		install -d ${D}${datadir}/bitstreams
		cp -r ${WORKDIR}/bitstreams/* ${D}${datadir}/bitstreams/
	fi
}
