FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# We want overlay support, so sompile everything with "symbols".
DTC_BFLAGS_append = " -@"

COMPATIBLE_MACHINE_xdpzu7 = ".*"
SRC_URI_append_xdpzu7 = " \
		file://zynqmp-topic-miamimp-xilinx-xdp.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-sd.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-usbhost.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-sd-usbhost.dts \
		"

COMPATIBLE_MACHINE_ttpzu9 = ".*"
SRC_URI_append_ttpzu9 = "\
	file://zynqmp-topic-miamiplusmp.dts \
	file://${MACHINE}.dts \
	"

COMPATIBLE_MACHINE_tdpzu9 = ".*"
SRC_URI_append_tdpzu9 = "\
	file://zynqmp-topic-miamiplusmp.dts \
	file://zynqmp-remoteproc-r5.dtsi \
	file://${MACHINE}.dts \
	file://${MACHINE}-wifi.dts \
	"

TDKZU_SOURCES = "\
	file://zynqmp-topic-miamimp-florida-gen.dts \
	file://zynqmp-topic-miamimp.dts \
	"
COMPATIBLE_MACHINE_tdkzu6  = ".*"
COMPATIBLE_MACHINE_tdkzu9  = ".*"
COMPATIBLE_MACHINE_tdkzu15 = ".*"
SRC_URI_append_tdkzu6  = "${TDKZU_SOURCES}"
SRC_URI_append_tdkzu9  = "${TDKZU_SOURCES}"
SRC_URI_append_tdkzu15 = "${TDKZU_SOURCES}"

TDKZ_SOURCES = "\
	file://topic-miami-florida-mio.dts \
	file://topic-miami-florida-mio.dtsi \
	file://topic-miami-florida.dtsi \
	file://topic-miami.dts \
	file://topic-miami.dtsi \
	"
COMPATIBLE_MACHINE_tdkz15 = ".*"
COMPATIBLE_MACHINE_tdkz30 = ".*"
SRC_URI_append_tdkz15 = "${TDKZ_SOURCES}"
SRC_URI_append_tdkz30 = "${TDKZ_SOURCES}"
