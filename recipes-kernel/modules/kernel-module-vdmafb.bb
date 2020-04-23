SUMMARY = "Kernel driver module VDMA video driver"
require kernel-module-topic.inc

SRCREV = "29c911fc839588830098dc61c57ed1357ae1671d"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

RRECOMMENDS_${PN} = "\
	kernel-module-backlight \
	kernel-module-xilinx-dma \
	"

KERNEL_MODULE_AUTOLOAD += "${MODULE}"
module_autoload_${MODULE} = "xilinx_dma ${MODULE}"

SRC_URI_append = "\
	file://vdma-dummy.patch \
	"
