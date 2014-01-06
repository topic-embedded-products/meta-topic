DESCRIPTION = "FPGA bitstream reference design from ADI."

require fpga-image.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = "git://github.com/analogdevicesinc/fpgahdl_xilinx.git"
SRCREV = "0a90b0d42efa2c571e1dd3a038a00f974d6b3e5f"
BOARD_DESIGN_PATH = ""
BOARD_DESIGN_PATH_zedboard = "cf_adv7511_zed"
BOARD_DESIGN_PATH_zynq-zc702 = "cf_adv7511_zc702"
BOARD_DESIGN_PATH_zynq-zc706 = "cf_adv7511_zc706"

PV = "1.${SRCPV}"
