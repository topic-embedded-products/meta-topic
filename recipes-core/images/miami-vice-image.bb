DESCRIPTION = "Boot image for Miami without Florida"

require my-image.bb

IMAGE_FEATURES += "read-only-rootfs"
IMAGE_FSTYPES = "squashfs-xz"
# No FPGA
MY_LOGIC = ""
# No packaging
ROOTFS_PKGMANAGE = ""

MY_THINGS = "\
	modutils-loadscript \
	${MY_LOGIC} \
	mtd-utils \
	${@base_contains("IMAGE_FSTYPES", "ubi", "mtd-utils-ubifs" , "", d)} \
	i2c-tools \
	"
