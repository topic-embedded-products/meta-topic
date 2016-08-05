DESCRIPTION = "Boot image for Miami without Florida"

# To boot this image via JTAG, which is initially the only way to boot a Vice:
# Using XMD:
#  Load u-boot.bin at 0x4000000
#  Load kernel at 0x3000000
#  Load devicetree at 0x2A00000
#  Load squash image at 0x8000000
# Start u-boot (using XMD "con 0x4000000" command), then via serial:
#  setenv bootargs "console=ttyPS0,115200 root=/dev/ram0 initrd=0x8000000,0x${filesize}"
#  bootm 0x3000000 - 0x2A00000

require my-image.bb

IMAGE_FEATURES += "read-only-rootfs"
IMAGE_FSTYPES = "squashfs-xz"

# No packaging
ROOTFS_PKGMANAGE = ""

MY_THINGS = "\
	modutils-loadscript \
	${@bb.utils.contains("IMAGE_FSTYPES", "ubi", "mtd-utils-ubifs" , "", d)} \
	i2c-tools \
	"
