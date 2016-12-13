PACKAGES =+ "${PN}-rsi-91x"

FILES_${PN}-rsi-91x = "/lib/firmware/rsi_91x.fw"

# Replace old recipe
RCONFLICTS_${PN}-rsi-91x = "firmware-rsi-91x"
RREPLACES_${PN}-rsi-91x = "firmware-rsi-91x"
