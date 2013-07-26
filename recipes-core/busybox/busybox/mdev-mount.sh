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
			MOUNTPOINT="/media/$MDEV"
			mkdir "$MOUNTPOINT"
			mount -t auto /dev/$MDEV "$MOUNTPOINT"
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

