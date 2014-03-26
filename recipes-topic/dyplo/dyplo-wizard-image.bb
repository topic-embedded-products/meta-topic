SUMMARY = "TOPIC DYPLO example image from wizard"

require recipes-core/images/my-image.bb

MY_LOGIC = "\
	fpga-image-wizard \
	"

MY_THINGS += "\
	kernel-module-dyplo \
	dyplo-utils \
	"
