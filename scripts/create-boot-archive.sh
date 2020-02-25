#!/bin/sh
# Script to create a tar file with the boot partition contents. Set DTB and
# MACHINE environments first, e.g.:
# MACHINE=topic-miami-florida-gen-xc7z015 DTB=topic-miami-florida-gen.dtb create-boot-archive.sh
if [ -z "${SD_BOOTSCRIPT}" ]
then
	SD_BOOTSCRIPT=boot.scr
fi

set -e
IMAGE_ROOT=tmp-glibc/deploy/images/${MACHINE}
if [ -d ${IMAGE_ROOT}/boot ]
then
	rm -rf ${IMAGE_ROOT}/boot
fi
mkdir ${IMAGE_ROOT}/boot
cp ${IMAGE_ROOT}/BOOT.bin ${IMAGE_ROOT}/boot/BOOT.BIN
cp ${IMAGE_ROOT}/u-boot.img ${IMAGE_ROOT}/boot/
cp ${IMAGE_ROOT}/${SD_BOOTSCRIPT} ${IMAGE_ROOT}/boot
cp ${IMAGE_ROOT}/uImage ${IMAGE_ROOT}/boot/
cp ${IMAGE_ROOT}/${DTB} ${IMAGE_ROOT}/boot/devicetree.dtb
tar czf ${IMAGE_ROOT}/boot.tar.gz -C ${IMAGE_ROOT}/boot/ .
rm -r ${IMAGE_ROOT}/boot
