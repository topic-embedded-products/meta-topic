 #!/bin/sh -e
CMD=$1
if [ $# -ne 1 ]
then
	echo "Usage: $0 command"
	exit 1
fi

if [ "$CMD" = "preinst" ]
then
	ubiattach -m 3 /dev/ubi_ctrl || true
elif [ "$CMD" = "postinst" ]
then
	reboot
else
	echo "Invalid command"
	exit 1
fi
