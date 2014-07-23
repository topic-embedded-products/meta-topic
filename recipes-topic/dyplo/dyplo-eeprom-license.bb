DESCRIPTION = "Dyplo license loader, reads key from EEPROM at startup"
# We don't need libc or gcc or whatever
INHIBIT_DEFAULT_DEPS = "1"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
# Package is machine independent, but each machine is expected to have
# different EEPROM location and offset.
PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS_${PN} = "dyplo-utils"

# On topic-miami boards, store the key in the last 8 bytes of the
# EEPROM chip.
EEPROM_FILE = "-o 504 /sys/bus/i2c/devices/1-0050/eeprom"

SRC_URI = "file://init"

inherit update-rc.d

# Set to start at 09, after loading FPGA and (dyplo) modules
INITSCRIPT_NAME = "${PN}.sh"
INITSCRIPT_PARAMS = "start 9 S ."

do_compile() {
	sed 's!@EEPROM_FILE@!${EEPROM_FILE}!g' ${WORKDIR}/init > ${B}/init
}

FILES_${PN} = "${sysconfdir}"
do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${B}/init ${D}${sysconfdir}/init.d/${PN}.sh
}
