 #!/bin/sh -e
CMD=$1
if [ $# -ne 1 ]
then
	echo "Usage: $0 command"
	exit 1
fi

if [ "$CMD" = "preinst" ]
then
	create_mmc_links
	prepare_filesystem /dev/emmc-rootfs-inactive /tmp/UPDATE-MOUNT
elif [ "$CMD" = "postinst" ]
then
	switch_mmc_boot_partition /dev/emmc-rootfs-inactive
	reboot
else
	echo "Invalid command"
	exit 1
fi
