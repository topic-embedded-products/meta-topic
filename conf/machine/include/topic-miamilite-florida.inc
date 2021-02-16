# Florida board common components
require conf/machine/include/topic-miamilite.inc

BOARD_PL_REFERENCE = "dtb-miamilite-florida-gen-reference"

# Extra packages to install for the board
MACHINE_EXTRA_RRECOMMENDS += "\
	kernel-module-g-ether \
	kernel-module-u-ether \
	kernel-module-usb-f-ecm \
	kernel-module-usb-f-ecm-subset \
	kernel-module-usb-f-rndis \
	"


MACHINE_FEATURES += "usbhost usbgadget ethernet"

DEVICETREE = "${MACHINE}.dtb"

DEVICETREELINKS = "\
	system.dtb  devicetree/${DEVICETREE} \n \
	"
