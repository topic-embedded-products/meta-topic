require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

#FILESDIR = "${@os.path.dirname(d.getVar('FILE',1))}/u-boot-git/${MACHINE}"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "7639205355d437d0650953021b99c0e515355c62"

PV = "xilinx-zc702"
PR = "r2.6"

SRC_URI = "git://git.xilinx.com/u-boot-xarm.git;protocol=git \
           file://0001-Change-boot-options-so-linux-can-boot-without-a-ramd.patch \
           file://zynq-boot-xip.patch \
           file://autorun-bootscript.patch \
          "

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Generate BOOT.bin from u-boot, FSBL and optionally a bitfile. This requires the
# "bootgen" tool from Xilinx, as well as a first-stage-bootloader which can be
# generated in the GUI, and optionally a bitfile for the FPGA. Set the ZYNQ_
# variables in your local.conf or site.conf to match your project. Or set
# ZYNQ_BOOTGEN="echo" if you don't want to generate BOOT.bin now.
do_bootbin() {
	cd ${DEPLOYDIR}
	rm -f ${DEPLOY_DIR_IMAGE}/BOOT.bin
	echo "the_ROM_image:" > bootimage.bif
	echo "{" >> bootimage.bif
	echo " [bootloader]${ZYNQ_FSBL}" >> bootimage.bif
	echo " ${ZYNQ_BITFILE}" >> bootimage.bif
	echo " [load = 0x04000000, startup = 0x04000000]${UBOOT_IMAGE}" >> bootimage.bif
	echo "}" >> bootimage.bif
	${ZYNQ_BOOTGEN} -image bootimage.bif -o i ${DEPLOY_DIR_IMAGE}/BOOT.bin
}

addtask bootbin before do_build after do_deploy
