FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
FILESEXTRAPATHS_prepend_petalinux := "${THISDIR}/${BPN}/petalinux:"


SRC_URI_append_petalinux = " file://topic_tdkzl_defconfig "

SRC_URI_append_tdkz15 = " file://0001-Support-autodetect-of-mermory-size-for-miami.patch "
SRC_URI_append_tdkz30 = " file://0001-Support-autodetect-of-mermory-size-for-miami.patch "

SRC_URI_append_zynq = " file://0001-UBI-support-u-boot.patch "

SRC_URI_append_tdkz15 = " file://0002-tdkz-for-ubi-set-hardcode-flash-size.patch "
SRC_URI_append_tdkz30 = " file://0002-tdkz-for-ubi-set-hardcode-flash-size.patch "

# file://0001-arm64-zynq_sdhci-Resolve-failed-mmc-tuning-due-to-gc.patch \
# file://0021-Fix-Makefiles-for-topic-boards.patch \
# Glitches on serial input interrupt the boot sequence on some boards, use
# a particular key (space) to stop autoboot instead of any key.
SRC_URI_append_topic-miamimp = " file://must-press-space-to-stop-autoboot.cfg"
SRC_URI_append_topic-miami = " file://must-press-space-to-stop-autoboot.cfg"

SRC_URI_append_zynqmp = " file://topic_zynqmp.cfg"

SRC_URI_append_tdpzu9 = " file://topic_tdpzu9.cfg"

SRC_URI_append_tdkz15 = " file://topic_tdkz.cfg"
SRC_URI_append_tdkz30 = " file://topic_tdkz.cfg"

SRC_URI_append_tdkzu9 = " file://topic_tdkzu.cfg"
SRC_URI_append_tdkzu15 = " file://topic_tdkzu.cfg"

SRC_URI_append_xdpzu7 = " file://topic_miamimp_xilinx_xdp.cfg"

SRC_URI_append_petalinux = " file://fastboot.cfg"
SRC_URI_append_petalinux = " file://boot.h"


EXTRACOMPILEDEPENDS = ""
EXTRACOMPILEDEPENDS_zynqmp = "arm-trusted-firmware:do_deploy"

do_configure_prepend_petalinux() {
    cat ${WORKDIR}/boot.h >> ${S}/include/configs/xilinx_zynqmp.h
}
# Add PMU and ATF
do_compile[depends] += "${EXTRACOMPILEDEPENDS}"

