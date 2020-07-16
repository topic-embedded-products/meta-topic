FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# We want overlay support, so sompile everything with "symbols".
DTC_BFLAGS_append = " -@"

COMPATIBLE_MACHINE_xdpzu7 = ".*"
SRC_URI_append_xdpzu7 = " \
		file://zynqmp-topic-miamimp-xilinx-xdp.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-usbhost.dts \
		"

COMPATIBLE_MACHINE_ttpzu9 = ".*"
SRC_URI_append_ttpzu9 = " file://${MACHINE}.dts"

COMPATIBLE_MACHINE_tdpzu9 = ".*"
SRC_URI_append_tdpzu9 = " file://${MACHINE}.dts"
