DESCRIPTION = "FPGA bitstream reference design from ADI."

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = "git://github.com/analogdevicesinc/fpgahdl_xilinx.git"
SRCREV = "3e6cfd4858635e49ea131da1bb4b7b6dd258280b"
BOARD_DESIGN_PATH = ""
BOARD_DESIGN_PATH_zedboard = "cf_adv7511_zed"
BOARD_DESIGN_PATH_zynq-zc702 = "cf_adv7511_zc702"
BOARD_DESIGN_PATH_zynq-zc706 = "cf_adv7511_zc706"

PV = "1.${SRCPV}"

# Support for u-boot-spl
PROVIDES = "virtual/xilinx-sdk"
# Store the SDK files into the sysroot for other packages
SYSROOT_PREPROCESS_FUNCS += "fpga_sysroot_preprocess"
fpga_sysroot_preprocess() {
	if [ -d ${S}/SDK/SDK_Export ]
	then
		install -d ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk
		cp -r ${S}/SDK/SDK_Export/hw/* ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk/
	else
		bbwarn "No SDK exported, cannot create u-boot SPL from this!"
	fi
}

