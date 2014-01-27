require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"

SRCREV = "c55c35bb239a40d5d5d7678fc38b92c77cf882f8"

PV = "xilinx-zynq"
PR = "r6"

SRC_URI = "git://github.com/Xilinx/u-boot-xlnx.git \
           file://0001-Use-bootscript-to-boot-use-fast-XIP-load-address-no-.patch \
           file://0002-Revert-arm-zynq-Enable-the-Neon-instructions.patch \
          "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Fetch the FSBL binary from the design for now, as building it is a royal pita.
BOARD_DESIGN_URI = "file:/"
SRC_URI += "${BOARD_DESIGN_URI}/fsbl.elf.bz2;name=fsbl-${MACHINE}"
ZYNQ_FSBL = "${WORKDIR}/fsbl.elf"
# SRC_URI[fsbl-zynq-zc702.md5sum] = "b30394d80d1b7355035c2aaf69133cbf"
# SRC_URI[fsbl-zedboard.md5sum] = "defd368ccf504c5d252f358516840b40"

# Generate BOOT.bin from u-boot, FSBL and optionally a bitfile. This requires the
# "bootgen" tool from Xilinx, as well as a first-stage-bootloader which can be
# generated in the GUI, and optionally a bitfile for the FPGA. Set the ZYNQ_
# variables in your local.conf or site.conf to match your project. Or set
# ZYNQ_BOOTGEN="echo" if you don't want to generate BOOT.bin now.
do_bootbin() {
	rm -f ${S}/BOOT.bin
	echo "the_ROM_image:" > bootimage.bif
	echo "{" >> bootimage.bif
	echo " [bootloader]${ZYNQ_FSBL}" >> bootimage.bif
	echo " ${ZYNQ_BITFILE}" >> bootimage.bif
	echo " [load = 0x04000000, startup = 0x04000000]${S}/${UBOOT_BINARY}" >> bootimage.bif
	echo "}" >> bootimage.bif
	echo "executing: {$ZYNQ_BOOTGEN} -image bootimage.bif -o i ${S}/BOOT.bin"
	${ZYNQ_BOOTGEN} -image bootimage.bif -o i ${S}/BOOT.bin
}

do_install_append() {
	install ${S}/BOOT.bin ${D}/boot/BOOT.bin
}

do_deploy_append() {
	install ${S}/BOOT.bin ${DEPLOYDIR}/BOOT.bin
}

addtask bootbin before do_deploy do_install after do_compile
