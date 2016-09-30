#!/bin/sh
case "$1" in
start)
	echo -n "Start ifplugd:"
	cd /sys/class/net
	for i in *[0-9]
	do
		echo -n " $i"
		ifplugd -i $i -r /etc/ifplugd.auto
	done
	echo "."
	;;

stop)
	echo -n "Stop ifplugd:"
	cd /sys/class/net
	for i in *[0-9]
	do
		echo -n " $i"
		ifplugd -i $i -k
	done
	echo "."
	;;
*)
	echo "Usage: $0 {start|stop}"
	exit 1
	;;
esac

exit 0
