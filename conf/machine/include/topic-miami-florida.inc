# Florida board common components
require conf/machine/include/topic-miami.inc

MACHINE_FPGA_BITSTREAM ?= "dtb-miami-florida-gen-reference"

# Extra packages to install for the board
BOARD_FPGA_PACKAGES ?= "\
	kernel-module-adi-axi-hdmi \
	kernel-module-adv7604 \
	kernel-module-axi-hdmi-rx \
	kernel-module-bitblit \
	kernel-module-dma-axi-dmac \
	kernel-module-snd-hwdep \
	kernel-module-snd-soc-adi-axi-spdif \
	kernel-module-snd-soc-adv7511-hdmi \
	kernel-module-vdmafb \
	kernel-module-videobuf2-core \
	kernel-module-videobuf2-dma-contig \
	kernel-module-videobuf2-memops \
	kernel-module-videobuf2-vmalloc \
	kernel-module-virt-dma \
	kernel-module-xilinx-dma \
	"

KERNEL_MODULE_AUTOLOAD += "adi_axi_hdmi"
module_autoload_adi_axi_hdmi = "xilinx_dma adi_axi_hdmi snd_soc_adi_axi_spdif snd_soc_adv7511_hdmi snd_soc_simple_card"
KERNEL_MODULE_AUTOLOAD += "axi-hdmi-rx"
module_autoload_axi-hdmi-rx = "adv7604 dma-axi-dmac axi-hdmi-rx"

MACHINE_FEATURES += "usbhost usbgadget screen touchscreen wifi alsa ethernet"

DEVICETREE = "topic-miami-florida-mio.dtb"
