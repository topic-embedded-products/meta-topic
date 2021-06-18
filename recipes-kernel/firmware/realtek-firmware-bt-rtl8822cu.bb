# The rtl8822cu chipset requires a newer BT firmware version than what is
# currently supplied with petalinux.
LIC_FILES_CHKSUM = "file://LICENCE.rtlwifi_firmware.txt;md5=00d06cfd3eddd5a2698948ead2ad54a5"
LICENSE = "Firmware-rtlwifi_firmware"
NO_GENERIC_LICENSE[Firmware-rtlwifi_firmware] = "LICENCE.rtlwifi_firmware.txt"

SUMMARY = "Firmware for rtl8822cu bluetooth"

inherit allarch

PV = "20210618"
SRCREV = "64f02a260ef8bd9e0d1dad369ef376338045d15b"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git"

S = "${WORKDIR}/git"

CLEANBROKEN = "1"
INSANE_SKIP = "arch"

do_compile() {
	:
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/rtl_bt
	install -m 644 ${S}/rtl_bt/rtl8822cu_*.bin ${D}${nonarch_base_libdir}/firmware/rtl_bt/
}

FILES_${PN} = "${nonarch_base_libdir}/firmware/rtl_bt"
