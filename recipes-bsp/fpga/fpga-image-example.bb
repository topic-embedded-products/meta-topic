# TODO: Convert fpga-image to a bbclass
require recipes-bsp/fpga/fpga-image.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

BOARD_DESIGN_URI = "git://github.com/topic-embedded-products/fpga-image-example.git"
SRCREV = "03f8ad2a71524e1851cfaac87c78c077906530e6"

inherit gitpkgv
PV = "1.${SRCPV}"
PKGV = "1.${GITPKGV}"

do_compile_prepend() {
	if [ -z ${DYPLO_DIR} ]; then
		bberror "Environment variable 'DYPLO_DIR' not set, please set this variable (with the path to where Dyplo is installed) in your local.conf file" 
		exit 1
	elif [ "${DYPLO_DIR}" == "" ]; then
		bberror "Environment variable 'DYPLO_DIR' is empty $DYPLO_DIR, please set this variable (with the path to where Dyplo is installed) in your local.conf file" 
		exit 1
	else
		if [ ! -d ${DYPLO_DIR} ]; then
			bberror "The path in environment variable 'DYPLO_DIR' is not valid, please set this variable (with the path to where Dyplo is installed) in your local.conf file"
			exit 1	
		fi
	fi
}

# Export DYPLO_DIR from local.conf
export DYPLO_DIR

