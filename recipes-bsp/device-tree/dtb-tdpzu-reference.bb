require dtb-overlay.inc

SUMMARY = "Devicetree overlay for tdpzu boards"

RRECOMMENDS:${PN} += "\
	kernel-module-i2c-xiic \
	kernel-module-gpio-xilinx \
	"

BITSTREAM:tdpzu = "fpga-image-tdp-reference"

COMPATIBLE_MACHINE = "^tdpzu"
