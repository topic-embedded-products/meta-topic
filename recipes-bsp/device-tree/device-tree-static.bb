require device-tree-plain.bb


# We provide the sysroot dtb
PROVIDES += "device-tree virtual/dtb"
# dont install this and Xilinx' device-tree at the same time
RPROVIDES:${PN} += "device-tree"
RREPLACES:${PN} = "device-tree"
RCONFLICTS:${PN} = "device-tree"

do_deploy:append() {
    ln -s devicetree/${SYSTEM_DTB} ${DEPLOYDIR}/system.dtb
}
