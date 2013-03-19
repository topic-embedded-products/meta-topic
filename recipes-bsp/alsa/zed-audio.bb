DESCRIPTION = "Zedboard  module loader for ADAU1761"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PV = "0"
PR = "r0"

PACKAGES = "${PN}"

S="${WORKDIR}"

SRC_URI = "\
	file://modules.conf \
	http://ez.analog.com/servlet/JiveServlet/download/80077-14002/zed_audio.state.zip \
	"
# Checksums for zed_audio.state.zip
SRC_URI[md5sum] = "96e42e5c3fd01e7c9d372a31b55ee013"
SRC_URI[sha256sum] = "fd8f7fb31fa57dd4a625b7d21c226260d304073afbbf37cfd2672d6396dd2722"

do_compile() {
}

do_install() {
	install -d ${D}/etc
	install -d ${D}/etc/modules-load.d
	install -m 644 ${S}/modules.conf ${D}/etc/modules-load.d/zed-adau1761.conf
	install -m 644 ${S}/zed_audio.state ${D}/etc/
}

