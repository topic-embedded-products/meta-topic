#!/bin/sh
export PATH=/usr/bin:/bin:/usr/sbin:/sbin

# No known expansion boards yet.

# Setup BLE UART
stty -F /dev/ttyS0 115200 crtscts
# TODO: All boards get the same address... EEPROM?
BD_ADDR=a4:88:aa:dd:cc:66
hciattach /dev/ttyS0 -t 10 bcm43xx 921600 flow nosleep $BD_ADDR bcm43xx_init &

