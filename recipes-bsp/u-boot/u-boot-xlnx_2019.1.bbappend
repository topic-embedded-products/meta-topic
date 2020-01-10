FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
# Reduce the length of the version number, otherwise builds will fail because
# of sstate-cache signatures over 255 characters long, see:
#   https://bugzilla.yoctoproject.org/show_bug.cgi?id=13177
UBOOT_VERSION_EXTENSION = "-x2019.1"

SRC_URI_append = "\
	file://pmu-firmware-zynqmp-pmu.bin.xz \
	file://0001-arm64-zynqmp-fix-preprocessor-check-for-SPL_ZYNQMP_T.patch \
	file://0001-mmc-sdhci-Add-card-detect-method.patch \
	file://0002-board-topic-Detect-RAM-size-at-boot.patch \
	file://0003-board-topic_miamilite-Support-cost-reduced-version.patch \
	file://0004-configs-topic_miami.h-Use-same-partitioning-for-USB-.patch \
	file://0005-topic_miami-Update-configuration.patch \
	file://0006-topic-miami-Increase-QSPI-partitions-for-u-boot-and-.patch \
	file://0007-ARM-zynqmp-Add-support-for-the-topic-miamimp-system-.patch \
	file://0008-Add-support-for-zynqmp-xilinx-xdp-platform.patch \
	file://0009-Pinmux-WIFI-or-SD-based-on-card-detect-GPIO-input-st.patch \
	file://0010-board-zynqmp-Fix-for-wrong-AMS-setting-by-ROM.patch \
	file://0011-board-topic-zynqmp-Implement-SPL-boot-fallbacks.patch \
	file://0012-Add-usb-reset-for-miamimp.patch \
	file://0013-topic-zynqmp-Enable-inner-shareable-transactions-to-.patch \
	"

EXTRACOMPILEDEPENDS = ""
EXTRACOMPILEDEPENDS_zynqmp = "arm-trusted-firmware:do_deploy"

# Add PMU and ATF
do_compile[depends] += "${EXTRACOMPILEDEPENDS}"
do_compile_prepend_zynqmp() {
	cp ${WORKDIR}/pmu-firmware-zynqmp-pmu.bin ${S}/board/topic/zynqmp/pmufw.bin
	cp ${DEPLOY_DIR_IMAGE}/arm-trusted-firmware.bin ${B}/arm-trusted-firmware.bin
}

do_compile_append_zynqmp() {
	cp ${S}/board/topic/zynqmp/fit_spl_atf.its ${B}/fit_spl_atf.its
	${B}/tools/mkimage -f ${B}/fit_spl_atf.its ${B}/u-boot.itb
}

