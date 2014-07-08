SUMMARY = "Kernel driver module for ltc2990 4-channel I2C ADC"
require kernel-module-topic.inc

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "${MODULE}" > ${D}/etc/modules-load.d/${MODULE}.conf
}
