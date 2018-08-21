
# build the "config" object into the PMU binary
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0001-Load-XPm_ConfigObject-at-boot.patch;striplevel=5"
SRC_URI += "file://0002-pm_cfg_obj.c-Allow-access-to-SD0-node.patch;striplevel=5;apply=n"

# Copy the pm_cfg_obj.c (makefile uses wildcard and will pick it up automatically */
do_configure_append() {
	sed 's!"pm_defs.h"!"../../../sw_services/xilpm/src/common/pm_defs.h"!' ../../zynqmp_fsbl/misc/pm_cfg_obj.c > pm_cfg_obj.c
	quilt import -p 5 ${WORKDIR}/0002-pm_cfg_obj.c-Allow-access-to-SD0-node.patch
	quilt push
}
