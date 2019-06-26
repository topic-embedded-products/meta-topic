# Kernel patch based approach. This takes a standard kernel and adds patches
# to add support for the Miami modules. Most patches are backports from newer
# kernels, so they're in mainline but not yet in Xilinx' tree. The exceptions
# to that are the ltc3562 driver, HDMI drivers and the devicetrees.
#
# TODO:
#  HDMI in and HDMI audio

require topic-xilinx-kernel-patches.inc

COMPATIBLE_MACHINE_topic-miami = "topic-miami"

# Using a defconfig from the kernel tree does not work when using patches, and
# the Xilinx provided zynqmp config contains way too much bloat, so we just use
# a full defconfig for our boards until this is mainlined.
SRC_URI_append_topic-miami = "file://defconfig"
SRC_URI_append_topic-miamimp = "file://defconfig"

# The configcheck doesn't do anything useful for us and takes several minutes
do_kernel_configcheck_topic-miamimp[noexec] = "1"
do_kernel_configcheck_topic-miami[noexec] = "1"

KERNEL_DEVICETREE_topic-miami = "\
	topic-miami-dyplo.dtb \
	topic-miami-dyplo-acp.dtb \
	topic-miami-florida-gen.dtb \
	topic-miami-florida-gen-pt.dtb \
	topic-miami-florida-gen-nand.dtb \
	topic-miami-florida-gen-amp.dtb \
	topic-miami-florida-mio.dtb \
	topic-miami-florida-mio-dyplo.dtb \
	topic-miami-florida-mio-nand-dyplo.dtb \
	topic-miami-florida-pci.dtb \
	topic-miami-florida-pci-pt.dtb \
	topic-miami-florida-test.dtb \
	topic-miamilite-florida-test.dtb \
	topic-miamiplus-florida-test.dtb \
	"
KERNEL_DEVICETREE_topic-miamimp = "\
	xilinx/zynqmp-topic-miamimp.dtb \
	xilinx/zynqmp-topic-miamimp-dyplo.dtb \
	xilinx/zynqmp-topic-miamimp-florida-gen.dtb \
	xilinx/zynqmp-topic-miamimp-florida-test.dtb \
	"
