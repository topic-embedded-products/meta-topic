# The miami lite boards have less I/O connectors and slightly different hardware
require conf/machine/include/topic-miami.inc

# This makes the "topic-miamilite" override work
SOM_FAMILY = "topic-miamilite"

# But the "topic-miami" override applies as well, with lower priority
MACHINEOVERRIDES =. "topic-miami:"

# Different MIO configuration, hence different u-boot
UBOOT_MACHINE = "topic_miamilite_config"
