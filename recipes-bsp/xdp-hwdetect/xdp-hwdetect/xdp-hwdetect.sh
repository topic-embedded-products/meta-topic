#!/bin/sh

# Create overlay entry
mkdir /sys/kernel/config/device-tree/overlays/eio

# Read byte from I2C device DP159, if that's a 'D' the EIO board is present
ID=`i2cget -f -y 3 5e 0 2>/dev/null`
if [ "${ID}" = "0x44" ]
then
	echo "XDP EIO board detected"
	echo -n xdp-eio.dtbo > /sys/kernel/config/device-tree/overlays/eio/path
fi
