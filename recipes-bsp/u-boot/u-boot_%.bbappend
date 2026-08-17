FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += "\
    file://0001-zynq-topic-miami.dts-Use-fixed-partitions-binding-fo.patch \
    file://0002-configs-topic_miami-_defconfig-configuration-tweaks.patch \
    file://0003-mach-zynq-SPL-Boot-from-QSPI-in-memory-mapped-mode.patch \
    file://0004-topic_miami-Boot-from-QSPI-in-memory-mapped-mode.patch \
"
