#!/bin/sh
export PATH=/usr/bin:/bin:/usr/sbin:/sbin

# Read byte from I2C device DP159, if that's a 'D' the EIO board is present
I2C_BUS=`basename /sys/devices/platform/amba/a0004000.i2c/i2c-* | sed -n 's@i2c-\([0-9]\+\)@\1@p'`
ID=`i2cget -f -y $I2C_BUS 5e 0 2>/dev/null`
if [ "${ID}" = "0x44" ]
then
	echo "XDP EIO board detected"

	# Create overlay entry
	mkdir /sys/kernel/config/device-tree/overlays/eio

	echo -n xdp-eio.dtbo > /sys/kernel/config/device-tree/overlays/eio/path
fi
