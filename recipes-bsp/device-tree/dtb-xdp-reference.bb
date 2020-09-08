SUMMARY = "Devicetree overlay for XDP reference FPGA image"
SECTION = "bsp"

# the device trees from within the layer are licensed as MIT, kernel includes are GPL
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PV = "2"

inherit devicetree

# We do not provide virtual/dtb, there may be more overlays
PROVIDES = ""

RRECOMMENDS_${PN} = "fpga-firmware-load xdp-hwdetect"

BITSTREAM = "Unsupported-board-for-overlay"
BITSTREAM_xdpzu7 = "fpga-image-pr-reference"
RDEPENDS_${PN} += "${BITSTREAM}"

COMPATIBLE_MACHINE = "^xdp"
SRC_URI = "file://pl.dts file://xdp-eio.dts"

do_replacevars() {
    sed -i 's/@BITSTREAM@/${BITSTREAM}/g' ${S}/pl.dts
}
addtask replacevars after do_patch before do_compile

# devicetree.bbclass installs into /boot/devicetree while we want to install
# into /lib/firmware
FILES_${PN} = "/lib/firmware"
do_install() {
    for DTB_FILE in `ls *.dtbo`; do
        install -Dm 0644 ${B}/${DTB_FILE} ${D}/lib/firmware/${DTB_FILE}
    done
}
