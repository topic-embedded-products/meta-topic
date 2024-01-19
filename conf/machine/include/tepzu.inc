# machine: Topic Florida Plus v2 with Zynq Ultrascale

MACHINE_BOARD = "tepzu"

require conf/machine/include/tspzu.inc

# Override XSA, serdes and clock config is different
TOPIC_XSA_DESIGN_VERSION = "280+38e5dd1"
TOPIC_XSA_DESIGN_NAME = "fpga-hardware-${MACHINE}-reference-${TOPIC_XSA_DESIGN_VERSION}.xsa"
TOPIC_XSA_DESIGN_SHA256SUM = "40d00c0ada816b7589d54eebfeb716db8a03ad8bf0228b8e8b25e3573d6d3dd9"

BOARD_PL_REFERENCE = "dtb-tepzu-reference"

MACHINE_WIFI_DRIVERS = "\
	kernel-module-cfg80211 \
	kernel-module-mac80211 \
	kernel-module-libarc4 \
	kernel-module-iwlwifi \
	kernel-module-iwlmvm \
	linux-firmware-iwlwifi-ax200 \
	"
MACHINE_BT_DRIVERS = "\
	kernel-module-bluetooth \
	kernel-module-btmrvl \
	kernel-module-btusb \
	linux-firmware-ibt-20 \
	"

# Extra packages to install for the board
MACHINE_EXTRA_RRECOMMENDS += "\
	kernel-module-g-ether \
	kernel-module-g-mass-storage \
	kernel-module-u-ether \
	kernel-module-usb-f-ecm \
	kernel-module-usb-f-ecm-subset \
	kernel-module-usb-f-mass-storage \
	kernel-module-usb-f-rndis \
	kernel-module-topic-pl-fanctrl \
	pwm-fancontrol \
	${MACHINE_WIFI_DRIVERS} \
	${MACHINE_BT_DRIVERS} \
	"

MACHINE_FEATURES += "rtc usbhost usbgadget ethernet wifi bluetooth powerkey"

DEVICETREE = "devicetree/${MACHINE_BOARD}.dtb"

KERNEL_MODULE_AUTOLOAD += "g_ether"
