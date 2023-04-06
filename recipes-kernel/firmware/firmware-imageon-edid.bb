SUMMARY = "EDID firmware blob for ADV7604 driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit allarch

SRC_URI = "https://github.com/analogdevicesinc/linux/blob/c7c505283d373d0618e33e0cefe3c195133e4ad4/firmware/imageon_edid.bin?raw=true;downloadfilename=imageon_edid.bin"

S = "${WORKDIR}"

FILES:${PN} = "/lib/firmware"

do_install() {
	install -d ${D}/lib/firmware
	install -m 644 ${S}/imageon_edid.bin ${D}/lib/firmware/
}

SRC_URI[md5sum] = "136512f5377191211382664d5f0474bd"
SRC_URI[sha256sum] = "191ee621dafb021d9c0c7cc65959daa8cd30913ff3ca37bf0c723314be7ed9d0"
