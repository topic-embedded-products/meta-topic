FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

FW_ENV ?= ""
FW_ENV:topic-miami   = "file://fw_env_mtd2_8000_2000.config"
FW_ENV:topic-miamimp = "file://fw_env_mtd2_20000_20000.config"
SRC_URI:append = " ${FW_ENV}"

do_install:append() {
    if [ ! -z ${FW_ENV} ] ; then
        install -d ${D}${sysconfdir}
        install -m 644 ${WORKDIR}/fw_env_*.config ${D}${sysconfdir}/fw_env.config
    fi
}
