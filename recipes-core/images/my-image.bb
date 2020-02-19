DESCRIPTION = "An image"

DISTRO_EXTRA_DEPENDS ?= ""
MACHINE_EXTRA_DEPENDS ?= ""
DEPENDS += "${DISTRO_EXTRA_DEPENDS} ${MACHINE_EXTRA_DEPENDS}"

IMAGE_FEATURES[validitems] += "swupdate"
IMAGE_FEATURES += "package-management ssh-server-dropbear swupdate"

IMAGE_FSTYPES = "tar.gz wic.gz ubifs"

inherit core-image

UBI_SUPPORT = "${@ 'true' if bb.utils.contains("IMAGE_FSTYPES", "ubi", True, False, d) or bb.utils.contains("IMAGE_FSTYPES", "ubifs", True, False, d) else 'false'}"

require ${@bb.utils.contains("IMAGE_FEATURES", "swupdate", "swu.inc", "", d)}

MY_THINGS = "\
	kernel-modules \
	kernel-devicetree \
	${@bb.utils.contains('VIRTUAL-RUNTIME_dev_manager', 'busybox-mdev', 'modutils-loadscript', '', d)} \
	${@ 'mtd-utils-ubifs' if d.getVar('UBI_SUPPORT') == 'true' else ''} \
	${@bb.utils.contains("IMAGE_FEATURES", "swupdate", d.getVar('SWUPDATE_THINGS'), "", d)} \
	udhcpd-iface-config \
	"

# Skip packagegroup-base to reduce the number of packages built. Thus, we need
# to include the MACHINE_EXTRA_ stuff ourselves.
IMAGE_INSTALL_MACHINE_EXTRAS ?= "packagegroup-machine-base"

IMAGE_INSTALL = "\
	packagegroup-core-boot \
	packagegroup-core-ssh-dropbear \
	packagegroup-distro-base \
	bootscript \
	${IMAGE_INSTALL_MACHINE_EXTRAS} \
	${ROOTFS_PKGMANAGE} \
	${MY_THINGS} \
	"

# Reduce dropbear host key size to reduce boot time by about 5 seconds
DROPBEAR_RSAKEY_SIZE="1024"

# Postprocessing to reduce the amount of work to be done
# by configuration scripts
myimage_rootfs_postprocess() {
	# Run populate-volatile.sh at rootfs time to set up basic files
	# and directories to support read-only rootfs.
	if [ -x ${IMAGE_ROOTFS}/etc/init.d/populate-volatile.sh ]; then
		echo "Running populate-volatile.sh"
		${IMAGE_ROOTFS}/etc/init.d/populate-volatile.sh
	fi
	# For sysvinit and similar, set up links. For systemd, no changes.
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'false', 'true', d)}
	then
		rm -rf ${IMAGE_ROOTFS}/media/* ${IMAGE_ROOTFS}/mnt
		ln -f -s media ${IMAGE_ROOTFS}/mnt
		rm -rf ${IMAGE_ROOTFS}/tmp
		ln -s var/volatile/tmp ${IMAGE_ROOTFS}/tmp
		rm -f ${IMAGE_ROOTFS}/etc/resolv.conf
		ln -s ../var/run/resolv.conf ${IMAGE_ROOTFS}/etc/resolv.conf
		rm -rf ${IMAGE_ROOTFS}/dev/*
		# Make links relative
		rm -f ${IMAGE_ROOTFS}/var/run ${IMAGE_ROOTFS}/var/tmp ${IMAGE_ROOTFS}/var/log
		ln -s volatile/tmp ${IMAGE_ROOTFS}/var/tmp
		ln -s volatile/log ${IMAGE_ROOTFS}/var/log
		ln -s ../run ${IMAGE_ROOTFS}/var/run
	fi

	ln -s ${DEVICETREE} ${IMAGE_ROOTFS}/boot/system.dtb

	echo 'DROPBEAR_RSAKEY_ARGS="-s ${DROPBEAR_RSAKEY_SIZE}"' >> ${IMAGE_ROOTFS}${sysconfdir}/default/dropbear
}
ROOTFS_POSTPROCESS_COMMAND += "myimage_rootfs_postprocess ; "
