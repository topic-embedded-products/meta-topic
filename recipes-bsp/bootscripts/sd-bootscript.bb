DESCRIPTION = "Bootscript for MMC/SD card"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PV = "1"

inherit uboot_bootscript deploy
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://autorun.scr"

S = "${WORKDIR}"

do_compile () {
	oe_mkimage_script -n "autorun" -d ${WORKDIR}/autorun.scr ${S}/autorun.uimage.scr
}

do_install () {
	install -d ${D}/boot
	install ${S}/autorun.uimage.scr ${D}/boot/autorun.scr
}

FILES_${PN} = "/boot"

do_deploy () {
	install -d ${DEPLOYDIR}
	install ${S}/autorun.uimage.scr ${DEPLOYDIR}/autorun.scr
}
do_deploy[dirs] = "${B}"
addtask deploy before do_build after do_compile

do_configure[noexec] = "1"
