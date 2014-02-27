#!/bin/sh
test -f /var/run/udhcpd.$IFACE.pid || exit 0

DAEMON=/usr/sbin/udhcpd
NAME=udhcpd
DESC="Busybox UDHCP Server"

set -e

echo -n "stopping $DESC: $NAME for $IFACE... "
/sbin/start-stop-daemon -K -p /var/run/udhcpd.$IFACE.pid -n $NAME
echo "done."

