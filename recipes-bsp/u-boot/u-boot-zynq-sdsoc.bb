# U-boot SPL boots without FSBL. This requires a hardware design that
# somehow exports the hardware SDK into the sysroot.

require u-boot-zynq.inc

# Don't provide virtual/bootloader, otherwise OE thinks they conflict.
PROVIDES = ""

DEPENDS += "virtual/xilinx-sdk"

UBOOT_SUFFIX = "elf"
SPL_BINARY = ""

SRC_URI += "file://0001-topic-miami-SDSoc-bootmode.patch"

# Fetch the ps7_init files from the FPGA image into the bootloader. The
# fpga-image recipe generates and copies these into the STAGING_DATADIR.
do_configure_prepend() {
	rm -f ${S}/board/xilinx/zynq/ps7_init.[ch]
	cp ${STAGING_DATADIR}/xilinx_sdk/ps7_init.[ch] ${S}/board/xilinx/zynq/
}

do_compile_append() {
	cp ${S}/u-boot ${S}/u-boot.${UBOOT_SUFFIX}
}

# Installing ELF results in extra debug files, package them properly.
FILES_${PN}-dbg += "boot/.debug"
