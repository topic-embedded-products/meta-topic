require dtb-overlay.inc

SUMMARY = "Devicetree overlay for XDP reference FPGA image"

PV = "2"

RRECOMMENDS_${PN} += "xdp-hwdetect"

BITSTREAM_xdpzu7 = "fpga-image-pr-reference"

COMPATIBLE_MACHINE = "^xdp"

SRC_URI += "file://xdp-eio.dts"
