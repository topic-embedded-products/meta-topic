# Class for creating u-boot compatible boot scripts

inherit kernel-arch

DEPENDS += "u-boot-mkimage-native"

oe_mkimage_script () {
    mkimage -A ${UBOOT_ARCH} -O linux -T script -C none -a 0 -e 0 $*
}
