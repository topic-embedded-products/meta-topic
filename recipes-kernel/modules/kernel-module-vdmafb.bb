SUMMARY = "Kernel driver module VDMA video driver"
require kernel-module-topic.inc

SRCREV = "8ec9c7da053e785ad09d3e20d945600d011c38b7"

RRECOMMENDS_${PN} = "\
	kernel-module-backlight \
	kernel-module-xilinx-dma \
	"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "xilinx_dma" > ${D}/etc/modules-load.d/${MODULE}.conf
	echo "${MODULE}" >> ${D}/etc/modules-load.d/${MODULE}.conf
}
