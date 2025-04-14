PACKAGECONFIG += "${@bb.utils.contains('MACHINE_FEATURES', 'mali400', 'lima kmsro', '', d)}"
