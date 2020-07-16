SUMMARY = "Devicetree overlay for ttpzu9 board"
SECTION = "bsp"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit devicetree

# We do not provide virtual/dtb, there may be more overlays
PROVIDES = ""

RRECOMMENDS_${PN} = "fpga-firmware-load"

BITSTREAM = "Unsupported-board-for-overlay"
BITSTREAM_ttpzu9 = "fpga-image-ttp-reference"

RDEPENDS_${PN} += "${BITSTREAM}"
RRECOMMENDS_${PN} += "kernel-module-i2c-xiic kernel-module-uio-dmem-genirq"

COMPATIBLE_MACHINE = "^ttp"
SRC_URI = "file://pl.dts"

# devicetree.bbclass installs into /boot/devicetree while we want to install
# into /lib/firmware
FILES_${PN} = "/lib/firmware"
do_install() {
    for DTB_FILE in `ls *.dtbo`; do
        install -Dm 0644 ${B}/${DTB_FILE} ${D}/lib/firmware/${DTB_FILE}
    done
}
