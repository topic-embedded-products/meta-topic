#!/bin/bash -e
if [ -z "$1" ] || [ ! -b "$1" ]
then
	echo "Usage: $0 [device]"
	echo "no device found."
	exit 1
else
	DEV=$1
fi

# Function to reverse an array
reverse () {
    local out=()
    while [ $# -gt 0 ]; do
        out=("$1" "${out[@]}")
        shift 1
    done
    echo "${out[@]}"
}

# umount all and wipe 8K of all partitions and block device.
# Start with the partitions first because on newer
# Ubuntu systems the partition table will be reloaded when writing to a block device
# The partitions are wiped because the kernel otherwise tries to remount them after the
# new partition table is written to the device
for n in $(reverse ${DEV}*)
do
	echo "Unmounting ${n}"
	umount $n || true
	dd if=/dev/zero of=$n bs=8192 count=1
done

# Wait until kernel reloaded partition table
echo -n "Waiting for partition table to reload "
sync ${DEV}*
partprobe ${DEV}
while [ -e "${DEV}1" ] || [ -e "${DEV}2" ] || [ -e "${DEV}3" ]
do
	echo -n "."
	sleep 0.1
done
echo ""

# Partition the disk, as 64M FAT16, 960MB root and the rest data
parted --align optimal --script ${DEV} -- \
	mklabel msdos \
	mkpart primary fat16 1MiB 64MiB \
	mkpart primary ext4 64MiB 1024MiB \
	mkpart primary ext4 1024MiB 2048MiB \
	mkpart primary ext4 2048MiB -1s \
	set 2 boot on

# Wait until kernel reloaded partition table
# The system is removing & adding the devices several times, therefore sleep for 1 second
sleep 1
echo -n "Waiting for partition table to reload "
while [ ! -e "${DEV}1" ] || [ ! -e "${DEV}2" ] || [ ! -e "${DEV}3" ]
do
	echo -n "."
	sleep 0.1
done
echo ""

# format the DOS part
mkfs.vfat -n "boot" ${DEV}1

# Format the Linux rootfs part
mkfs.ext4 -m 0 -L "sd-rootfs-a" -O sparse_super,dir_index  ${DEV}2
mkfs.ext4 -m 0 -L "sd-rootfs-b" -O sparse_super,dir_index  ${DEV}3

# Format the Linux data part, optimize for large files
mkfs.ext4 -m 0 -L "data" -O large_file,sparse_super,dir_index ${DEV}4

