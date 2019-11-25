FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# We want overlay support, so sompile everything with "symbols".
DTC_BFLAGS_append = " -@"

COMPATIBLE_MACHINE_xdpzu7 = ".*"
SRC_URI_append_xdpzu7 = " \
		file://zynqmp-topic-miamimp-xilinx-xdp.dts \
		"
