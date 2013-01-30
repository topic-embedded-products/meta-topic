#!/bin/sh
# Licensed under GPLv2
# Load kernel modules for hotpluggable devices

# Probe all USB modules so that drivers load automatically
grep -h '^usb:' `find /sys/devices/ -name modalias` | while read m
do
	modprobe $m > /dev/null 2> /dev/null
done

exit 0
