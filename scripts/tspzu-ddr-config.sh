#!/bin/sh -e
# Programs the DDR settings into EEPROM.
# 
# Warning: Incorrect settings may brick the board.

F=`ls /sys/bus/nvmem/devices/1-0051*/nvmem`
echo -n "Current setting: "
dd if=$F bs=1 count=4 skip=3944 2> /dev/null | hexdump -e '4/1 "%02x"'
echo "."

density=$1
bw=$2
ecc=$3

if [ -z "$density" ]
then
  echo "Usage: $0 density buswidth ecc"
  echo "   density: 8 or 16"
  echo "   buswidth: 32 or 64"
  echo "   ecc: 0 or 1"
  echo "Example: $0 16 64 1"
  echo "         For 8GB board with ECC enabled"
  echo "WARNING: Incorrect settings may brick the board."
  exit 1
fi

if [ "$density" -eq 8 ]
then
  id="\x03"
elif [ "$density" -eq 16 ]
then
  id="\x04"
else
  echo "density must be 8 or 16"
  exit 1
fi

if [ "$bw" -eq 64 ]
then
  w="\x04"
elif [ "$bw" -eq 32 ]
then
  w="\x02"
else
  echo "buswidth must be 32 or 64"
  exit 1
fi

if [ "$3" -eq 1 ]
then
  ecc="\x01"
else
  ecc="\x00"
fi

ddr="\xff${id}${w}${ecc}"
echo -n "New setting: " 
echo -n -e "${ddr}" | hexdump -e '4/1 "%02x"'

/usr/sbin/i2cset -y -f 1 0x51 0x80 0x00 0x00 i
echo -n -e "${ddr}" | dd of=$F bs=1 seek=3944
/usr/sbin/i2cset -y -f 1 0x51 0x80 0x00 0x08 i

echo "EEPROM programmed."
