SUMMARY = "Module loader for Zedboard OLED"
DESCRIPTION = "Loads Zedboard OLED driver and displays a random pattern on it at boot"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
DEPENDS = "python-native"

PV = "2"
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
INITSCRIPT_PARAMS = "start 9 S ."

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
