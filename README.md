BSP for Topic development boards, like the Miami and its Florida carrier
boards.

This is an overlay intended for OpenEmbedded and/or Yocto.

# OpenEmbedded

Recommended way of building is to use the "topic-platform" repository:
https://github.com/topic-embedded-products/topic-platform

This will build a tested combination of the OE repositories and the meta-topic
layer.

For those who want to live on the cutting edge, you can try to build the current
master branches for all repositories using this script. This is not guaranteed
to work, since it always builds the "current state of affairs":

```
mkdir my-zynq
cd my-zynq
git clone git://git.openembedded.org/openembedded-core oe-core
git clone git://github.com/openembedded/meta-openembedded.git meta-oe
git clone git://git.openembedded.org/bitbake bitbake
git clone http://github.com/topic-embedded-products/meta-topic.git meta-topic
meta-topic/scripts/init-oe.sh
cd build

# Edit local.conf to match your setup.
vi conf/local.conf

# The default machine is "tdkz5", change it in the "profile" script or just
# export the MACHINE environment before building.

# Then build your first image and relax a bit:
. ./profile
nice bitbake my-image
````

Note that "my-image" was designed to be used with DISTRO=tiny. It
expects to run with busybox-mdev instead of udev.


# Yocto

For Yocto, clone this repository to a local directory, and add it to the
conf/bblayers.conf file. It should have a BBLAYERS that looks like this:
````
BBLAYERS ?= " \
  ${HOME}/poky/meta \
  ${HOME}/poky/meta-yocto \
  ${HOME}/poky/meta-yocto-bsp \
  ${HOME}/poky/meta-oe/meta-oe \
  ${HOME}/poky/meta-topic \
  "
````

Change your MACHINE to, for example, "tdkz15" or "tdkzu6", depending on
the board you have, and bitbake core-image-minimal. See also the Yocto
Quick start for more information:
http://www.yoctoproject.org/docs/1.6.1/yocto-project-qs/yocto-project-qs.html

A few recipes still require the "gitpkgv" class from meta-openembedded.
Include it by cloning git://github.com/openembedded/meta-openembedded.git and add
the path to its "meta-oe" subdirectory to the conf/bblayers.conf list.


# BOOT

The simplest way to boot the resulting image is to place it on an SD
card. The `scripts/partition-sd-card.sh` script formats an SD card so it
can be used directly. This needs to be done only once for a card.
The `install-to-sd...` scripts copy the required files to the card. You'll
have to run these scripts as root, as they require low-level access to
the SD card.

To boot a Miami-Florida board, insert the SD card into the slot, set the
jumpers on the miami to boot from SD (01010110) and switch on the power
supply.

# MPSoC PMU firmware

The MPSoC machines, named "tdkzu*", need a PMU firmware "blob" for the embedded
microblaze power management unit. This repo holds the source code for generating
this binary using OpenEmbedded recipes, but since it generates a single
identical firmware that can be used on any MPSoC, a pre-built binary is already
included and injected into the u-boot SPL image. The procedure using multiconfig
in meta-xilinx does not actually work, so to reproduce it, do as follows:

Go to the `build` directory (use topic-platform to create one).

Add these lines to `conf/local.conf`:
```
MACHINE="zynqmp-pmu"
DISTRO="xilinx-standalone"
TMPDIR="${TOPDIR}/pmutmp"
```
Now execute:
```
. ./profile
MACHINE=zynqmp-pmu bitbake pmu-firmware
```
The PMU firmware binary will be created in `pmutmp/deploy/images/zynqmp-pmu/`.
Compress this using `xz` and replace the `pmu-firmware.bin.xz` file in
`u-boot-xlnx`:
```
xz < pmutmp/deploy/images/zynqmp-pmu/pmu-firmware-zynqmp-pmu.bin > ../meta-topic/recipes-bsp/u-boot/u-boot-xlnx/pmu-firmware-zynqmp-pmu.bin.xz
```

Don't forget to remove the three extra lines from your `local.conf` file afterwards...


# Additional information

In order to properly use the Zynq for anything useful, you will need to
build a bitstream image using the Xilinx tools.

This started as a fork of Ballister's OE environment for Zynq boards.

Support for Dyplo, which is not directly related to the hardware in this
layer, is provided by `meta-dyplo`:
https://github.com/topic-embedded-products/meta-dyplo

In future, this OpenEmbedded overlay is to be split up into several
components, as to separate the board support package for Topic Miami
boards and the tiny distro.


# Contact

For questions or comments, contact Topic Embedded Products via the website:
http://www.topicembeddedproducts.com/
