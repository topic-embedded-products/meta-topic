DESCRIPTION = "An image"
DEPENDS += "sd-bootscript"

DISTRO_EXTRA_DEPENDS ?= ""
MACHINE_EXTRA_DEPENDS ?= ""
DEPENDS += "${DISTRO_EXTRA_DEPENDS} ${MACHINE_EXTRA_DEPENDS}"

IMAGE_FEATURES += "package-management ssh-server-dropbear"

IMAGE_FSTYPES = "tar.gz ubi"

inherit image

MY_THINGS = "\
	kernel-modules \
	modutils-loadscript \
	fpga-image \
	adi-hdmi-load \
	mtd-utils \
	mtd-utils-ubifs \
	"

# Skip packagegroup-base to reduce the number of packages built. Thus, we need
# to include the MACHINE_EXTRA_ stuff ourselves.
IMAGE_INSTALL = "\
	packagegroup-core-boot \
	packagegroup-core-ssh-dropbear \
	${MACHINE_EXTRA_RDEPENDS} ${MACHINE_EXTRA_RRECOMMENDS} \
	${DISTRO_EXTRA_RDEPENDS} ${DISTRO_EXTRA_RRECOMMENDS} \
	${ROOTFS_PKGMANAGE} \
	${MY_THINGS} \
	"

# Postprocessing to reduce the amount of work to be done
# by configuration scripts
myimage_rootfs_postprocess() {
	curdir=$PWD
	cd ${IMAGE_ROOTFS}
	rm -rf boot
	rm -rf media/* mnt
	ln -f -s media mnt
	rm -rf tmp
	ln -s var/volatile/tmp tmp
	rm -f etc/resolv.conf
	ln -f -s ../var/run/resolv.conf etc/resolv.conf
	rm -rf dev/*

        cd $curdir
}
ROOTFS_POSTPROCESS_COMMAND += "myimage_rootfs_postprocess ; "
