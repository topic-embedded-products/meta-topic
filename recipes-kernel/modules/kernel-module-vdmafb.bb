SUMMARY = "Kernel driver module VDMA video driver"
require kernel-module-topic.inc

SRCREV = "d0a4fd1975912c937bd7cab32ead4e60a6b8759f"

RRECOMMENDS_${PN} = "\
	kernel-module-backlight \
	kernel-module-xilinx-dma \
	"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "xilinx_dma" > ${D}/etc/modules-load.d/${MODULE}.conf
	echo "${MODULE}" >> ${D}/etc/modules-load.d/${MODULE}.conf
}
