require swu-revision.inc

LICENSE = "CLOSED"

SWUPDATE_THINGS = "\
   swupdate \
   swupdate-www \
   u-boot-fw-utils \
   kernel-devicetree \
   "

rootfs_add_swu_metadata() {
   # Hardware revision for SWUpdate
   echo "${SWU_BOARD_HWREVISION}" > ${IMAGE_ROOTFS}${sysconfdir}/hwrevision
}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_add_swu_metadata;"
