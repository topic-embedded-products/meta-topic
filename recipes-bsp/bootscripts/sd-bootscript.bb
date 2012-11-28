DESCRIPTION = "Bootscript for MMC/SD card"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0.3"

inherit uboot_bootscript

SRC_URI = "file://autorun.scr"
S = "${WORKDIR}"

do_configure () {
}

do_compile () {
	oe_mkimage_script -n "${MACHINE}-autorun" -d ${S}/autorun.scr ${S}/${MACHINE}-autorun.scr
}

do_install () {
	install -d ${D}/boot
	install ${S}/${MACHINE}-autorun.scr ${D}/boot/${MACHINE}-autorun.scr
}

FILES_${PN} = "/boot"

do_deploy () {
	install -d ${DEPLOY_DIR_IMAGE}
	install ${S}/${MACHINE}-autorun.scr ${DEPLOY_DIR_IMAGE}/${MACHINE}-autorun.scr
}
do_deploy[dirs] = "${S}"

addtask deploy before do_package_stage after do_compile
