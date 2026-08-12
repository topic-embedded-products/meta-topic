require recipes-bsp/device-tree/dtb-overlay.inc

SUMMARY = "Devicetree overlay for tepzu boards"

RRECOMMENDS:${PN} += "\
    kernel-module-i2c-xiic \
    kernel-module-topic-pl-fanctrl \
    pwm-fancontrol \
    "

BITSTREAM:tepzu = "fpga-image-tepzu-reference"

COMPATIBLE_MACHINE = "^tepzu"
