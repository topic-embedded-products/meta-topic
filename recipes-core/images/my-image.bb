DESCRIPTION = "An image"
DEPENDS += "sd-bootscript"
# IMAGE_FEATURES += "splash"

IMAGE_FSTYPES = "tar.gz ubi"

inherit image

MY_THINGS = "\
	kernel-modules \
	modutils-loadscript \
	fpga-image \
	adi-hdmi-load \
	mtd-utils \
	nbench-byte \
	"

IMAGE_INSTALL = "\
	packagegroup-core-boot \
	packagegroup-core-ssh-dropbear \
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
ROOTFS_POSTPROCESS_COMMAND += "myimage_rootfs_postprocess"
