#!/bin/sh
set -e
if [ ! -z "$1" ]
then
  BUILDDIR="$1"
else
  BUILDDIR=build
fi
if [ `readlink /bin/sh` = dash ]
then
	echo "Your shell is set to 'dash', this will cause lots of troubles. See:"
	echo "http://www.openembedded.org/wiki/OEandYourDistro"
	echo "Please run this and answer 'No' when asked to set dash as the default shell:"
	echo "sudo dpkg-reconfigure dash"
	exit 1
fi
if [ ! -d oe-core ]
then
	echo "Please run this as 'meta-topic/scripts/init-oe.sh' from the top directory."
	exit 1
fi
if [ ! -e oe-core/.git ]
then
	git submodule init
	git submodule update --init
fi
if [ ! -d ${BUILDDIR}/conf ]
then
	mkdir -p ${BUILDDIR}/conf
fi
if [ ! -f ${BUILDDIR}/conf/local.conf ]
then
	cp -rp meta-topic/scripts/templates/build/* ${BUILDDIR}/
fi
cd oe-core
source ./oe-init-build-env ../${BUILDDIR} ../bitbake
echo "-------------------------------------------------------------"
echo "You'll need to install the following packages, if you haven't"
echo "already done so. On ubuntu, use:"
echo "sudo apt-get install \\"
echo "   sed wget cvs subversion git-core \\"
echo "   coreutils unzip texi2html texinfo docbook-utils \\"
echo "   gawk python-pysqlite2 diffstat help2man make gcc \\"
echo "   build-essential g++ desktop-file-utils chrpath"
echo "-------------------------------------------------------------"
echo "Initialization complete!"
echo ""
echo "First, EDIT build/conf/local.conf to match your system."
echo "To build an image for the zedboard, run the following:"
echo ""
echo "cd ${BUILDDIR}"
echo ". profile"
echo "bitbake my-image"
