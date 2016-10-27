if [ -z "${MACHINE}" ]
then
	echo "MACHINE environment is not set. Set it before calling this"
	echo "script. Note that 'sudo' will not pass your environment"
	echo "along."
	exit 1
fi
if [ -z "${IMAGE}" ]
then
	echo "IMAGE environment is not set. Set it before calling this"
	echo "script. Note that 'sudo' will not pass your environment"
	echo "along."
	exit 1
fi
if [ -z "${IMAGE_ROOT}" ]
then
	IMAGE_ROOT=tmp-glibc/deploy/images/${MACHINE}
fi
# Ubuntu <14 uses /media for mounts, Ubuntu 14 uses /media/$USER
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
if [ ! -w ${MEDIA}/boot ]
then
    # For some reason, vfat systems get mounted with CASE charactors under
    # some distro's.
    if [ ! -w ${MEDIA}/BOOT ]
    then
        echo "${MEDIA_BOOT} is not accesible. Are you root (sudo me),"
        echo "is the SD card inserted, and did you partition and"
        echo "format it with partition_sd_card.sh?"
        exit 1
    else
        MEDIA_BOOT=${MEDIA}/BOOT
    fi
else
    MEDIA_BOOT=${MEDIA}/boot
fi
if [ ! -w ${MEDIA}/rootfs ]
then
	echo "${MEDIA}/rootfs is not accesible. Are you root (sudo me),"
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
DO_UNMOUNT=1
while [ ! -z "$1" ]
do
	if [ "$1" == "-n" ]
	then
		DO_UNMOUNT=0
	fi
	shift
done
if [ -z "${DTB}" ]
then
	DTB="uImage-*-adv7511.dtb"
fi
set -e
if [ -z "${SD_BOOTSCRIPT}" ]
then
	SD_BOOTSCRIPT=autorun.scr
fi
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
	cp ${IMAGE_ROOT}/BOOT.bin ${MEDIA_BOOT}/BOOT.BIN
	if [ -e ${IMAGE_ROOT}/u-boot.img ]
	then
		cp ${IMAGE_ROOT}/u-boot.img ${MEDIA_BOOT}/
	fi
	cp ${IMAGE_ROOT}/${SD_BOOTSCRIPT} ${MEDIA_BOOT}/autorun.scr
	cp ${IMAGE_ROOT}/uImage ${MEDIA_BOOT}/
	cp ${IMAGE_ROOT}/${DTB} ${MEDIA_BOOT}/devicetree.dtb
else
	tar xaf ${IMAGE_ROOT}/boot.tar.gz --no-same-owner -C ${MEDIA_BOOT}
fi
for FS in ubi squashfs-lzo squashfs-xz
do
	if [ -f ${IMAGE_ROOT}/${IMAGE}*-${MACHINE}.${FS} ]
	then
		cp ${IMAGE_ROOT}/${IMAGE}*-${MACHINE}.${FS} ${MEDIA_DATA}
	fi
done
echo "Writing rootfs..."
if [ ! -f dropbear_rsa_host_key -a -f ${MEDIA}/rootfs/etc/dropbear/dropbear_rsa_host_key ]
then
	cp ${MEDIA}/rootfs/etc/dropbear/dropbear_rsa_host_key .
	chmod 666 dropbear_rsa_host_key
fi
rm -rf ${MEDIA}/rootfs/*
tar xzf ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.tar.gz -C ${MEDIA}/rootfs
if [ -f dropbear_rsa_host_key ]
then
	install -d ${MEDIA}/rootfs/etc/dropbear
	install -m 600 dropbear_rsa_host_key ${MEDIA}/rootfs/etc/dropbear/dropbear_rsa_host_key
fi
if [ ! -z "${FPGA_BOOT_IMAGE}" ]
then
	cp ${MEDIA}/rootfs/${FPGA_BOOT_IMAGE} ${MEDIA_BOOT}/fpga.bin
fi

if [ $DO_UNMOUNT -ne 0 ]
then
	sleep 1
	echo -n "Unmounting"
	for p in ${MEDIA_BOOT} ${MEDIA}/rootfs ${MEDIA}/data
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
