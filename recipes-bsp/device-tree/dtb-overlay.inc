# Overlay template. Actual recipe is expected to provide "pl.dts" file
# and set the BITSTREAM variable to something machine-specific

SUMMARY = "Devicetree overlay"
SECTION = "bsp"

# the device trees from within the layer are licensed as MIT, kernel includes are GPL
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit devicetree

# We do not provide virtual/dtb, there may be more overlays
PROVIDES = ""

RRECOMMENDS:${PN} = "fpga-firmware-load"

BITSTREAM = "Unsupported-board-for-overlay"
RDEPENDS:${PN} += "${BITSTREAM}"

SRC_URI = "file://pl.dts"

do_replacevars() {
    sed -i 's/@BITSTREAM@/${BITSTREAM}/g' ${S}/pl.dts
}
addtask replacevars after do_patch before do_compile

# devicetree.bbclass installs into /boot/devicetree while we want to install
# into /lib/firmware
FILES:${PN} = "/lib/firmware"
do_install() {
    for DTB_FILE in `ls *.dtbo`; do
        install -Dm 0644 ${B}/${DTB_FILE} ${D}/lib/firmware/${DTB_FILE}
    done
}

# Do not deploy, there may be multiple overlay providers, and the overlay is
# not needed in the "images" directory anyway
do_deploy[noexec] = "1"
do_deploy() {
    true
}
