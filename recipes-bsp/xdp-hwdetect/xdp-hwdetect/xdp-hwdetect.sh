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

# Load the settings for camera 0

mknod /dev/start_stream c 243 0

yavta -w '0x0098c981 4' /dev/v4l-subdev2

#SONY IMX274 Sensor
media-ctl -d /dev/media0 -V "\"IMX274_0\":0  [fmt:SRGGB10_1X10/1920x1080 field:none]"
#MIPI CSI2-Rx Subsystem
media-ctl -d /dev/media0 -V "\"a00f0000.csiss\":1  [fmt:SRGGB10_1X10/1920x1080 field:none]"
media-ctl -d /dev/media0 -V "\"a00f0000.csiss\":0  [fmt:SRGGB8_1X8/1920x1080 field:none]"
#Demosaic IP
media-ctl -d /dev/media0 -V "\"a0140000.demosaic\":0  [fmt:SRGGB8_1X8/1920x1080 field:none]"
media-ctl -d /dev/media0 -V "\"a0140000.demosaic\":1  [fmt:RBG888_1X24/1920x1080 field:none]"

media-ctl -d /dev/media0 -V "\"a0100000.v_proc_ss\":0  [fmt:RBG888_1X24/1920x1080 field:none]"
media-ctl -d /dev/media0 -V "\"a0100000.v_proc_ss\":1  [fmt:RBG888_1X24/1920x1080 field:none]"

# Load the settings for camera 1

yavta -w '0x0098c981 4' /dev/v4l-subdev6

#SONY IMX274 Sensor
media-ctl -d /dev/media0 -V "\"IMX274_1\":0  [fmt:SRGGB10_1X10/1920x1080 field:none]"
#MIPI CSI2-Rx Subsystem
media-ctl -d /dev/media0 -V "\"a0180000.csiss\":1  [fmt:SRGGB10_1X10/1920x1080 field:none]"
media-ctl -d /dev/media0 -V "\"a0180000.csiss\":0  [fmt:SRGGB8_1X8/1920x1080 field:none]"
#Demosaic IP
media-ctl -d /dev/media0 -V "\"a01c0000.demosaic\":0  [fmt:SRGGB8_1X8/1920x1080 field:none]"
media-ctl -d /dev/media0 -V "\"a01c0000.demosaic\":1  [fmt:RBG888_1X24/1920x1080 field:none]"

media-ctl -d /dev/media0 -V "\"a0040000.v_proc_ss\":0  [fmt:RBG888_1X24/1920x1080 field:none]"
media-ctl -d /dev/media0 -V "\"a0040000.v_proc_ss\":1  [fmt:RBG888_1X24/1920x1080 field:none]"

#Set the color correction correctly

yavta -w '0x0098c9a1 80' /dev/v4l-subdev0
yavta -w '0x0098c9a2 55' /dev/v4l-subdev0
yavta -w '0x0098c9a3 35' /dev/v4l-subdev0
yavta -w '0x0098c9a4 24' /dev/v4l-subdev0
yavta -w '0x0098c9a5 40' /dev/v4l-subdev0

yavta -w '0x0098c9a1 80' /dev/v4l-subdev4
yavta -w '0x0098c9a2 55' /dev/v4l-subdev4
yavta -w '0x0098c9a3 35' /dev/v4l-subdev4
yavta -w '0x0098c9a4 24' /dev/v4l-subdev4
yavta -w '0x0098c9a5 40' /dev/v4l-subdev4
