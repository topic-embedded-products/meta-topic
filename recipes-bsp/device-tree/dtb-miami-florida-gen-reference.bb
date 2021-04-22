require dtb-overlay.inc

SUMMARY = "Devicetree overlay for Topic Miami Florida GEN board"

BITSTREAM_topic-miami = "fpga-image-pr-reference"
BITSTREAM_topic-miamimp = "fpga-image-pr-reference"

COMPATIBLE_MACHINE = "^tdkz"

SRC_URI_append_topic-miami = " file://pl-pmodld8.dts"
