SUMMARY = "Kernel driver module for TI cdce925 clock synthesizer"
require kernel-module-topic.inc

SRCREV = "f1302079fd203fa4b3940c635e6870e2de049c64"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "${MODULE}" > ${D}/etc/modules-load.d/${MODULE}.conf
}
