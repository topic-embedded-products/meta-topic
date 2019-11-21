DESCRIPTION = "FPGA bitstream reference design from ADI."
LICENSE = "ADI"
LIC_FILES_CHKSUM = "file://../../../LICENSE;md5=af545fecac82c7016ae5cf2b62a18816"
require fpga-image.inc

# NOTE: This REQUIRES Vivado 2013.4 - using 2014.1 will fail because some
# ip cores have changed versions.
XILINX_VIVADO_VERSION = "2013.4"

BOARD_DESIGN_URI = "git://github.com/analogdevicesinc/hdl.git"
SRCREV = "f3f8414d812470f309713fdd99c91af1a6458375"
BOARD_DESIGN_PATH = ""
BOARD_DESIGN_PATH_zedboard = "projects/adv7511/zed"
BOARD_DESIGN_PATH_zynq-zc702 = "projects/adv7511/zc702"
BOARD_DESIGN_PATH_zynq-zc706 = "projects/adv7511/zc706"

HDL_LIBRARIES = "axi_clkgen axi_hdmi_tx axi_i2s_adi axi_spdif_tx util_i2c_mixer"

PV = "2.${SRCPV}"

do_compile() {
	source ${XILINX_VIVADO_PATH}/settings${XILINX_TOOL_ARCH}.sh
	test -f ${S}/system_project.tcl
	# Create libaries
	cd ${S}/../../../library/
	for lib in ${HDL_LIBRARIES}
	do
		cd ${lib}
		vivado -mode tcl -source ${lib}_ip.tcl < /dev/null
		cd ..
	done
	# Create project
	cd ${S}
	vivado -mode tcl -source system_project.tcl < /dev/null
	ln *.sdk/SDK/SDK_Export/hw/system_top.bit fpga.bit
	test -f fpga.bit
}

# Store the SDK files into the sysroot for other packages
SYSROOT_PREPROCESS_FUNCS += "fpga_sysroot_preprocess"
fpga_sysroot_preprocess() {
	if [ -d ${S}/*.sdk/SDK/SDK_Export ]
	then
		install -d ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk
		cp -r ${S}/*.sdk/SDK/SDK_Export/hw/* ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk/
	else
		bbwarn "No SDK exported, cannot create u-boot SPL from this!"
	fi
}

