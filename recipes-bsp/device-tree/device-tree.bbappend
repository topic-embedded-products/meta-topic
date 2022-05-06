FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# We want overlay support, so sompile everything with "symbols".
DTC_BFLAGS:append = " -@"

COMPATIBLE_MACHINE:tdkz10 = ".*"
SRC_URI:append:tdkz10 = "\
	file://topic-miami-florida-mio.dtsi \
	file://topic-miami-florida.dtsi \
	file://topic-miami.dtsi \
	file://topic-miamilite.dtsi \
	file://${MACHINE}.dts \
	"

COMPATIBLE_MACHINE:xdpzu7 = ".*"
SRC_URI:append:xdpzu7 = " \
		file://zynqmp-topic-miamimp-xilinx-xdp.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-sd.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-usbhost.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-sd-usbhost.dts \
		"

COMPATIBLE_MACHINE:ttpzu9 = ".*"
SRC_URI:append:ttpzu9 = "\
	file://zynqmp-topic-miamiplusmp.dts \
	file://${MACHINE}.dts \
	"

COMPATIBLE_MACHINE:tdpzu9 = ".*"
SRC_URI:append:tdpzu9 = "\
	file://zynqmp-topic-miamiplusmp.dts \
	file://${MACHINE}.dts \
	file://${MACHINE}-v1r1.dts \
	file://${MACHINE}-v1r1-wifi.dts \
	"

TDKZU_SOURCES = "\
	file://zynqmp-topic-miamimp-florida-gen.dts \
	file://zynqmp-topic-miamimp.dts \
	"
COMPATIBLE_MACHINE:tdkzu6  = ".*"
COMPATIBLE_MACHINE:tdkzu9  = ".*"
COMPATIBLE_MACHINE:tdkzu15 = ".*"
SRC_URI:append:tdkzu6  = "${TDKZU_SOURCES}"
SRC_URI:append:tdkzu9  = "${TDKZU_SOURCES}"
SRC_URI:append:tdkzu15 = "${TDKZU_SOURCES}"

TDKZ_SOURCES = "\
	file://topic-miami-florida-mio.dts \
	file://topic-miami-florida-mio.dtsi \
	file://topic-miami-florida.dtsi \
	file://topic-miami.dts \
	file://topic-miami.dtsi \
	"
COMPATIBLE_MACHINE:tdkz15 = ".*"
COMPATIBLE_MACHINE:tdkz30 = ".*"
SRC_URI:append:tdkz15 = "${TDKZ_SOURCES}"
SRC_URI:append:tdkz30 = "${TDKZ_SOURCES}"

# Remove the check that requires some obsure variables to point to some files
def check_devicetree_variables(d):
    pass
