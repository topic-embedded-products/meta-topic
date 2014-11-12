SUMMARY = "FPGA bitstream for Miami Florida GEN boards"

# Downloads a precompiled bitstream from the TOPIC website

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}/src"
PV = "v2r2"

BITSTREAM_FILENAME ?= ""
BITSTREAM_FILENAME_xc7z015 = "flo-med-7z15-ref-${PV}.bit"
BITSTREAM_FILENAME_xc7z030 = "flo-med-7z30-ref-${PV}.bit"
SRC_URI = "http://www.topicembeddedproducts.com/support/download/public/${BITSTREAM_FILENAME};name=${FPGA_FAMILY}"

# Copy static bitstream to the source dir.
do_compile() {
	cp ${WORKDIR}/${BITSTREAM_FILENAME} ${S}/fpga.bit
}

SRC_URI[xc7z015.md5sum] = "9b06af813020c611823a878f84f91cb8"
SRC_URI[xc7z015.sha256sum] = "44130149d87f86c2b44ceb0ae8a65d7a3f0f0ebd387284bc41db01b263fe5cbd"
SRC_URI[xc7z030.md5sum] = "abfceda472382fcfc43e3ec679f3d100"
SRC_URI[xc7z030.sha256sum] = "250422bb04f58551f53ab5d424615772ec0df1c7aa589173e266d8742dbb5546"
