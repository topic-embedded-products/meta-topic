FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append_topic-miami = " file://fw_env.config"

# Appears to be present in u-boot.inc, but isn't executed with u-boot-fw-utils
do_install_append() {
    if [ -e ${WORKDIR}/fw_env.config ] ; then
        install -d ${D}${sysconfdir}
        install -m 644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
    fi
}
