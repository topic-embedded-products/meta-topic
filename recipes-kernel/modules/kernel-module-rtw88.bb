require kernel-module-topic.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "0+${SRCPV}"
PKGV = "0+${GITPKGV}"

SRCREV = "09a04a21fae786546366fb3ca84ef5d2887255f7"
SRC_URI = "git://github.com/kimocoder/rtw88-usb.git;protocol=https"

SRC_URI += "file://0001-Makefile-OpenEmbedded-compatible.patch"

PACKAGES =+ "firmware-rtw88"

FILES_firmware-rtw88 = "/lib/firmware"

RRECOMMENDS_${PN} += "firmware-rtw88"

do_install_append() {
	install -d ${D}/lib/firmware/rtw88
	install -m 644 ${S}/fw/* ${D}/lib/firmware/rtw88
}
