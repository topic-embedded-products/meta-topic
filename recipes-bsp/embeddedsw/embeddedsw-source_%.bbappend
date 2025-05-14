FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# PMU firmware
SRC_URI += "\
	file://0001-Enable-watchdog-reset.patch \
	file://0002-Give-PCAP-ctrl-back-to-ICAP-for-partial-programming-.patch \
"

# FSBL
SRC_URI += "\
	file://0001-xfsbl_initialization-Run-ECC-initialization-when-nee.patch \
"

SRC_URI:append:tspzu = "\
	file://0001-topic-Determine-ECC-range-programmatically.patch \
	"
