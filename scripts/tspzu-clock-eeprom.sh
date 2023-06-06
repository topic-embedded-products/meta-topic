#!/bin/sh -e
# Programs the base clock into EEPROM.
#
# This script reads the contents of the EEPROM and verifies that the clock
# setting is valid. When not correct, it takes the current frequency and writes
# that to the EEPROM. The script is formatted such that it can be copy-pasted
# directly into a terminal.

F=`ls /sys/bus/nvmem/devices/1-0051*/nvmem`
if [ `dd if=$F bs=1 count=1 skip=3983 2> /dev/null | hexdump -e '1/1 "%02x"'` = '02' ]
then
  echo "Already programmed"
else
  echo "Clock in EEPROM not valid"
  /usr/sbin/i2cset -y -f 1 0x51 0x80 0x00 0x00 i
  if [ `cat /sys/kernel/debug/clk/xtal_mux/clk_rate` = '38880000' ]
  then
    echo "Programming 38.88MHz"
    echo -e -n '\x00\x43\x51\x02' | dd of=$F bs=1 seek=3980
  else
    echo "Programming 50MHz"
    echo -e -n '\x80\xf0\xfa\x02' | dd of=$F bs=1 seek=3980
  fi
  /usr/sbin/i2cset -y -f 1 0x51 0x80 0x00 0x08 i
fi
