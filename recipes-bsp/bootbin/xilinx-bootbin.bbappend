FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# For MPSoC devices, enable outer cache (i.o.w. make the HPC ports work)
BIF_COMMON_ATTR:append:zynqmp = " init"
BIF_COMMON_ATTR[init] = "initouter.int"

SRC_URI:append:zynqmp = " file://initouter.int"
do_compile:prepend:zynqmp() {
	cp ${UNPACKDIR}/initouter.int ${B}/
}
