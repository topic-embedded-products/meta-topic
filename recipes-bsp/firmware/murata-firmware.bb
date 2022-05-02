LICENSE = "CLOSED"
SUMMARY = "Temporary provider for Murata BLE and SDIO firmware additions"

# Copied from linux-firmware and the murata website

inherit allarch

SRC_URI = "file://brcmfmac43430-sdio.MUR1DX.txt file://BCM43430A1.1DX.hcd"

S = "${WORKDIR}"

do_compile() {
	:
}

FILES:${PN} = "${nonarch_base_libdir}/firmware /etc/firmware"
# inhibit dbg/dev packages
PACKAGES = "${PN}"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/brcm
	install -m 644 ${S}/brcmfmac43430-sdio.MUR1DX.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac43430-sdio.txt
	install -m 644 ${S}/BCM43430A1.1DX.hcd ${D}${nonarch_base_libdir}/firmware/brcm/
	install -d ${D}/etc
	ln -s ..${nonarch_base_libdir}/firmware ${D}/etc/firmware
}
