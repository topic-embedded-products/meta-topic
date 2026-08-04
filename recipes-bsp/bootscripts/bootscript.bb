DESCRIPTION = "U-Boot Bootscript"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"
PV = "2"

inherit uboot_bootscript
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://boot.scr"

S = "${UNPACKDIR}"

do_compile () {
	oe_mkimage_script -n "boot" -d ${S}/boot.scr ${B}/boot.uimage.scr
}

do_install () {
	install -d ${D}/boot
	install ${B}/boot.uimage.scr ${D}/boot/boot.scr
}

FILES:${PN} = "/boot"

do_configure[noexec] = "1"
