DESCRIPTION = "U-Boot Bootscript"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"
PV = "2"

inherit uboot_bootscript
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://boot.scr"
SRC_URI:append:tdkz10 = " file://autorun.scr"

S = "${WORKDIR}"

do_compile () {
	oe_mkimage_script -n "autorun" -d ${WORKDIR}/boot.scr ${S}/autorun.uimage.scr
}

do_install () {
	install -d ${D}/boot
	install ${S}/autorun.uimage.scr ${D}/boot/boot.scr
}

do_compile:append:tdkz10 () {
	oe_mkimage_script -n "autorun" -d ${WORKDIR}/autorun.scr ${S}/autorun-compat.uimage.scr
}
do_install:append:tdkz10 () {
	install ${S}/autorun-compat.uimage.scr ${D}/boot/autorun.scr
}

FILES:${PN} = "/boot"

do_configure[noexec] = "1"
