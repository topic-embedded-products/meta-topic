SUMMARY = "Kernel driver module VDMA video driver"
require kernel-module-topic.inc

SRCREV = "08648f1f62b22e6fada44f32816456af54a196a3"

RRECOMMENDS_${PN} = "\
	kernel-module-backlight \
	kernel-module-xilinx-dma \
	"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "xilinx_dma" > ${D}/etc/modules-load.d/${MODULE}.conf
	echo "${MODULE}" >> ${D}/etc/modules-load.d/${MODULE}.conf
}
