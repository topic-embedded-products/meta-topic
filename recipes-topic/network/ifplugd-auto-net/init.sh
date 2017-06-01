#!/bin/sh

IFPLUGDLIST="*[0-9]"
test ! -r /etc/default/ifplugd-auto-net || . /etc/default/ifplugd-auto-net

case "$1" in
start)
	echo -n "Start ifplugd:"
	cd /sys/class/net
	for i in $IFPLUGDLIST
	do
		echo -n " $i"
		if [ -x /etc/ifplugd.$i ]
		then
			IFACE=$i /etc/ifplugd.$i $1
		fi
		ifplugd -i $i -r /etc/ifplugd.auto
	done
	echo "."
	;;
stop)
	echo -n "Stop ifplugd:"
	cd /sys/class/net
	for i in $IFPLUGDLIST
	do
		echo -n " $i"
		ifplugd -i $i -k
		if [ -x /etc/ifplugd.$i ]
		then
			IFACE=$i /etc/ifplugd.$i $1
		fi
	done
	echo "."
	;;
*)
	echo "Usage: $0 {start|stop}"
	exit 1
	;;
esac

exit 0
