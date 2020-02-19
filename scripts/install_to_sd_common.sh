#!/bin/bash -e
# parse commandline
DO_UNMOUNT=true
DO_ROOTFS=true
while [ ! -z "$1" ]
do
	if [ "$1" == "-n" ]
	then
		DO_UNMOUNT=false
	elif [ "$1" == "-b" ]
	then
		DO_ROOTFS=false
	else
		echo "Usage: $0 [-n] [-b]"
		echo "-b  Write boot partition only, don't write the rootfs"
		echo "-n  Do not unmount media after writing"
		exit 1
	fi
	shift
done

if [ -z "${MACHINE}" ]
then
	echo "MACHINE environment is not set. Set it before calling this"
	echo "script. Note that 'sudo' will not pass your environment"
	echo "along."
	exit 1
fi

if [ -z "${IMAGE_ROOT}" ]
then
	IMAGE_ROOT=tmp-glibc/deploy/images/${MACHINE}
fi

# Ubuntu <14 uses /media for mounts, Ubuntu 14 uses /media/$USER
if [ -z "${SUDO_USER}" ]
then
	SUDO_USER="${USER}"
fi

if [ -d /media/${SUDO_USER} ]
then
	MEDIA=/media/${SUDO_USER}
else
    # Fedora/Arch/other systemd distro's use /var/run/media for mounts.
    if [ -d /var/run/media/${SUDO_USER} ]
    then
        MEDIA=/var/run/media/${SUDO_USER}
    else
        MEDIA=/media
    fi
fi

ROOTFS=${MEDIA}/sd-rootfs-a

if $DO_ROOTFS
then
	if [ -z "${IMAGE}" ]
	then
		echo "IMAGE environment is not set. Set it before calling this"
		echo "script. Note that 'sudo' will not pass your environment"
		echo "along."
		exit 1
	fi

	if [ ! -w ${ROOTFS} ]
	then
		echo "${ROOTFS} is not accesible. Are you root (sudo me),"
		echo "is the SD card inserted, and did you partition and"
		echo "format it with partition_sd_card.sh?"
		exit 1
	fi

	if [ ! -f ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.tar.gz ]
	then
		echo "Image '${IMAGE}' does not exist, cannot flash it."
		echo ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.tar.gz
		exit 1
	fi
fi

if [ ! -w ${MEDIA}/boot ]
then
    # Some distros mount vfat systems with CASE characters.
    if [ ! -w ${MEDIA}/BOOT ]
    then
        echo "${MEDIA}/boot is not accesible. Are you root (sudo me),"
        echo "is the SD card inserted, and did you partition and"
        echo "format it with partition_sd_card.sh?"
        exit 1
    else
        MEDIA_BOOT=${MEDIA}/BOOT
    fi
else
    MEDIA_BOOT=${MEDIA}/boot
fi

if [ -z "${DTB}" ]
then
	DTB="${DEVICETREE}"
fi

if [ -z "${DTB}" ]
then
	echo "Devicetree is not set, please provide DTB environment"
	exit 1
fi

set -e
if [ -d ${MEDIA}/data ]
then
	MEDIA_DATA=${MEDIA}/data
else
	MEDIA_DATA=${MEDIA_BOOT}
fi

echo "Writing boot..."
rm -f ${MEDIA_BOOT}/*.ubi ${MEDIA_BOOT}/*.squashfs-lzo

if [ -e ${IMAGE_ROOT}/BOOT.bin ]
then
	BOOT_BIN=BOOT.bin
else
	BOOT_BIN=boot.bin
fi

if [ -e ${IMAGE_ROOT}/${BOOT_BIN} ]
then
	cp ${IMAGE_ROOT}/${BOOT_BIN} ${MEDIA_BOOT}/BOOT.BIN
	for fn in u-boot.img u-boot.bin u-boot.itb
	do
		if [ -e ${IMAGE_ROOT}/${fn} ]
		then
			cp ${IMAGE_ROOT}/${fn} ${MEDIA_BOOT}
		fi
	done
else
	echo "${IMAGE_ROOT}/${BOOT_BIN} not found, attempt to use boot.tar.gz"
	tar xaf ${IMAGE_ROOT}/boot.tar.gz --no-same-owner -C ${MEDIA_BOOT}
fi

if $DO_ROOTFS
then
	if [ -d ${MEDIA}/data ]
	then
		echo "Writing data..."
		for FS in ubi ubifs squashfs-lzo squashfs-xz cpio.gz wic.gz
		do
			if [ -f ${IMAGE_ROOT}/${IMAGE}*-${MACHINE}.${FS} ]
			then
				cp ${IMAGE_ROOT}/${IMAGE}*-${MACHINE}.${FS} ${MEDIA_DATA}
			fi
		done
	fi

	echo "Writing sd-rootfs-a..."
	if [ ! -f dropbear_rsa_host_key -a -f ${ROOTFS}/etc/dropbear/dropbear_rsa_host_key ]
	then
		cp ${ROOTFS}/etc/dropbear/dropbear_rsa_host_key .
		chmod 666 dropbear_rsa_host_key
	fi
	rm -rf ${ROOTFS}/*
	tar xzf ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.tar.gz -C ${ROOTFS}
	if [ -f dropbear_rsa_host_key ]
	then
		install -d ${ROOTFS}/etc/dropbear
		install -m 600 dropbear_rsa_host_key ${ROOTFS}/etc/dropbear/dropbear_rsa_host_key
	fi
	if [ ! -z "${FPGA_BOOT_IMAGE}" ]
	then
		cp ${ROOTFS}/${FPGA_BOOT_IMAGE} ${MEDIA_BOOT}/fpga.bin
	fi

	if [ "${COPY_LOADABLE_MODULES}" = "1" ]
	then
		echo "Copying loadable modules directory to boot partition..."
		cp -r ${ROOTFS}/usr/share/loadable_modules ${MEDIA_BOOT}
	fi
fi

if $DO_UNMOUNT
then
	sleep 1
	echo -n "Unmounting"
	for p in ${MEDIA_BOOT} ${MEDIA}/sd-rootfs-a ${MEDIA}/sd-rootfs-b ${MEDIA}/data
	do
		if [ -d $p ]
		then
			echo -n " $p..."
			umount $p
			if [ -d $p ]
			then
				rmdir $p
			fi
		fi
	done
	echo ""
fi
echo "done."
