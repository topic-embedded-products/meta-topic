# U-boot SPL boots without FSBL. This requires a hardware design that
# somehow exports the hardware SDK into the sysroot.

require u-boot-zynq.inc

DEPENDS += "virtual/xilinx-sdk"

SPL_BINARY = "boot.bin"
SPL_SYMLINK = "BOOT.bin"

UBOOT_SUFFIX = "img"

# Fetch the ps7_init files from the FPGA image into the bootloader. The
# fpga-image recipe generates and copies these into the STAGING_DATADIR.
do_configure_prepend() {
	rm -f ${S}/board/xilinx/zynq/ps7_init.[ch]
	cp ${STAGING_DATADIR}/xilinx_sdk/ps7_init.[ch] ${S}/board/xilinx/zynq/
}
