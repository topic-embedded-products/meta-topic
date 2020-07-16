SUMMARY = "Kernel driver module for PL fan controller"
require kernel-module-topic.inc

PV = "4+${SRCPV}"
PKGV = "4+${GITPKGV}"

SRCREV = "ca1e84464aba47e37a8cec426f5ff7c8bba7be4f"

# Automatically load at boot
KERNEL_MODULE_AUTOLOAD = "${MODULE}"
