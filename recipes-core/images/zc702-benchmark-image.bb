DESCRIPTION = "A console-only image with a few benchmark tools."

DEPENDS += "sd-bootscript"

IMAGE_FEATURES += "splash"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-basic \
    kernel-modules \
	nbench-byte \
	netperf \
    "

inherit core-image
