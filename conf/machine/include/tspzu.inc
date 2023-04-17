# machine: Topic System-on-Module Plus w/ Zynq Ultrascale

FPGA_FAMILY_SHORT ?= "xczu"
DEVICETREE ?= "devicetree/zynqmp-topic-miamiplusmp.dtb"

require conf/machine/include/topic-miamimp.inc

# Add SOM to overrides, takes precedence over topic-miamimp
SOM_FAMILY .= ":tspzu"

# Default XSA to create PMUFW and FSBL
TOPIC_XSA_DESIGN_VERSION = "194+3aaae37"
TOPIC_XSA_DESIGN_NAME = "fpga-hardware-tdp-reference-ecc-${TOPIC_XSA_DESIGN_VERSION}.xsa"

