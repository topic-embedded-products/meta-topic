DESCRIPTION = "An image"
DEPENDS += "sd-bootscript"

DISTRO_EXTRA_DEPENDS ?= ""
MACHINE_EXTRA_DEPENDS ?= ""
DEPENDS += "${DISTRO_EXTRA_DEPENDS} ${MACHINE_EXTRA_DEPENDS}"

IMAGE_FEATURES += "package-management ssh-server-dropbear"

IMAGE_FSTYPES = "tar.gz ubi"

inherit core-image

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
	# Run populate-volatile.sh at rootfs time to set up basic files
	# and directories to support read-only rootfs.
	if [ -x ${IMAGE_ROOTFS}/etc/init.d/populate-volatile.sh ]; then
		echo "Running populate-volatile.sh"
		${IMAGE_ROOTFS}/etc/init.d/populate-volatile.sh
	fi
	rm -rf ${IMAGE_ROOTFS}/boot
	rm -rf ${IMAGE_ROOTFS}/media/* ${IMAGE_ROOTFS}/mnt
	ln -f -s media ${IMAGE_ROOTFS}/mnt
	rm -rf ${IMAGE_ROOTFS}/tmp
	ln -s var/volatile/tmp ${IMAGE_ROOTFS}/tmp
	rm -f ${IMAGE_ROOTFS}/etc/resolv.conf
	ln -s ../var/run/resolv.conf ${IMAGE_ROOTFS}/etc/resolv.conf
	rm -rf ${IMAGE_ROOTFS}/dev/*
}
ROOTFS_POSTPROCESS_COMMAND += "myimage_rootfs_postprocess ; "
