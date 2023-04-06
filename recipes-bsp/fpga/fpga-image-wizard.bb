SUMMARY = "FPGA bitstream from local wizard"

# Expects the PATH_TO_BITSTREAMS variable to point to a location of a
# pre-built image. This must contain a "static.bit" bitstream for the
# full FPGA, and subdirectories with partial bitstreams in them.

require fpga-image.inc

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

# These should be provided by wizard, supplying a default here that is
# convenient for testing.
PATH_TO_BITSTREAMS ??= "/tmp/bitstreams"
PATH_TO_HW_EXPORT ??= "/tmp/SDK_export/SDK/SDK_Export/hw"

BOARD_DESIGN_URI = ""

S = "${WORKDIR}/src"

# Copy static.bit to the workdir. Create a symlink to the other
# bitstreams to fool the convert_bitstreams task into compiling the
# partial bitstreams.
do_compile() {
	cp ${PATH_TO_BITSTREAMS}/static.bit ${S}/fpga.bit
	ln -s -f ${PATH_TO_BITSTREAMS} ${S}/bitstreams

	if [ "${PATH_TO_DYPLO_LICENSE_FILE}" != "" ] && [ -f "${PATH_TO_DYPLO_LICENSE_FILE}" ]; then
		cp -f ${PATH_TO_DYPLO_LICENSE_FILE} ${S}/dyplo_license_file.lic
	fi
}

# Copy the dyplo_license_file if exists.
do_install:append() {
	if [ -f ${S}/dyplo_license_file.lic ]; then
		cp -f ${S}/dyplo_license_file.lic ${D}${datadir}/dyplo_license_file.lic
	fi
}


# Store the SDK files into the sysroot for other packages.
SYSROOT_PREPROCESS_FUNCS += "fpga_sysroot_preprocess"
fpga_sysroot_preprocess() {
	if [ -d ${PATH_TO_HW_EXPORT} ]
	then
		install -d ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk
		cp -r ${PATH_TO_HW_EXPORT}/* ${SYSROOT_DESTDIR}${datadir}/xilinx_sdk/
	else
		bbwarn "No SDK exported, cannot create u-boot SPL. Ensure that PATH_TO_HW_EXPORT points to the location of the ps7_init.* files."
	fi
}
