FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append = "\
	file://pmu-firmware-zynqmp-pmu.bin.xz \
	file://0001-board-topic-Detect-RAM-size-at-boot.patch \
	file://0002-board-topic_miamilite-Support-cost-reduced-version.patch \
	file://0003-configs-topic_miami.h-Use-same-partitioning-for-USB-.patch \
	file://0004-topic_miami-Update-configuration.patch \
	file://0005-topic-miami-Increase-QSPI-partitions-for-u-boot-and-.patch \
	file://0006-board-zynqmp-Fix-for-wrong-AMS-setting-by-ROM.patch \
	file://0007-ARM-zynqmp-Add-support-for-the-topic-miamimp-system-.patch \
	file://0008-Add-support-for-zynqmp-xilinx-xdp-platform.patch \
	file://0009-Pinmux-WIFI-or-SD-based-on-card-detect-GPIO-input-st.patch \
	file://0010-board-topic-zynqmp-Implement-SPL-boot-fallbacks.patch \
	file://0011-topic-zynqmp-Enable-inner-shareable-transactions-to-.patch \
	file://0012-Disable-spi-transfer-breakable-by-CTRL-C-support.patch \
	file://0013-Support-UBIFS.patch \
	file://0014-spi_flash-do-not-write-out-of-bounds-when-doing-unal.patch \
	file://0014-ARM-zynqmp-Add-support-for-the-topic-miamiplusmp-SoM.patch \
	file://0015-topic-miami-support-new-filesystem-structure.patch \
	"

SRC_URI_append_topic-miami += "file://0002-miami-qspi-ubifs.cfg "

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

