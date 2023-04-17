# machine: Topic Developmentkit Plus with Zynq Ultrascale

MACHINE_BOARD = "tdpzu"

require conf/machine/include/tspzu.inc

BOARD_PL_REFERENCE = "dtb-tdpzu-reference"

# Extra packages to install for the board
MACHINE_EXTRA_RRECOMMENDS += "\
	kernel-module-g-ether \
	kernel-module-g-mass-storage \
	kernel-module-u-ether \
	kernel-module-usb-f-ecm \
	kernel-module-usb-f-ecm-subset \
	kernel-module-usb-f-mass-storage \
	kernel-module-usb-f-rndis \
	kernel-module-topic-pl-fanctrl \
	pwm-fancontrol \
	"

MACHINE_FEATURES += "rtc usbhost usbgadget ethernet"

DEVICETREE = "devicetree/${MACHINE_BOARD}.dtb"

KERNEL_MODULE_AUTOLOAD += "g_ether"
