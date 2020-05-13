
# Use our patched repo
SRCREV = "6cfab56ef1dc19e0243679ff7d92a2645c8e71f0"
SRC_URI = "git://github.com/topic-embedded-products/xlnx-embeddedsw.git;protocol=https;branch=topic-2019.2"

# Copy the pm_cfg_obj.c (makefile uses wildcard and will pick it up automatically)
do_configure_append() {
	sed 's!"pm_defs.h"!"../../../sw_services/xilpm/src/zynqmp/client/common/pm_defs.h"!' ../../zynqmp_fsbl/misc/pm_cfg_obj.c > pm_cfg_obj.c
}
