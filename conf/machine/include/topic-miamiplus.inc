# The miami plus boards have extra I/O connectors and slightly different hardware
require conf/machine/include/topic-miami.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-topic"

# This makes the "topic-miamiplus" override work
SOM_FAMILY = "topic-miamiplus"

# But the "topic-miami" override applies as well, with lower priority
MACHINEOVERRIDES =. "topic-miami:"

# Different flash layout, hence different u-boot
UBOOT_MACHINE = "topic_miamiplus_config"

# Use QSPI flash with 128k sector size (dual parallel qspi flash)
MKUBIFS_ARGS = "-m 1 -e 130944 -c 472"
UBINIZE_ARGS = "-m 1 -p 128KiB"
UBI_VOLNAME = "qspi-rootfs"

