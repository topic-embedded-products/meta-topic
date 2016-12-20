#!/bin/sh -e
cat /usr/share/fpga.bin > /dev/xdevcfg
result=`cat /sys/class/xdevcfg/xdevcfg/device/prog_done`
if [ $result -ne 1 ]; then
  echo "ERROR configuring FPGA, logic is not configured!"
  exit 1
fi
