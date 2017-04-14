# Florida board common components
require conf/machine/include/topic-miamiplus.inc

MACHINE_FPGA_BITSTREAM ?= ""

# Extra packages to install for the board
BOARD_FPGA_PACKAGES ?= "\
	kernel-module-g-ether \
	kernel-module-g-mass-storage \
	kernel-module-g-ncm \
	kernel-module-gspca-main \
	kernel-module-u-ether \
	kernel-module-pwc \
	kernel-module-usb-f-ecm \
	kernel-module-usb-f-ecm-subset \
	kernel-module-usb-f-mass-storage \
	kernel-module-usb-f-ncm \
	kernel-module-usb-f-rndis \
	kernel-module-usbserial \
	kernel-module-uvcvideo \
	"

MACHINE_FEATURES += "usbhost usbgadget"

DEVICETREE = "uImage-topic-miamiplus-florida-test.dtb"
