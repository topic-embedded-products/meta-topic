require dtb-overlay.inc

SUMMARY = "Devicetree overlay for tdpzu9 board"

RRECOMMENDS:${PN} += "tdpzu-hwdetect kernel-module-i2c-xiic"

BITSTREAM:tdpzu9 = "fpga-image-tdp-reference"

COMPATIBLE_MACHINE = "^tdp"
