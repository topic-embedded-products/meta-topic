FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += "\
    file://0001-xilinx-Allow-alternative-boot-strategies-in-zynq-com.patch \
    file://0001-topic-Use-distro_boot-for-miami-boards.patch \
    file://0001-topic-Boot-from-QSPI-using-UBI-root-filesystem.patch \
    "
