require recipes-bsp/u-boot/u-boot.inc

# To build u-boot for your machine, provide the following lines in
# your machine config, replacing the assignments as appropriate for
# your machine.
# UBOOT_MACHINE = "omap3_beagle_config"
# UBOOT_ENTRYPOINT = "0x80008000"
# UBOOT_LOADADDRESS = "0x80008000"

UBOOT_MACHINE = "zynq_zc70x_config"

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

#FILESDIR = "${@os.path.dirname(d.getVar('FILE',1))}/u-boot-git/${MACHINE}"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "7639205355d437d0650953021b99c0e515355c62"

PV = "xilinx-zc702"
PR = "r1"

SRC_URI = "git://git.xilinx.com/u-boot-xarm.git;protocol=git \
           file://0001-Change-boot-options-so-linux-can-boot-without-a-ramd.patch"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
