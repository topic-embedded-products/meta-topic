#!/bin/sh

case "$ACTION" in
	add|"")
		ACTION="add"
		# check if already mounted
		if grep -q "^/dev/${MDEV} " /proc/mounts ; then
			# Already mounted
			exit 0
		fi
		DEVBASE=`expr substr $MDEV 1 3`
		if [ "${DEVBASE}" == "mmc" ] ; then
			DEVBASE=`expr substr $MDEV 1 7`
		fi
		# check for "please don't mount it" file
		if [ -f "/dev/nomount.${DEVBASE}" ] ; then
			# blocked
			exit 0
		fi
		# check for full-disk partition
		if [ "${DEVBASE}" == "${MDEV}" ] ; then
			if [ -d /sys/block/${DEVBASE}/${DEVBASE}*1 ] ; then
				# Partition detected, just quit
				exit 0
			fi
			if [ ! -f /sys/block/${DEVBASE}/size ] ; then
				# No size at all
				exit 0
			fi
			if [ `cat /sys/block/${DEVBASE}/size` == 0 ] ; then
				# empty device, bail out
				exit 0
			fi
		fi
		# first allow fstab to determine the mountpoint
		if ! mount /dev/$MDEV > /dev/null 2>&1
		then
			# no fstab entry, use automatic mountpoint
			if [ "${DEVBASE}" == "mmcblk0" ]
			then
				DEVICETYPE="mmc1"
			else
				REMOVABLE=`cat /sys/block/$DEVBASE/removable`
				if [ "${REMOVABLE}" -eq "0" ]; then
					# mount the first non-removable internal device on /media/hdd
					DEVICETYPE="hdd"
				else
					DEVICETYPE="usb"
				fi
			fi
			if grep -q " /media/$DEVICETYPE " /proc/mounts || grep -q -w "\s/media/$DEVICETYPE\s" /etc/fstab
			then
			        # $DEVICETYPE already mounted, or in fstab
				MOUNTPOINT="/media/$MDEV"
			else
				# Use mkdir as 'atomic' action, failure means someone beat us to the punch
				if mkdir "/media/$DEVICETYPE"
				then
					# /media/$DEVICETYPE is available
					MOUNTPOINT="/media/$DEVICETYPE"
				else
					mkdir "/media/$MDEV"
					MOUNTPOINT="/media/$MDEV"
				fi
			fi
			mount -t auto /dev/$MDEV $MOUNTPOINT
		fi
		;;
	remove)
		MOUNTPOINT=`grep "^/dev/$MDEV\s" /proc/mounts | cut -d' ' -f 2`
		if [ ! -z "$MOUNTPOINT" ]
		then
			umount "$MOUNTPOINT"
			rmdir "$MOUNTPOINT" 
		else
			umount /dev/$MDEV
		fi
		;;
	*)
		# Unexpected keyword
		exit 1
		;;
esac

