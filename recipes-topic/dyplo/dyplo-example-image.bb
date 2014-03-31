DESCRIPTION = "TOPIC Bricks DYPLO example image (no HDMI nor audio etc.)"

require recipes-core/images/my-image.bb

MY_THINGS = "\
	modutils-loadscript \
	mtd-utils \
	${@base_contains("IMAGE_FSTYPES", "ubi", "mtd-utils-ubifs" , "", d)} \
	distro-feed-configs \
	kernel-module-dyplo \
	dyplo-utils \
	fpga-image-example \
	dyplo-example-app \
	"
