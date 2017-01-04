# Apply patch to make ATF work with SPL
# Thanks Michal for this tip
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://fix-atf-for-spl.patch"

# u-boot SPL wants the ATF to be called "atf-uboot.ub"
# Also create the specially-crafted atf-spi version for QSPI boot
do_deploy_append() {
	ln -sf ${ATF_BASE_NAME}.ub ${DEPLOYDIR}/atf-uboot.ub 
	mkimage -A arm64 -T firmware -C none -O u-boot \
		-a $BL31_BASE_ADDR -e $BL31_BASE_ADDR \
		-n "atf-for-qspi" -E -d ${OUTPUT_DIR}/bl31.bin \
		${DEPLOYDIR}/atf-spi.ub
}
