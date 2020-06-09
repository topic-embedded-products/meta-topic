
# Use our patched repo
SRCREV = "198bd10b433d37614ff2d44be4fbf443f17284c3"
SRC_URI = "git://github.com/topic-embedded-products/xlnx-embeddedsw.git;protocol=https;branch=topic-2019.2"

# Copy the pm_cfg_obj.c (makefile uses wildcard and will pick it up automatically)
do_configure_append() {
	sed 's!"pm_defs.h"!"../../../sw_services/xilpm/src/zynqmp/client/common/pm_defs.h"!' ../../zynqmp_fsbl/misc/pm_cfg_obj.c > pm_cfg_obj.c
}
