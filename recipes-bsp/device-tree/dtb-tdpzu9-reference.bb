require dtb-overlay.inc

SUMMARY = "Devicetree overlay for tdpzu9 board"

RRECOMMENDS_${PN} += "tdpzu-hwdetect kernel-module-i2c-xiic"

BITSTREAM_tdpzu9 = "fpga-image-tdp-reference"

COMPATIBLE_MACHINE = "^tdp"
