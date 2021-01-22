DESCRIPTION = "U-Boot Bootscript"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PV = "2"

inherit uboot_bootscript
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend_tdkz15 := "${THISDIR}/bootscript/tdkz:"
FILESEXTRAPATHS_prepend_tdkz30 := "${THISDIR}/bootscript/tdkz:"

SRC_URI = "file://boot.scr"

S = "${WORKDIR}"

do_compile () {
	oe_mkimage_script -n "autorun" -d ${WORKDIR}/boot.scr ${S}/autorun.uimage.scr
}

do_install () {
	install -d ${D}/boot
	install ${S}/autorun.uimage.scr ${D}/boot/boot.scr
}

FILES_${PN} = "/boot"

do_configure[noexec] = "1"
