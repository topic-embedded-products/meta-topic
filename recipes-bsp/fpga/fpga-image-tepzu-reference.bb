require fpga-image-download.inc

DEPENDS += "unzip-native"

# XSA is being used to download the bitstream on this particular board
TOPIC_XSA_DESIGN_VERSION = "280+38e5dd1"
TOPIC_XSA_DESIGN_NAME = "fpga-hardware-${MACHINE}-reference-${TOPIC_XSA_DESIGN_VERSION}.xsa"
TOPIC_XSA_DESIGN_SHA256SUM = "40d00c0ada816b7589d54eebfeb716db8a03ad8bf0228b8e8b25e3573d6d3dd9"
# This is also the filename of the bitstream inside the XSA file
FPGA_PART ?= "xczu9eg-ffvb1156-1-i"

# Use bitstream from xsa
PV = "${TOPIC_XSA_DESIGN_VERSION}"
SRC_URI[tepzu9.sha256sum] = "${TOPIC_XSA_DESIGN_SHA256SUM}"

BOARD_DESIGN_URI = "${TOPICDOWNLOADS_URI}/files/${TOPIC_XSA_DESIGN_NAME}.xz;name=${MACHINE}"

# We need unzip during compile
do_compile[depends] += "unzip-native:do_populate_sysroot"
# Nothing to build, just unpack the XSA to get the bitstream
do_compile() {
   unzip ${S}/${TOPIC_XSA_DESIGN_NAME} ${FPGA_PART}.bit
   cp -f -l ${FPGA_PART}.bit fpga.bit
}
