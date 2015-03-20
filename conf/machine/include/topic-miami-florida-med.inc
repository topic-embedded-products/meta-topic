# Florida board common components
require conf/machine/include/topic-miami-florida.inc

# Extra drivers to load as modules
BOARD_FPGA_PACKAGES += "\
	kernel-module-ads129x \
	kernel-module-spi-cadence \
	"
