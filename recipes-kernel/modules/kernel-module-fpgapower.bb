SUMMARY = "Kernel driver module to indicate that the FPGA has been programmed"
require kernel-module-topic.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8264535c0c4e9c6c335635c4026a8022"

SRCREV = "dcf85db5e3ea6fa41042c26de4a49b4d56eda2a1"

# Prefix the module with an underscore to make it load sooner.
do_install_append() {
	install -d ${D}/etc/modules-load.d
	echo "${MODULE}" >> ${D}/etc/modules-load.d/_${MODULE}.conf
}
