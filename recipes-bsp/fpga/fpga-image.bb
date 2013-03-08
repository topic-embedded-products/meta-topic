DESCRIPTION = "FPGA bit image loader from userspace and tools"
DEPENDS = "python-native"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PV = "0"
PR = "r2"

PACKAGES = "${PN}"

S="${WORKDIR}"

SRC_URI = "file://fpga-bit-to-bin.py file://init"

inherit update-rc.d

# Set to start at 03, which is before modutils
# so you can autoload modules which use FPGA logic.
INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "start 03 S ."

do_compile() {
	python fpga-bit-to-bin.py --flip ${ZYNQ_ROOTFS_BITFILE} ${S}/fpga.bin
}

do_install() {
	install -d ${D}/etc
	install -d ${D}/etc/init.d
	install -m 755 ${S}/init ${D}/etc/init.d/${PN}
	install -m 644 ${S}/fpga.bin ${D}/etc/fpga.bin
}

