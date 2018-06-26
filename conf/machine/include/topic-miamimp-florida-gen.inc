require conf/machine/include/topic-miamimp.inc

MACHINE_FPGA_BITSTREAM ?= "fpga-image-miamimp-florida-gen-reference"

KERNEL_MODULE_AUTOLOAD += "macb g_ether spi-cadence mali"

# Extra packages to install for the board
BOARD_FPGA_PACKAGES ?= "\
	kernel-module-adi-axi-hdmi \
	kernel-module-adv7604 \
	kernel-module-axi-hdmi-rx \
	kernel-module-bitblit \
	kernel-module-dma-axi-dmac \
	kernel-module-g-ether \
	kernel-module-g-mass-storage \
	kernel-module-macb \
	kernel-module-u-ether \
	kernel-module-usb-f-ecm \
	kernel-module-usb-f-ecm-subset \
	kernel-module-usb-f-mass-storage \
	kernel-module-usb-f-rndis \
	kernel-module-videobuf2-core \
	kernel-module-videobuf2-dma-contig \
	kernel-module-videobuf2-memops \
	kernel-module-videobuf2-vmalloc \
	kernel-module-xilinx-dma \
	kernel-module-mali \
	"

KERNEL_MODULE_AUTOLOAD += "adi_axi_hdmi"
module_autoload_adi_axi_hdmi = "xilinx_dma adi_axi_hdmi snd_soc_adi_axi_spdif snd_soc_adv7511_hdmi snd_soc_simple_card"
KERNEL_MODULE_AUTOLOAD += "axi-hdmi-rx"
module_autoload_axi-hdmi-rx = "adv7604 dma-axi-dmac axi-hdmi-rx"

MACHINE_FEATURES += "usbhost usbgadget"

DEVICETREE ?= "Image-zynqmp-topic-miamimp-florida-gen.dtb"
