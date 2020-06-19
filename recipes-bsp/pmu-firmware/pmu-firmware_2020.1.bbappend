
# Use our patched repo
SRCREV = "cdebc09459ad23e3c34aa7f96c07a47429c58042"
SRC_URI = "git://github.com/topic-embedded-products/xlnx-embeddedsw.git;protocol=https;branch=topic-2020.1"

# Copy the pm_cfg_obj.c (makefile uses wildcard and will pick it up automatically)
do_configure_append() {
	sed 's!"pm_defs.h"!"../../../sw_services/xilpm/src/zynqmp/client/common/pm_defs.h"!' ../../zynqmp_fsbl/misc/pm_cfg_obj.c > pm_cfg_obj.c
}
