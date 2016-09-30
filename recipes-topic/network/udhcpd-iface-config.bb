SUMMARY = "Supplies interface-based startup of DHCP server udhcpd"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"
inherit allarch
PACKAGE_ARCH = "${MACHINE_ARCH}"
PV = "4"

PACKAGES = "${PN}"
SRC_URI = "file://udhcpd_up.sh file://udhcpd_down.sh file://udhcpd.*.conf"
S = "${WORKDIR}"
FILES_${PN} = "${sysconfdir}"

RDEPENDS_${PN} = "ifplugd-auto-net"

do_compile() {
}

do_install() {
	install -d ${D}/${sysconfdir}/network/if-up.d
	install -d ${D}/${sysconfdir}/network/if-down.d
	install -m 644 udhcpd.*.conf ${D}/${sysconfdir}
	install -m 755 udhcpd_up.sh ${D}/${sysconfdir}/network/if-up.d/udhcpd
	install -m 755 udhcpd_down.sh ${D}/${sysconfdir}/network/if-down.d/udhcpd
}
