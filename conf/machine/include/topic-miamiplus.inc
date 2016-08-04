# The miami plus boards have extra I/O connectors and slightly different hardware
require conf/machine/include/topic-miami.inc

# This makes the "topic-miamiplus" override work
SOM_FAMILY = "topic-miamiplus"

# But the "topic-miami" override applies as well, with lower priority
MACHINEOVERRIDES =. "topic-miami:"
