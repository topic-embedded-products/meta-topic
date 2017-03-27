SUMMARY = "Kernel driver module VDMA video driver"
require kernel-module-topic.inc

SRCREV = "29c911fc839588830098dc61c57ed1357ae1671d"

RRECOMMENDS_${PN} = "\
	kernel-module-backlight \
	kernel-module-xilinx-dma \
	"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "xilinx_dma" > ${D}/etc/modules-load.d/${MODULE}.conf
	echo "${MODULE}" >> ${D}/etc/modules-load.d/${MODULE}.conf
}
