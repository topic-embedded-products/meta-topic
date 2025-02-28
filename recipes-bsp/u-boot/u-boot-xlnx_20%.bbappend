FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# Glitches on serial input interrupt the boot sequence on some boards, use
# a particular key (space) to stop autoboot instead of any key.
# Speed up boot by setting boot delays to zero and remove environment loading
# Remove support for net, sata, video to reduce the bootloader size
# Use UBI to boot from QSPI. Add support for squashfs.
SRC_URI:append:topic-miamimp = "\
	file://must-press-space-to-stop-autoboot.cfg \
	file://no-bootdelay.cfg \
	file://no-env.cfg \
	file://no-network.cfg \
	file://no-sata.cfg \
	file://no-video.cfg \
	file://support-ubi-boot.cfg \
	file://support-squashfs.cfg \
	file://0001-zynqmp-Boot-with-UBI-from-QSPI.patch \
	"

SRC_URI:append:tspzu = "\
	file://enable-of-board-setup.cfg \
	file://0001-zynqmp-Detect-and-fixup-memory-config-on-topic-mpsoc.patch \
	"
