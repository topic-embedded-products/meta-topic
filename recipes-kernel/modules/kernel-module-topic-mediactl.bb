SUMMARY = "Kernel driver module for a dummy mediactl"
require kernel-module-topic.inc
LIC_FILES_CHKSUM = "file://topic-mediactl.c;beginline=1;endline=1;md5=50d2ba0afecd20f74c12a4bdbcfcfe61"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

PV = "0+${SRCPV}"
PKGV = "0+${GITPKGV}"

SRCREV = "8108d27c8cd15e27b1cd67403b1d756d8abdcbf3"

SRC_URI_append = "\
	file://0001-Update-kernel-module-to-kernel-version-5.4.patch \
	"

# Automatically load at boot
KERNEL_MODULE_AUTOLOAD = "${MODULE}"
