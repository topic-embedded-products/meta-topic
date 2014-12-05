DESCRIPTION = "FPGA bitstream image from userspace and tools. Requires \
Vivado or XPS on the build host to build."
# We don't need libc or gcc or whatever
INHIBIT_DEFAULT_DEPS = "1"
# Need the bitstream converter on the build machine
DEPENDS = "fpga-bit-to-bin-native ${FPGA_STATIC_PN}"
# Package is FPGA logic specific, usually not related to the machine.
PACKAGE_ARCH = "${FPGA_FAMILY_ARCH}"

# Tool paths and versions may differ, the output should be the same, so
# do not take these into account.
do_compile[vardepsexclude] += "XILINX_TOOL_PATH XILINX_LM_LICENSE_FILE XILINX_TOOL_ARCH XILINX_VIVADO_PATH"
do_convert_bitstreams[vardepsexclude] += "WORKDIR"

# Required parameters
BOARD_DESIGN_URI ?= ""
BOARD_DESIGN_PATH ?= ""

SRC_URI = "${BOARD_DESIGN_URI}"
S = "${WORKDIR}/git/${BOARD_DESIGN_PATH}"

PACKAGES = "${PN}"

FPGA_STATIC_PN ?= "${MACHINE_FPGA_BITSTREAM}"
FPGA_STATIC_EXPORTS ?= "${STAGING_DATADIR}/${FPGA_STATIC_PN}"
FPGA_STATIC_CHECKPOINT ?= "${FPGA_STATIC_EXPORTS}/top_impl_static.dcp"

do_compile() {
	export LM_LICENSE_FILE="${XILINX_LM_LICENSE_FILE}"
	export FPGA_FAMILY="${FPGA_FAMILY}"
	export FPGA_STATIC_EXPORTS="${FPGA_STATIC_EXPORTS}"
	export FPGA_STATIC_CHECKPOINT="${FPGA_STATIC_CHECKPOINT}"
	if [ -f generate_partial.sh ]
	then
		echo "Executing generate_partial.sh"
		source ${XILINX_VIVADO_PATH}/settings${XILINX_TOOL_ARCH}.sh
		source ./generate_partial.sh
	fi
}

do_convert_bitstreams() {
	if [ -d bitstreams ]
	then
		for path in bitstreams/*
		do
			if [ -d "${path}" ]
			then
				mkdir -p "${WORKDIR}/${path}"
				for p in ${path}/*partial.bit
				do
					fpga-bit-to-bin.py --flip "${p}" "${WORKDIR}/${p}.bin"
				done
			fi
		done
	fi
}

FILES_${PN} = "${datadir}"
do_install() {
	install -d ${D}${datadir}/bitstreams.${FPGA_STATIC_PN}
	if [ -d ${WORKDIR}/bitstreams ]
	then
		cp -r ${WORKDIR}/bitstreams/* ${D}${datadir}/bitstreams.${FPGA_STATIC_PN}
	fi
}

addtask convert_bitstreams after do_compile before do_install

# Prevent bitstreams ending up in sysroot.
sysroot_stage_dirs_append() {
	rm -rf $to${datadir}/bitstreams.${FPGA_STATIC_PN}
}
