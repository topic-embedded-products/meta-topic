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
if [ ! -f tmp-eglibc/deploy/images/${MACHINE}/${IMAGE}-${MACHINE}.tar.gz ]
then
	echo "Image '${IMAGE}' does not exist, cannot flash it."
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
cp tmp-eglibc/deploy/images/${MACHINE}/BOOT.bin /media/boot/BOOT.BIN
if [ -e tmp-eglibc/deploy/images/${MACHINE}/u-boot.img ]
then
	cp tmp-eglibc/deploy/images/${MACHINE}/u-boot.img /media/boot/
fi
cp tmp-eglibc/deploy/images/${MACHINE}/*.scr /media/boot/
cp tmp-eglibc/deploy/images/${MACHINE}/uImage /media/boot/
cp tmp-eglibc/deploy/images/${MACHINE}/${DTB} /media/boot/devicetree.dtb
if [ -f tmp-eglibc/deploy/images/${MACHINE}/${IMAGE}-${MACHINE}.ubi ]
then
	cp tmp-eglibc/deploy/images/${MACHINE}/${IMAGE}-${MACHINE}.ubi /media/boot/
fi
echo "Writing rootfs..."
if [ ! -f dropbear_rsa_host_key -a -f /media/rootfs/etc/dropbear/dropbear_rsa_host_key ]
then
	cp /media/rootfs/etc/dropbear/dropbear_rsa_host_key .
	chmod 666 dropbear_rsa_host_key
fi
rm -rf /media/rootfs/*
tar xzf tmp-eglibc/deploy/images/${MACHINE}/${IMAGE}-${MACHINE}.tar.gz -C /media/rootfs
cp /media/rootfs/usr/share/fpga.bin /media/boot/
if [ -f dropbear_rsa_host_key ]
then
	install -d /media/rootfs/etc/dropbear
	install -m 600 dropbear_rsa_host_key /media/rootfs/etc/dropbear/dropbear_rsa_host_key
fi
if [ $DO_UNMOUNT -ne 0 ]
then
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
