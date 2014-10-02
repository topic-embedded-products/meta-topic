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
if [ ! -w /media/boot ]
then
	echo "/media/boot is not accesible. Are you root (sudo me),"
	echo "is the SD card inserted, and did you partition and"
	echo "format it with partition_sd_card.sh?"
	exit 1
fi
if [ ! -w /media/rootfs ]
then
	echo "/media/rootfs is not accesible. Are you root (sudo me),"
	echo "is the SD card inserted, and did you partition and"
	echo "format it with partition_sd_card.sh?"
	exit 1
fi
if [ -z "${IMAGE_ROOT}" ]
then
	IMAGE_ROOT=tmp-glibc/deploy/images/${MACHINE}
fi
if [ ! -f ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.tar.gz ]
then
	echo "Image '${IMAGE}' does not exist, cannot flash it:"
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
	DTB="uImage-${MACHINE}-dyplo.dtb"
fi
set -e
echo "Writing boot..."
rm -f /media/boot/*.ubi
cp ${IMAGE_ROOT}/BOOT.bin /media/boot/BOOT.BIN
if [ -e ${IMAGE_ROOT}/u-boot.img ]
then
	cp ${IMAGE_ROOT}/u-boot.img /media/boot/
fi
cp ${IMAGE_ROOT}/*.scr /media/boot/
cp ${IMAGE_ROOT}/uImage /media/boot/
cp ${IMAGE_ROOT}/${DTB} /media/boot/devicetree.dtb
if [ -f ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.ubi ]
then
	cp ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.ubi /media/boot/
fi
echo "Writing rootfs..."
if [ ! -f dropbear_rsa_host_key -a -f /media/rootfs/etc/dropbear/dropbear_rsa_host_key ]
then
	cp /media/rootfs/etc/dropbear/dropbear_rsa_host_key .
	chmod 666 dropbear_rsa_host_key
fi
rm -rf /media/rootfs/*
tar xzf ${IMAGE_ROOT}/${IMAGE}-${MACHINE}.tar.gz -C /media/rootfs
if [ -f dropbear_rsa_host_key ]
then
	install -d /media/rootfs/etc/dropbear
	install -m 600 dropbear_rsa_host_key /media/rootfs/etc/dropbear/dropbear_rsa_host_key
fi
if [ $DO_UNMOUNT -ne 0 ]
then
	sleep 1
	echo -n "Unmounting"
	for p in /media/boot /media/rootfs /media/data
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
