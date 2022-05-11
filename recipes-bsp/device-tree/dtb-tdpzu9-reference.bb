require dtb-overlay.inc

SUMMARY = "Devicetree overlay for tdpzu9 board"

RRECOMMENDS:${PN} += "\
	kernel-module-i2c-xiic \
	kernel-module-gpio-xilinx \
	"

BITSTREAM:tdpzu9 = "fpga-image-tdp-reference"

COMPATIBLE_MACHINE = "^tdp"
