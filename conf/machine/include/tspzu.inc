# machine: Topic System-on-Module Plus w/ Zynq Ultrascale

FPGA_FAMILY_SHORT ?= "xczu"
DEVICETREE ?= "devicetree/zynqmp-topic-miamiplusmp.dtb"

require conf/machine/include/topic-miamimp.inc

# Add SOM to overrides, takes precedence over topic-miamimp
SOM_FAMILY .= ":tspzu"

# Default XSA to create PMUFW and FSBL
TOPIC_XSA_DESIGN_VERSION = "275+c09563c"
TOPIC_XSA_DESIGN_NAME = "fpga-hardware-tdp-reference-ecc-${TOPIC_XSA_DESIGN_VERSION}.xsa"
TOPIC_XSA_DESIGN_SHA256SUM = "739768df14f3bc379739f287dcd980e8db2a9eeab116eebc0a527cbb93709935"

# XSERVER_EXT:tspzu = "xf86-video-armsoc"
