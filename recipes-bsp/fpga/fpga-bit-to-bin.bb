DESCRIPTION = "FPGA bitstream image loader, loads fpga.bin early at boot"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
# Package is machine independent (shell script only)
inherit allarch

BBCLASSEXTEND = "native"

SRC_URI = "file://fpga-bit-to-bin.py"

S = "${UNPACKDIR}"
PV = "3.1"

# Patch the script to use the OE-provided Python interpreter
do_compile() {
	mv ${S}/fpga-bit-to-bin.py ${S}/fpga-bit-to-bin.py.tmp
	echo "#!${PYTHON}" > ${S}/fpga-bit-to-bin.py
	grep -v '^#' ${S}/fpga-bit-to-bin.py.tmp >> ${S}/fpga-bit-to-bin.py
}

FILES:${PN} = "${bindir}"
do_install() {
	install -d ${D}${bindir}
	install -m 755 ${S}/fpga-bit-to-bin.py ${D}${bindir}/fpga-bit-to-bin.py
}

