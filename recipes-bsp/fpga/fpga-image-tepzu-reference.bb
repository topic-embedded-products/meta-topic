require fpga-image-download.inc

DEPENDS += "unzip-native"

# Use bitstream from xsa
PV = "${TOPIC_XSA_DESIGN_VERSION}"
SRC_URI[tepzu9.sha256sum] = "${TOPIC_XSA_DESIGN_SHA256SUM}"

BOARD_DESIGN_URI = "${TOPICDOWNLOADS_URI}/files/${TOPIC_XSA_DESIGN_NAME}.xz;name=${MACHINE}"

# We need unzip during compile
do_compile[depends] += "unzip-native:do_populate_sysroot"
# Nothing to build, just unpack the XSA to get the bitstream
do_compile() {
   unzip ${WORKDIR}/${TOPIC_XSA_DESIGN_NAME} ${FPGA_PART}.bit
   cp -f -l ${FPGA_PART}.bit fpga.bit
}
