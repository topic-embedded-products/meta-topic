#!/bin/sh -e
if [ -e /lib/firmware/pl.dtbo ]
then
    test -d /sys/kernel/config/device-tree/overlays/full || mkdir /sys/kernel/config/device-tree/overlays/full
    echo -n pl.dtbo > /sys/kernel/config/device-tree/overlays/full/path
else
    echo "Devicetree overlay not found, attempt to load fpga.bin"
    echo fpga.bin > /sys/class/fpga_manager/fpga0/firmware
fi
