require recipes-bsp/device-tree/dtb-overlay.inc

SUMMARY = "Devicetree overlay for tepzu boards"

RRECOMMENDS:${PN} += "\
	kernel-module-i2c-xiic \
	"

BITSTREAM:tepzu = "fpga-image-tepzu-reference"

COMPATIBLE_MACHINE = "^tepzu"
