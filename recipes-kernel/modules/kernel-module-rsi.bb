DESCRIPTION = "Redpine WIFI module"
require kernel-module-topic.inc
MODULE = "rsi-91x-sdio"
RRECOMMENDS_${PN} = "firmware-rsi-91x"
SRCREV = "e4bae633f72b9c337bc10ed5c61db9b7d98a3064"

do_install_append() {
	install -d ${D}${sysconfdir}/modules-load.d
	echo "${MODULE}" > ${D}${sysconfdir}/modules-load.d/${MODULE}.conf
}
