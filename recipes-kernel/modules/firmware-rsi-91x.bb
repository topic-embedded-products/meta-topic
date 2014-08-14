# Note: This package should be replaced by linux-firmware
SUMMARY = "Firmware for redpine wifi module"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://WHENCE;beginline=2469;endline=2480;md5=3bfb14daa3015a6a8408d68f41057db4"
PE = "1"
PV = "2+git${SRCPV}"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git"
SRCREV="7f388b4885cf64d6b7833612052d20d4197af96f"

S = "${WORKDIR}/git"

do_compile() {
	true
}

FILES_${PN} = "/lib/firmware"

do_install() {
	install -d ${D}/lib/firmware
	install -m 644 ${S}/rsi_91x.fw ${D}/lib/firmware/
}
