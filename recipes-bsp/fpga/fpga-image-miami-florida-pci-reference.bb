SUMMARY = "FPGA bitstream for Miami Florida PCIe boards"
COMPATIBLE_MACHINE = "topic-miami-florida-pci"

# Downloads a precompiled bitstream from the TOPIC website. For now, just
# uses the same image as the GEN and MED boards.

require fpga-image.inc

# The PCIe board loads the logic in the bootloader
RRECOMMENDS_${PN} = ""

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}"
PV = "0"

BITSTREAM_FILENAME = "${PN}-${FPGA_FAMILY}.bit"
SRC_URI = "http://www.topicembeddedproducts.com/support/download/public/${BITSTREAM_FILENAME};name=${FPGA_FAMILY}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[xc7z030.md5sum] = "0b3846fbd9b23617a421655108a0c790"
SRC_URI[xc7z030.sha256sum] = "acea507b7dd21d220cfd7666cba96f74e004c8ecbe1a2320181207fdc4514e30"
SRC_URI[xc7z015.md5sum] = "cb16d38453129d791b066642ce271203"
SRC_URI[xc7z015.sha256sum] = "5307d7600cc91cc35ce80ef9104f71510a4fa2c4f3baacde74d66220775b4f32"
