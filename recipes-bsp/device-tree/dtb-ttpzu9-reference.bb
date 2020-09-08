require dtb-overlay.inc

SUMMARY = "Devicetree overlay for ttpzu9 board"

BITSTREAM_ttpzu9 = "fpga-image-ttp-reference"

RRECOMMENDS_${PN} += "kernel-module-i2c-xiic kernel-module-uio-dmem-genirq"

COMPATIBLE_MACHINE = "^ttp"
