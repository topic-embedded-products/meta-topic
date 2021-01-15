DESCRIPTION = "U-Boot Bootscript"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PV = "2"

inherit uboot_bootscript
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://boot.scr"
SRC_URI_append_tdkz10 = " file://autorun.scr"

S = "${WORKDIR}"

do_compile () {
	oe_mkimage_script -n "autorun" -d ${WORKDIR}/boot.scr ${S}/autorun.uimage.scr
}

do_install () {
	install -d ${D}/boot
	install ${S}/autorun.uimage.scr ${D}/boot/boot.scr
}

do_compile_append_tdkz10 () {
	oe_mkimage_script -n "autorun" -d ${WORKDIR}/autorun.scr ${S}/autorun-compat.uimage.scr
}
do_install_append_tdkz10 () {
	install ${S}/autorun-compat.uimage.scr ${D}/boot/autorun.scr
}

FILES_${PN} = "/boot"

do_configure[noexec] = "1"
