FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# Apply extra patches to psu_init.c
EXTRA_PSUINIT_PATCH = ""
EXTRA_PSUINIT_PATCH:xdpzu7 = "xdpzu7_psu_init_sdio.patch"
EXTRA_PSUINIT_PATCH:tdkzu6 = "tdkzu_psu_init_dual_rank_support.patch"
EXTRA_PSUINIT_PATCH:tdkzu9 = "tdkzu_psu_init_dual_rank_support.patch"
EXTRA_PSUINIT_PATCH:tdkzu15 = "tdkzu_psu_init_dual_rank_support.patch"
EXTRA_PSUINIT_PATCH:tdpzu9 = "0001-tdkzu9-ddr-routines.patch"

SRC_URI:append = "\
	file://0001-xfsbl_initialization-Run-ECC-initialization-when-nee.patch \
	${@'${EXTRA_PSUINIT_PATCH}' and 'file://${EXTRA_PSUINIT_PATCH};apply=n' or ''} \
	file://ps_iic_eeprom.c file://ps_iic_eeprom.h \
	file://psu_init_ddr.inc file://psu_init_ddr_custom.inc \
	"

# The configure step generates the psu_init.c anew and doesn't actually use the one from the XSA
# To get our modifications into the FSBL, patch both psu_init.c files:
# fsbl-firmware/fsbl-firmware_plat/hw/psu_init.c is taken from the XSA file
# fsbl-firmware/fsbl-firmware/psu_init.c is what actually gets compiled
do_configure:append:zynqmp() {
	for p in fsbl-firmware/fsbl-firmware_plat/hw fsbl-firmware/fsbl-firmware
	do
		ln ${WORKDIR}/ps_iic_eeprom.c ${WORKDIR}/ps_iic_eeprom.h ${WORKDIR}/psu_init_ddr.inc ${WORKDIR}/psu_init_ddr_custom.inc ${S}/$p/
		sed -i "s#PSU_MASK_POLL_TIME 1100000#PSU_MASK_POLL_TIME 11000#g" ${S}/$p/psu_init.c
		pushd ${S}/$p
		for f in ${EXTRA_PSUINIT_PATCH}
		do
			patch -p 1 -i ${WORKDIR}/$f
		done
		popd
	done
}
