require conf/machine/include/topic-miamimp.inc

MACHINE_FPGA_BITSTREAM ?= ""

KERNEL_MODULE_AUTOLOAD += "g_ether"

# Extra packages to install for the board
BOARD_FPGA_PACKAGES ?= "\
	kernel-module-g-ether \
	kernel-module-g-mass-storage \
	kernel-module-u-ether \
	kernel-module-usb-f-ecm \
	kernel-module-usb-f-ecm-subset \
	kernel-module-usb-f-mass-storage \
	kernel-module-usb-f-rndis \
	"

MACHINE_FEATURES += "usbhost usbgadget"

DEVICETREE ?= "Image-zynqmp-topic-miamimp-florida-gen.dtb"
