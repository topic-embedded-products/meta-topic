FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

TODO_PATCHES_THAT_DONT_APPLY = "\
	file://0011-Disable-spi-transfer-breakable-by-CTRL-C-support.patch \
	file://0016-dts-Add-spi-flash-to-compatible-list-for-miami-SOMs.patch \
"

SRC_URI:append = "\
	file://pmu-firmware-zynqmp-pmu.bin.xz \
	file://0001-board-topic-Detect-RAM-size-at-boot.patch \
	file://0002-board-topic_miamilite-Support-cost-reduced-version.patch \
	file://0003-configs-topic_miami.h-Use-same-partitioning-for-USB-.patch \
	file://0004-topic_miami-Update-configuration.patch \
	file://0005-topic-miami-Increase-QSPI-partitions-for-u-boot-and-.patch \
	file://0006-ARM-zynqmp-Add-support-for-the-topic-miamimp-system-.patch \
	file://0007-Add-support-for-zynqmp-xilinx-xdp-platform.patch \
	file://0008-Pinmux-WIFI-or-SD-based-on-bootmode-selection.patch \
	file://0009-mach-zynqmp-spl.c-Implement-more-SPL-boot-fallbacks.patch \
	file://0010-topic-zynqmp-Enable-inner-shareable-transactions-to-.patch \
	file://0011-Disable-spi-transfer-breakable-by-CTRL-C-support.patch \
	file://0012-topic-miamimp-Support-UBIFS.patch \
	file://0013-ARM-zynqmp-Add-support-for-the-topic-miamiplusmp-SoM.patch \
	file://0014-topic-miami-support-new-filesystem-structure.patch \
	file://0015-xdp-psu_init_gpl-Set-DDR-to-2133MHz.patch \
	file://0016-mach-zynqmp-spl.c-Remove-1-second-delay-at-boot.patch \
	file://0017-zynqmp-topic-boards-Add-no-1-8-v-quirk-to-SD1-contro.patch \
	file://0018-Fix-Makefiles-for-topic-boards.patch \
	file://0019-Split-miamiplusmp-into-tdpzu9-and-ttpzu9.patch \
	file://0020-zynqmp-topic-miamimp-psu_init_gpl-New-DDR-chips.patch \
	file://0021-ttpzu9-psu_init_gpl-Run-DDR-at-2400.patch \
	file://0022-xdp-Set-DDR-to-2400-MT-s.patch \
	file://0023-arch-arm-dts-Makefile-Fix-topic-board-errors.patch \
	file://0024-topic_miamimp-Move-CONFIG_SYS_SPI_U_BOOT_OFFS-to-def.patch \
	file://0025-dts-zynqmp-topic-Use-jedec-spi-nor-compatible-instea.patch \
	file://0026-topic-miamiplusmp-Enable-I2C-support-on-module.patch \
	file://0027-topic-miamimp-xilinx-xdp-Enable-CAN-controllers.patch \
	file://0028-tdpzu9-Board-v1r1.patch \
	file://0029-ttpzu9-psu_init-Use-100MHz-refclk-for-USB3.patch \
	file://0030-tdpzu9-ttpzu9-Add-initial-delay-to-allow-clock-chip-.patch \
	file://0031-configs-topic_miami-lite-Fix-boot-order-and-disable-.patch \
	file://0032-tdpzu9-Apply-pull-down-on-MIO39.patch \
	file://0033-board-topic-miamiplus-Do-not-toggle-pin-46-at-boot.patch \
	file://0034-xilinx-zynq-zynqmp-Return-ENVL_NOWHERE-instead-of-EN.patch \
	"

# Glitches on serial input interrupt the boot sequence on some boards, use
# a particular key (space) to stop autoboot instead of any key.
SRC_URI:append:topic-miamimp = " file://must-press-space-to-stop-autoboot.cfg"

EXTRACOMPILEDEPENDS = ""
EXTRACOMPILEDEPENDS:zynqmp = "arm-trusted-firmware:do_deploy"

# Add PMU and ATF
do_compile[depends] += "${EXTRACOMPILEDEPENDS}"
do_compile:prepend:zynqmp() {
	cp ${WORKDIR}/pmu-firmware-zynqmp-pmu.bin ${S}/board/topic/zynqmp/pmufw.bin
	cp ${DEPLOY_DIR_IMAGE}/arm-trusted-firmware.bin ${B}/arm-trusted-firmware.bin
}

do_compile:append:zynqmp() {
	cp ${S}/board/topic/zynqmp/fit_spl_atf.its ${B}/fit_spl_atf.its
	${B}/tools/mkimage -f ${B}/fit_spl_atf.its ${B}/u-boot.itb
}

