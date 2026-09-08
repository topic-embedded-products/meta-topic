# machine: Topic Developmentkit Plus with Zynq Ultrascale

MACHINE_BOARD = "ttpzu"

require conf/machine/include/tspzu.inc

DEVICETREE = "devicetree/${MACHINE}.dtb"
UBOOT_MACHINE_DTB = "${MACHINE}-uboot.dtb"

BOARD_PL_REFERENCE = "dtb-ttpzu9-reference"

# Extra packages to install for the board
MACHINE_EXTRA_RRECOMMENDS += "\
	kernel-module-u-ether \
	kernel-module-usb-f-ecm \
	kernel-module-usb-f-ecm-subset \
	kernel-module-usb-f-mass-storage \
	kernel-module-usb-f-rndis \
	"

MACHINE_FEATURES_EXTRA += "usbhost usbgadget ethernet powerkey"
