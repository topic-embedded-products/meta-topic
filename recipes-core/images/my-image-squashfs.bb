DESCRIPTION = "A read-only image for writing to flash"

require my-image.bb

IMAGE_FEATURES += "read-only-rootfs"
IMAGE_FSTYPES = "squashfs-xz"

# No packaging, pointless on a read-only system
ROOTFS_PKGMANAGE = ""
IMAGE_FEATURES_remove = "package-management"
