SUMMARY = "Kernel driver module VDMA video driver"
require kernel-module-topic.inc

SRCREV = "b79d781158ad10b78594b2c7e59c3ee87ead62c2"

RRECOMMENDS_${PN} = "\
	kernel-module-backlight \
	kernel-module-xilinx-dma \
	"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "xilinx_dma" > ${D}/etc/modules-load.d/${MODULE}.conf
	echo "${MODULE}" >> ${D}/etc/modules-load.d/${MODULE}.conf
}
