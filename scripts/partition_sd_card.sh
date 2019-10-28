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
sfdisk $1 << EOF
1M 63M 6
,960M
,

EOF

# format the DOS part
mkfs.vfat -n "boot" ${DEV}1

# Format the Linux rootfs part
mkfs.ext4 -m 0 -L "rootfs" -O sparse_super,dir_index  ${DEV}2

# Format the Linux data part, optimize for large files
mkfs.ext4 -m 0 -L "data" -O large_file,sparse_super,dir_index ${DEV}3

