PACKAGES =+ "${PN}-iwlwifi-ax200"

LICENSE:${PN}-iwlwifi-ax200 = "Firmware-iwlwifi_firmware"
FILES:${PN}-iwlwifi-ax200   = "\
	${nonarch_base_libdir}/firmware/iwlwifi-cc-a0-50.ucode \
	"
RDEPENDS:${PN}-iwlwifi-ax200 = "${PN}-iwlwifi-license"

# These are provided nowadays, but > 50 yields errors in our driver ...
#       1101228 Mar  9 12:34 iwlwifi-cc-a0-50.ucode
#       1261280 Mar  9 12:34 iwlwifi-cc-a0-59.ucode
#       1307180 Mar  9 12:34 iwlwifi-cc-a0-66.ucode
#       1329780 Mar  9 12:34 iwlwifi-cc-a0-72.ucode
#       1333296 Mar  9 12:34 iwlwifi-cc-a0-73.ucode
#       1334780 Mar  9 12:34 iwlwifi-cc-a0-74.ucode
#       1365484 Mar  9 12:34 iwlwifi-cc-a0-77.ucode
