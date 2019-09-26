SRC_URI += "\
	file://inetd file://inetd.conf \
	file://mdev file://mdev.conf file://mdev-defaults \
	"

do_install_append() {
	if grep -q "CONFIG_CRONTAB=y" ${WORKDIR}/defconfig; then
		install -d ${D}${sysconfdir}/cron/crontabs
	fi
	install -d ${D}${sysconfdir}/default
	install -m 644 ${WORKDIR}/mdev-defaults ${D}${sysconfdir}/default/mdev
}

FILES_${PN}-mdev += "${sysconfdir}/default/mdev"

# use our own defconfig
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
