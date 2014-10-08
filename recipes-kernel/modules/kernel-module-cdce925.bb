SUMMARY = "Kernel driver module for TI cdce925 clock synthesizer"
require kernel-module-topic.inc

SRCREV = "59da5f5db2ba33d10a11247f071e9ec54bdb6c8b"

do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "${MODULE}" > ${D}/etc/modules-load.d/${MODULE}.conf
}
