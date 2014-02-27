#!/bin/sh
CONF="/etc/udhcpd.${IFACE}.conf"
test -f "$CONF" || exit 0

DAEMON=/usr/sbin/udhcpd
NAME=udhcpd
DESC="Busybox UDHCP Server"

set -e

echo -n "starting $DESC: $NAME for $IFACE... "
/sbin/start-stop-daemon -S -b -n $NAME -p /var/run/udhcpd.$IFACE.pid -a $DAEMON -- $CONF
echo "done."
