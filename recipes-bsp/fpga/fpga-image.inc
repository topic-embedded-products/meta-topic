DESCRIPTION = "FPGA bitstream image from userspace and tools. Requires \
Vivado or XPS on the build host to build."
# We don't need libc or gcc or whatever
INHIBIT_DEFAULT_DEPS = "1"
# Need the bitstream converter on the build machine
DEPENDS = "fpga-bit-to-bin-native"
# Recommend bitstream to be loaded at boot
RRECOMMENDS:${PN} = "${@bb.utils.contains("MACHINE_FEATURES", "fpgamgr", "fpga-firmware-load", "fpga-image-load", d)}"
# Package is machine specific
PACKAGE_ARCH = "${MACHINE_ARCH}"

# By default, installs into /usr/share, but when using the fpga manager you
# would want this to be /lib/firmware
FPGA_STATIC_BITSTREAM_PATH ?= "${@bb.utils.contains("MACHINE_FEATURES", "fpgamgr", "/lib/firmware", "${datadir}", d)}"
# Where you want to put the partials depends on what you want to do with them.
# Dyplo likes them to be in /usr/share so that remains the default for all platforms
FPGA_BITSTREAM_PATH ?= "${datadir}"

# Tool paths and versions may differ, the output should be the same, so
# do not take these into account.
do_compile[vardepsexclude] += "XILINX_TOOL_PATH XILINX_LM_LICENSE_FILE XILINX_TOOL_ARCH XILINX_VIVADO_PATH"
do_convert_bitstreams[vardepsexclude] += "WORKDIR WORKDIRBIN"

# Required parameters
BOARD_DESIGN_URI ?= ""
BOARD_DESIGN_PATH ?= ""

SRC_URI = "${BOARD_DESIGN_URI}"
S = "${WORKDIR}/git${@'${BOARD_DESIGN_PATH}' and '/${BOARD_DESIGN_PATH}' or ''}"


PACKAGES = "${PN}"

# Multiple bitstream providers may exist for a single machine, so use
# update-alternatives to keep them apart
inherit update-alternatives
ALTERNATIVE:${PN} = "fpga.bin bitstreams"
ALTERNATIVE_LINK_NAME[fpga.bin] = "${FPGA_STATIC_BITSTREAM_PATH}/fpga.bin"
ALTERNATIVE_TARGET[fpga.bin] = "${FPGA_STATIC_BITSTREAM_PATH}/fpga.bin"
ALTERNATIVE_LINK_NAME[bitstreams] = "${FPGA_BITSTREAM_PATH}/bitstreams"
ALTERNATIVE_TARGET[bitstreams] = "${FPGA_BITSTREAM_PATH}/bitstreams"

do_compile() {
	export LM_LICENSE_FILE="${XILINX_LM_LICENSE_FILE}"
	export FPGA_FAMILY="${FPGA_FAMILY}"
	if [ -f generate_bitstreams.sh ]
	then
		if [ -f fpga.bit ]
		then
			rm fpga.bit
		fi
		echo "Executing generate_bitstreams.sh"
		source ${XILINX_VIVADO_PATH}/settings${XILINX_TOOL_ARCH}.sh
		source ./generate_bitstreams.sh
		test -f fpga.bit
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
				test -f ${S}/implementation/${iseproj}.bit
				cp ${S}/implementation/${iseproj}.bit fpga.bit
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
				test -f ${S}/${vivadoproj}.runs/impl_1/${vivadoproj}*.bit
				cp ${S}/${vivadoproj}.runs/impl_1/${vivadoproj}*.bit fpga.bit
			fi
		done
	fi
}

# Where to store the "bin" files after conversion
WORKDIRBIN = "${WORKDIR}/converted-bin"
# Postcondition for do_compile must be that there's a fpga.bit
# bitstream to be loaded at board startup, and optionally there can be
# partial bitstreams in a bitstreams directory.
do_convert_bitstreams() {
	rm -rf "${WORKDIRBIN}"
	mkdir -p "${WORKDIRBIN}"

	# Convert static bitfile
	fpga-bit-to-bin.py --flip fpga.bit ${WORKDIRBIN}/fpga.bin

	# Create symbolic link to build/bitstreams
	if [ ! -d bitstreams ]
	then
		if [ -d build/bitstreams ]
		then
			ln -s build/bitstreams bitstreams
		fi
	fi

	# Convert all partial bitfiles present in (build/)bitstreams
	if [ -d bitstreams ]
	then
		for path in bitstreams/*
		do
			if [ -d "${path}" ]
			then
				mkdir -p "${WORKDIRBIN}/${path}"
				for p in ${path}/*.bit
				do
					fpga-bit-to-bin.py --flip "${p}" "${WORKDIRBIN}/${p}.bin"
				done
			fi
		done
	fi
}
do_convert_bitstreams[dirs] = "${B}"

FILES:${PN} = "${FPGA_BITSTREAM_PATH} ${FPGA_STATIC_BITSTREAM_PATH}"
do_install() {
	install -d ${D}${FPGA_BITSTREAM_PATH}
	install -d ${D}${FPGA_STATIC_BITSTREAM_PATH}
	install -m 644 ${WORKDIRBIN}/fpga.bin ${D}${FPGA_STATIC_BITSTREAM_PATH}/fpga.bin
	install -d ${D}${FPGA_BITSTREAM_PATH}/bitstreams
	if [ -d ${WORKDIRBIN}/bitstreams ]
	then
		cp -r ${WORKDIRBIN}/bitstreams/* ${D}${FPGA_BITSTREAM_PATH}/bitstreams/
	fi
}

addtask convert_bitstreams after do_compile before do_install

# Prevent bitstreams ending up in sysroot.
sysroot_stage_dirs:append() {
	rm -rf $to${FPGA_STATIC_BITSTREAM_PATH}/fpga.bin $to${FPGA_BITSTREAM_PATH}/bitstreams
}
