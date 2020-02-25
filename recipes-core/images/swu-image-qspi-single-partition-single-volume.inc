require swu-revision.inc

SUMMARY ?= "Image for SWUpdate"
SECTION = ""
LICENSE = "CLOSED"

IMAGEVERSION ?= "0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/qspi-single-partition-single-volume:"

# Add all local files to be added to the SWU
# sw-description must always be in the list.
# You can extend with scripts or wahtever you need
SRC_URI = " \
    file://sw-description \
    file://update.sh \
    "

IMAGE_DEPENDS = "${BASENAME}-image virtual/kernel"

# Only one @@...@@ per line gets replaced, so combine them here
ROOTFSNAME = "${BASENAME}-image-${MACHINE}.rootfs.ubifs"

# images and files that will be included in the .swu image
SWUPDATE_IMAGES = "${ROOTFSNAME}"

inherit swupdate
