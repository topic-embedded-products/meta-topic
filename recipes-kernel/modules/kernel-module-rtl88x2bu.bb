require kernel-module-topic.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://core/rtw_io.c;beginline=2;endline=8;md5=cd30f5806fdd68c2d204b403e8fef4f2"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "5.8.7.1+${SRCPV}"
PKGV = "5.8.7.1+${GITPKGV}"

# pick up latest rev for this module. Note this a deferred evaluation!
SRCREV = "${AUTOREV}"
#SRCREV = "1751f39dcbc2ab101cb17a09052945613bb9b78c"

SRC_URI = "git://github.com/cilynx/rtl88x2bu.git;branch=5.8.7.1_35809.20191129_COEX20191120-7777 \
           file://0001-Makefile-Sanitize-config-for-non-x86-platforms.patch \
           file://0002-Add-CONFIG_PLATFORM_OPENEMBEDDED-to-Makefile-as-defa.patch \
           file://0003-Don-t-use-__DATE__.patch \
"
SRC_URI[md5sum] = "1117c79a33f8c15ba32d19a68bdd91dc"

