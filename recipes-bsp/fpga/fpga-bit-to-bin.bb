DESCRIPTION = "FPGA bitstream image loader, loads fpga.bin early at boot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
# Package is machine independent (shell script only)
inherit allarch

BBCLASSEXTEND = "native"

SRC_URI = "file://fpga-bit-to-bin.py"

S = "${WORKDIR}"
PV = "3.1"

# Patch the script to use the OE-provided Python interpreter
do_compile() {
	mv ${WORKDIR}/fpga-bit-to-bin.py ${WORKDIR}/fpga-bit-to-bin.py.tmp
	echo "#!${PYTHON}" > ${WORKDIR}/fpga-bit-to-bin.py
	grep -v '^#' ${WORKDIR}/fpga-bit-to-bin.py.tmp >> ${WORKDIR}/fpga-bit-to-bin.py
}

FILES_${PN} = "${bindir}"
do_install() {
	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/fpga-bit-to-bin.py ${D}${bindir}/fpga-bit-to-bin.py
}

