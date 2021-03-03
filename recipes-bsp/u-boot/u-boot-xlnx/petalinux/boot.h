#undef CONFIG_EXTRA_ENV_SETTINGS
#define CONFIG_EXTRA_ENV_SETTINGS \
	ENV_MEM_LAYOUT_SETTINGS \
	BOOTENV \
	DFU_ALT_INFO \
	"boot_targets=jtag mmc0 qspi0 usb0 usb1\0" \
	"mtdids=" CONFIG_MTDIDS_DEFAULT "\0" \
	"mtdparts=" CONFIG_MTDPARTS_DEFAULT "\0" \
	"bootcmd_ubi=run ubi_mount && ubifsload ${scriptaddr} /boot/boot.scr && source ${scriptaddr}\0"    \
  "scriptaddr=0x1900000\0"   \
	"ubi_mount=sf probe && ubi part qspi-rootfs && ubifsmount ubi0:qspi-rootfs && setenv devtype ubi\0" \
	"bootubipart=qspi-rootfs\0" \
	"bootubivol=qspi-rootfs\0" \
	"bootcmd_qspi0=run bootcmd_ubi\0"
