FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Apply extra patches to psu_init.c
EXTRA_PSUINIT_PATCH = ""
EXTRA_PSUINIT_PATCH:tdkzu6 = "tdkzu_psu_init_dual_rank_support.patch"
EXTRA_PSUINIT_PATCH:tdkzu9 = "tdkzu_psu_init_dual_rank_support.patch"
EXTRA_PSUINIT_PATCH:tdkzu15 = "tdkzu_psu_init_dual_rank_support.patch"
EXTRA_PSUINIT_PATCH:tspzu = "0001-tspzu-ddr-routines.patch"

SRC_URI:append = "\
	${@'${EXTRA_PSUINIT_PATCH}' and 'file://${EXTRA_PSUINIT_PATCH};apply=n' or ''} \
	file://ps_iic_eeprom.c file://ps_iic_eeprom.h \
	file://psu_init_ddr.inc file://psu_init_ddr_custom.inc \
	"

# The configure step generates the psu_init.c anew and doesn't actually use the one from the XSA
# To get our modifications into the FSBL, patch both psu_init.c files:
# fsbl-firmware/fsbl-firmware_plat/hw/psu_init.c is taken from the XSA file
# fsbl-firmware/fsbl-firmware/psu_init.c is what actually gets compiled
# The files are links to external sources, un-link them before patching
do_configure:append:zynqmp() {
	echo  "Patching psu_init in S=${S} B=${B}"
	for p in fsbl-firmware/fsbl-firmware_plat/hw fsbl-firmware/fsbl-firmware_plat/zynqmp_fsbl fsbl-firmware/fsbl-firmware
	do
		cp -l ${UNPACKDIR}/ps_iic_eeprom.c ${UNPACKDIR}/ps_iic_eeprom.h ${UNPACKDIR}/psu_init_ddr.inc ${UNPACKDIR}/psu_init_ddr_custom.inc ${S}/$p/
		sed "s#PSU_MASK_POLL_TIME 1100000#PSU_MASK_POLL_TIME 11000#g" ${S}/$p/psu_init.c > ${S}/$p/psu_init.c.new
		rm ${S}/$p/psu_init.c
		mv ${S}/$p/psu_init.c.new ${S}/$p/psu_init.c
		pushd ${S}/$p
		for f in ${EXTRA_PSUINIT_PATCH}
		do
			patch -p 1 -i ${UNPACKDIR}/$f
		done
		popd
	done
}
