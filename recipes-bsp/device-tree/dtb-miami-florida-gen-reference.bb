SUMMARY = "Devicetree overlay for Topic Miami Florida GEN board"
SECTION = "bsp"

# the device trees from within the layer are licensed as MIT, kernel includes are GPL
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit devicetree

RRECOMMENDS_${PN} = "fpga-firmware-load"

BITSTREAM = "Unsupported-board-for-overlay"
BITSTREAM_topic-miami = "fpga-image-miami-florida-gen-reference"
BITSTREAM_topic-miamimp = "fpga-image-miamimp-florida-gen-reference"
RDEPENDS_${PN} += "${BITSTREAM}"

COMPATIBLE_MACHINE = "^topic-miami-florida-gen|^topic-miamimp-florida-gen"
SRC_URI = "file://pl.dts"

# devicetree.bbclass installs into /boot/devicetree while we want to install
# into /lib/firmware
FILES_${PN} = "/lib/firmware"
do_install() {
    for DTB_FILE in `ls *.dtbo`; do
        install -Dm 0644 ${B}/${DTB_FILE} ${D}/lib/firmware/${DTB_FILE}
    done
}
