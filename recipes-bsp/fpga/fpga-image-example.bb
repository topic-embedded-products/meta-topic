# TODO: Convert fpga-image to a bbclass
require recipes-bsp/fpga/fpga-image.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${META_ZYNQ_BASE}/COPYING;md5=751419260aa954499f7abaabaa882bbe"

GITHUB_TOPIC_URI ?= "git://github.com/topic-embedded-products"
SRC_URI = "${GITHUB_TOPIC_URI}/${BPN}"
SRCREV = "598ebc91170e6f5268710767b4404517b3a64df8"

inherit gitpkgv
PV = "1.${SRCPV}"
PKGV = "1.${GITPKGV}"

do_compile_prepend() {
	if [ -z ${DYPLO_DIR} ]; then
		bberror "Environment variable 'DYPLO_DIR' not set, please set this variable (with the path to where Dyplo is installed) in your local.conf file" 
		exit 1
	elif [ "${DYPLO_DIR}" == "" ]; then
		bberror "Environment variable 'DYPLO_DIR' is empty, please set this variable (with the path to where Dyplo is installed) in your local.conf file" 
		exit 1
	else
		if [ ! -d ${DYPLO_DIR} ]; then
			bberror "The path in environment variable 'DYPLO_DIR' is not valid, please set this variable (with the path to where Dyplo is installed) in your local.conf file"
			exit 1	
		fi
	fi

	if [ -z ${DYPLO_LICENSE_TYPE} ]; then
		bberror "Environment variable 'DYPLO_LICENSE_TYPE' not set, please set this variable (with the license purchased (demo, trial, student, full or kit)) in your local.conf file" 
		exit 1
	elif [ "${DYPLO_LICENSE_TYPE}" == "" ]; then
		bberror "Environment variable 'DYPLO_LICENSE_TYPE' not set, please set this variable (with the license purchased (demo, trial, student, full or kit)) in your local.conf file"
		exit 1
	else
		if [ ! "${DYPLO_LICENSE_TYPE}" == "demo" ] && [ ! "${DYPLO_LICENSE_TYPE}" == "trial" ] && [ ! "${DYPLO_LICENSE_TYPE}" == "student" ] && [ ! "${DYPLO_LICENSE_TYPE}" == "full" ] && [ ! "${DYPLO_LICENSE_TYPE}" == "kit"]; then
			bberror "The license in environment variable 'DYPLO_LICENSE_TYPE' is not valid, please set this variable (with the license purchased (demo, trial, student, full or kit)) in your local.conf file"
			exit 1		
		fi
	fi
}

# Export DYPLO_DIR and DYPLO_LICENSE_TYPE from local.conf
export DYPLO_DIR
export DYPLO_LICENSE_TYPE

