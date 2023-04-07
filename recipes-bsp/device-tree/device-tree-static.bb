SUMMARY = "BSP device tree"
DESCRIPTION = "Out-of-kernel BSP device trees"
SECTION = "bsp"
# the device trees from within the layer are licensed as MIT, kernel includes are GPL
LICENSE = "MIT & GPL-2.0-only"
LIC_FILES_CHKSUM = "\
	file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
	file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6 \
	"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit devicetree

# Device-trees are inherently board specific
BOARD_ARCH ??= "${MACHINE_ARCH}"
PACKAGE_ARCH = "${BOARD_ARCH}"

DEPENDS += "python3-dtc-native"

PROVIDES = "virtual/dtb"
# dont install this and Xilinx' device-tree at the same time
PROVIDES += "device-tree"
RPROVIDES:${PN} += "device-tree"
RREPLACES:${PN} = "device-tree"
RCONFLICTS:${PN} = "device-tree"

# Petalinux likes to have a "system.dtb" and a "system-top.dtb" somewhere.
SYSTEM_TOP_DTB ?= "${MACHINE}.dtb"
SYSTEM_DTB ?= "${SYSTEM_TOP_DTB}"

COMPATIBLE_MACHINE:xdpzu7 = ".*"
SRC_URI:xdpzu7 = " \
		file://zynqmp-topic-miamimp-xilinx-xdp.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-sd.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-usbhost.dts \
		file://zynqmp-topic-miamimp-xilinx-xdp-sd-usbhost.dts \
		"
SYSTEM_TOP_DTB:xdpzu7 = "zynqmp-topic-miamimp-xilinx-xdp.dtb"

COMPATIBLE_MACHINE:ttpzu9 = ".*"
SRC_URI:ttpzu9 = "\
	file://zynqmp-topic-miamiplusmp.dts \
	file://${MACHINE}.dts \
	"

COMPATIBLE_MACHINE:tdpzu9 = ".*"
SRC_URI:tdpzu9 = "\
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
SRC_URI:tdkzu6  = "${TDKZU_SOURCES}"
SRC_URI:tdkzu9  = "${TDKZU_SOURCES}"
SRC_URI:tdkzu15 = "${TDKZU_SOURCES}"
SYSTEM_TOP_DTB:tdkzu6 = "zynqmp-topic-miamimp-florida-gen.dtb"
SYSTEM_TOP_DTB:tdkzu9 = "zynqmp-topic-miamimp-florida-gen.dtb"
SYSTEM_TOP_DTB:tdkzu15 = "zynqmp-topic-miamimp-florida-gen.dtb"

TDKZ_SOURCES = "\
	file://topic-miami-florida-mio.dts \
	file://topic-miami-florida-mio.dtsi \
	file://topic-miami-florida.dtsi \
	file://topic-miami.dts \
	file://topic-miami.dtsi \
	"
COMPATIBLE_MACHINE:tdkz15 = ".*"
COMPATIBLE_MACHINE:tdkz30 = ".*"
SRC_URI:tdkz15 = "${TDKZ_SOURCES}"
SRC_URI:tdkz30 = "${TDKZ_SOURCES}"
SYSTEM_TOP_DTB:tdkz15 = "topic-miami.dtb"
SYSTEM_DTB:tdkz15 = "topic-miami-florida-mio.dtb"
SYSTEM_TOP_DTB:tdkz30 = "topic-miami.dtb"
SYSTEM_DTB:tdkz30 = "topic-miami-florida-mio.dtb"

TDKZL_SOURCES = "\
	file://${MACHINE}.dts \
	file://topic-miami-florida-mio.dtsi \
	file://topic-miamilite.dtsi \
	file://topic-miami.dtsi \
	"
COMPATIBLE_MACHINE:tdkz07 = ".*"
COMPATIBLE_MACHINE:tdkz10 = ".*"
SRC_URI:tdkz07 = "${TDKZL_SOURCES}"
SRC_URI:tdkz10 = "${TDKZL_SOURCES}"

# Some extra links to make petalinux happy
do_install:append() {
    ln -s ${SYSTEM_TOP_DTB} ${D}/boot/devicetree/system-top.dtb
}

do_deploy:append() {
    ln -s devicetree/${SYSTEM_DTB} ${DEPLOYDIR}/system.dtb
}
