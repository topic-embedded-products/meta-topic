#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#
SUMMARY = "Kernel module for Topic media ctl"
SECTION = "Test"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://topic-mediactl.c;beginline=1;endline=1;md5=50d2ba0afecd20f74c12a4bdbcfcfe61"
PR = "2"
inherit module gitpkgv

SRC_URI = "file://Makefile \ 
	   file://topic-mediactl.c "

S = "${WORKDIR}"


#Start at boot
KERNEL_MODULE_AUTOLOAD += "topic-mediactl"

