DESCRIPTION = "TOPIC Miami(7015/7030) reference image"

require recipes-core/images/my-image.bb

MY_THINGS = "\
	modutils-loadscript \
	mtd-utils \
	${@base_contains("IMAGE_FSTYPES", "ubi", "mtd-utils-ubifs" , "", d)} \
	distro-feed-configs \
	fpga-image-miami-reference-design \
	"
