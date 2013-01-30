SECTION = "base"
DESCRIPTION = "modutils configuration files"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://modload.sh;lines=2;md5=9a17195430e0508eaf9830505cd89be4"
SRC_URI = "file://modload.sh"
PR = "r0"

INITSCRIPT_NAME = "modload.sh"
INITSCRIPT_PARAMS = "start 5 S ."

S = "${WORKDIR}"

inherit update-rc.d

do_compile () {
}

do_install () {
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/modload.sh ${D}${sysconfdir}/init.d/
}
