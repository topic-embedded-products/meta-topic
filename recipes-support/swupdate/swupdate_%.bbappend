FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# To get and set active boot partition:
RDEPENDS_${PN} += "get-bootable-mbr-partition"
# To create ext4 filesystems:
RDEPENDS_${PN} += "e2fsprogs-mke2fs"
# To create ubi structures
RDEPENDS_${PN} += "mtd-utils"
# To create partition tables
RDEPENDS_${PN} += "parted"

SRC_URI += " \
	file://20-swupdate-arguments \
	file://background.jpg \
	file://swupdate.cfg \
	file://switch_mmc_boot_partition \
	file://create_mmc_links \
	file://prepare_filesystem \
"

do_install_append() {
	install -m 644 ${WORKDIR}/20-swupdate-arguments ${D}${libdir}/swupdate/conf.d/
	if [ -e ${WORKDIR}/swupdate.cfg ] ; then
		install -d ${D}${sysconfdir}
		install -m 644 ${WORKDIR}/swupdate.cfg ${D}${sysconfdir}/swupdate.cfg
	fi

	install -d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/switch_mmc_boot_partition ${D}${sbindir}
	install -m 0755 ${WORKDIR}/create_mmc_links ${D}${sbindir}
	install -m 0755 ${WORKDIR}/prepare_filesystem ${D}${sbindir}

	# Replace 1MB image with something more modest
	install -m 644 ${WORKDIR}/background.jpg ${D}/www/images/background.jpg
}
