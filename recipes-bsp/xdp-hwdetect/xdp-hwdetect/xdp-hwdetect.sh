#!/bin/sh
export PATH=/usr/bin:/bin:/usr/sbin:/sbin

# Create overlay entry
mkdir /sys/kernel/config/device-tree/overlays/eio

# Read byte from I2C device DP159, if that's a 'D' the EIO board is present
ID=`i2cget -f -y 3 5e 0 2>/dev/null`
if [ "${ID}" = "0x44" ]
then
	echo "XDP EIO board detected"
	echo -n xdp-eio.dtbo > /sys/kernel/config/device-tree/overlays/eio/path

	# Beeper on EIO board
	d=/sys/class/leds/eio-h2:beep
	if echo transient > $d/trigger
	then
		echo 5 > $d/duration
		echo 1 > $d/state
	fi
fi

# Setup IIO sensors
for f in /sys/bus/iio/devices/iio:device*/*_oversampling_ratio
do
  echo 16 > $f
done

# Setup GPS UART
stty -F /dev/ttyPS1 9600 -echo
echo 97 > /sys/class/gpio/export
echo out > /sys/class/gpio/gpio97/direction
# To actually enable GPS:
# echo 1 > /sys/class/gpio/gpio97/value

# Setup BLE UART
stty -F /dev/ttyS0 115200 crtscts
# To actually start bluetooth:
# BD_ADDR=a4:08:ea:e1:c6:66
# hciattach /dev/ttyS0 -t 10 bcm43xx 921600 flow nosleep $BD_ADDR bcm43xx_init
# hciconfig hci0 up
