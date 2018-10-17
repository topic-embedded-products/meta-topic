FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append = "\
	file://0001-zynqmp-Boot-from-SD-card-if-SPI-is-not-supported.patch \
	file://0002-board-topic-Detect-RAM-size-at-boot.patch \
	file://0003-topic-miamiplus-Run-CPU-at-800MHz-for-speedgrade-2.patch \
	file://0004-ARM-zynqmp-Add-support-for-the-topic-miamimp-system-.patch \
	file://0005-mmc-sdhci-Add-card-detect-method.patch \
	file://0006-board-topic_miamilite-Support-cost-reduced-version.patch \
	file://0007-configs-topic_miami.h-Use-same-partitioning-for-USB-.patch \
	file://0008-board-topic-miamiplus-Run-IO-PLL-at-1000-MHz.patch \
	file://0009-miamimp-Load-FPGA-bitstream-at-boot.patch \
	"

# Build the PMU firmware into boot.bin
DEPENDS_append_zynqmp = " zynqmp-pmu-pmu-firmware"
do_compile_zynqmp[depends] += "zynqmp-pmu-pmu-firmware:do_deploy"
do_compile_prepend_zynqmp() {
	cp ${DEPLOY_DIR_IMAGE}/pmu-${MACHINE}.bin ${S}/board/topic/zynqmp/pmufw.bin
}
