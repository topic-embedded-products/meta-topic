# U-boot SPL boots without FSBL. This requires a hardware design that
# somehow exports the hardware SDK into the sysroot.

require u-boot-zynq.inc

SRC_URI += "\
	file://0003-Disable-FPGA-support.patch \
	"

DEPENDS += "virtual/xilinx-sdk"

# Fetch the ps7_init files from the FPGA image into the bootloader. The
# fpga-image recipe generates and copies these into the STAGING_DATADIR.
do_configure_prepend() {
	rm -f ${S}/board/xilinx/zynq/ps7_init.[ch]
	cp ${STAGING_DATADIR}/xilinx_sdk/ps7_init.[ch] ${S}/board/xilinx/zynq/
}


do_install_append() {
	install ${S}/boot.bin ${D}/boot/BOOT.bin
	install ${S}/u-boot.img ${D}/boot/
}

do_deploy_append() {
	install ${S}/boot.bin ${DEPLOYDIR}/BOOT.bin
	install ${S}/u-boot.img ${DEPLOYDIR}/
}

