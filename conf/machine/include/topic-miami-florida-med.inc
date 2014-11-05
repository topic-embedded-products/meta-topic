# Florida board common components
require conf/machine/include/topic-miami-florida.inc

KERNEL_MODULE_AUTOLOAD += "spi-cadence"

# Extra drivers to load as modules
MACHINE_EXTRA_RRECOMMENDS += "\
	kernel-module-ads129x \
	kernel-module-spi-cadence \
	"
