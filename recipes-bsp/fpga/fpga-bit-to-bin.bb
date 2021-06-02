DESCRIPTION = "FPGA bitstream image loader, loads fpga.bin early at boot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
# Package is machine independent (shell script only)
inherit allarch

BBCLASSEXTEND = "native"

SRC_URI = "file://fpga-bit-to-bin.py"

S = "${WORKDIR}"
PV = "4"

do_compile() {
	true
}

FILES_${PN} = "${bindir}"
do_install() {
	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/fpga-bit-to-bin.py ${D}${bindir}/fpga-bit-to-bin.py
}

