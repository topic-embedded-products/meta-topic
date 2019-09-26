FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

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
	"

# Add PMU and ATF
do_compile_zynqmp[depends] += "arm-trusted-firmware:do_deploy"
do_compile_prepend_zynqmp() {
	cp ${WORKDIR}/pmu-firmware-zynqmp-pmu.bin ${S}/board/topic/zynqmp/pmufw.bin
	cp ${DEPLOY_DIR_IMAGE}/arm-trusted-firmware.bin ${B}/arm-trusted-firmware.bin
}

do_compile_append_zynqmp() {
	cp ${S}/board/topic/zynqmp/fit_spl_atf.its ${B}/fit_spl_atf.its
	${B}/tools/mkimage -f ${B}/fit_spl_atf.its ${B}/u-boot.itb
}
