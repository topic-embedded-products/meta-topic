
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " file://regs.init"

BIF_PARTITION_ATTR_zynqmp = "${@'fsbl pmu atf dtb u-boot init' if d.getVar('FPGA_MNGR_RECONFIG_ENABLE') == '1' else 'fsbl bitstream pmu atf dtb u-boot init'}"

BIF_PARTITION_ATTR[init] ?= "init"
BIF_PARTITION_IMAGE[init] ?= "${WORKDIR}/regs.init"
BIF_PARTITION_DEPENDS[init] ?= "arm-trusted-firmware:do_deploy"

BIF_PARTITION_ATTR_zynq ?= "fsbl u-boot"
