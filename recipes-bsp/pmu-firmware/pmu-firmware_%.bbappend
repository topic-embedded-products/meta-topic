FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = "\
	file://0001-Give-PCAP-ctrl-back-to-ICAP-for-partial-programming-.patch \
	file://0001-Enable-watchdog-reset.patch \
"