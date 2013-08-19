SUMMARY = "Zedboard  module loader for ADAU1761"
DESCRIPTION = "Loads drivers for the on-board audio codec, configures it for generic \
capture and playback, and sets it as the default output for ALSA applications."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
INHIBIT_DEFAULT_DEPS = "1"

PV = "3"
PR = "r0"

PACKAGES = "${PN}"

S="${WORKDIR}"

SRC_URI = "\
	file://modules.conf \
	file://zed-audio-config.sh \
	file://asound.conf \
	http://ez.analog.com/servlet/JiveServlet/download/80077-14002/zed_audio.state.zip \
	"
# Checksums for zed_audio.state.zip
SRC_URI[md5sum] = "96e42e5c3fd01e7c9d372a31b55ee013"
SRC_URI[sha256sum] = "fd8f7fb31fa57dd4a625b7d21c226260d304073afbbf37cfd2672d6396dd2722"

inherit update-rc.d

INITSCRIPT_NAME = "zed-audio-config.sh"
INITSCRIPT_PARAMS = "start 50 S ."

FILES_${PN} += "\
	/lib/firmware/adau1761.bin \
	${sysconfdir}/init.d/zed-audio-config.sh \
	"

RDEPENDS_${PN} += "alsa-utils-alsactl"

# Provide fake firmware to prevent blocking during bootup
do_compile() {
	touch ${S}/adau1761.bin
}

do_install() {
	install -d ${D}${sysconfdir}/modules-load.d
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${base_libdir}/firmware
	install -m 644 ${S}/modules.conf ${D}${sysconfdir}/modules-load.d/zed-adau1761.conf
	install -m 644 ${S}/zed_audio.state ${D}${sysconfdir}/
	install -m 644 ${S}/asound.conf ${D}${sysconfdir}/
	install -m 644 ${S}/adau1761.bin ${D}${base_libdir}/firmware/
	install -m 755 ${S}/zed-audio-config.sh ${D}${sysconfdir}/init.d/
}
