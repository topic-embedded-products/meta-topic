SUMMARY = "Kernel driver module for a dummy mediactl"
require kernel-module-topic.inc
LIC_FILES_CHKSUM = "file://topic-mediactl.c;beginline=1;endline=1;md5=50d2ba0afecd20f74c12a4bdbcfcfe61"

PV = "0+${SRCPV}"
PKGV = "0+${GITPKGV}"

SRCREV = "d54ceaef5a2bae74fbe18f2fab2c92513715e403"

# Automatically load at boot
KERNEL_MODULE_AUTOLOAD = "${MODULE}"
