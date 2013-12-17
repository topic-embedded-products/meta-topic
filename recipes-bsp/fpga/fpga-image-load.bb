DESCRIPTION = "FPGA bitstream image loader, loads fpga.bin early at boot"
# We don't need libc or gcc or whatever
INHIBIT_DEFAULT_DEPS = "1"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
# Package is machine independent (shell script only)
PACKAGE_ARCH = "all"

SRC_URI = "file://init"

inherit update-rc.d

# Set to start at 03, which is before modutils
# so you can autoload modules which use FPGA logic.
INITSCRIPT_NAME = "${PN}.sh"
INITSCRIPT_PARAMS = "start 03 S ."

do_compile() {
	true
}

FILES_${PN} = "${sysconfdir}"
do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}.sh
}
