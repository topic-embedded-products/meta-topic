SUMMARY = "Module loader for Zedboard OLED"
DESCRIPTION = "Loads Zedboard OLED driver and displays a random pattern on it at boot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "python-native"

PV = "5"
PR = "r0"

PACKAGES = "${PN}"

S="${WORKDIR}"

SRC_URI = "\
	file://modules.conf \
	file://init \
	file://bmp24_to_oled.py \
	file://topic_logo.bmp.gz \
	"

INITSCRIPT_NAME = "zed-oled.sh"
INITSCRIPT_PARAMS = "start 9 S . stop 80 0 1 6 ."

inherit update-rc.d

do_compile() {
	${PYTHON} ${S}/bmp24_to_oled.py ${S}/topic_logo.bmp > ${S}/oled.bin
}

FILES_${PN} += "${datadir}/oled.bin"

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${S}/init ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
	install -d ${D}${sysconfdir}/modules-load.d
	install -m 644 ${S}/modules.conf ${D}${sysconfdir}/modules-load.d/zed-oled.conf
	install -d ${D}${datadir}
	install -m 644 ${S}/oled.bin ${D}${datadir}/
}
