if [ -z "$1" ]
then
	echo "Usage: $0 [device]"
	echo "no device found."
	exit 1
else
	DEV=$1
fi
set -e

# Partition the disk, as 64M FAT16, 400MB root and the rest data
sfdisk -uM $1 << EOF
0 62 6
,400
,

EOF

# format the DOS part
mkfs.vfat -n "boot" ${DEV}1

# Format the Linux rootfs part
mkfs.ext4 -m 0 -L "rootfs" -b 2048 -N 24000 -O sparse_super,dir_index  ${DEV}2

# Format the Linux data part, optimize for large files
mkfs.ext4 -m 0 -L "data" -O large_file,sparse_super,dir_index,bigalloc -C 262144 ${DEV}3

mkdir /media/boot
mount ${DEV}1 /media/boot
mkdir /media/rootfs
mount ${DEV}2 /media/rootfs
mkdir /media/data
mount ${DEV}3 /media/data
