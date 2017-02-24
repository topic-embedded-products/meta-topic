# The miami lite boards have less I/O connectors and slightly different hardware
require conf/machine/include/topic-miami.inc

# This makes the "topic-miamilite" override work
SOM_FAMILY = "topic-miamilite"

# But the "topic-miami" override applies as well, with lower priority
MACHINEOVERRIDES =. "topic-miami:"

# Different MIO configuration, hence different u-boot
UBOOT_MACHINE = "topic_miamilite_config"

# Use QSPI flash with 128k sector size (dual parallel qspi flash)
MKUBIFS_ARGS = "-m 1 -e 130944 -c 472"
UBINIZE_ARGS = "-m 1 -p 128KiB"
UBI_VOLNAME = "qspi-rootfs"
