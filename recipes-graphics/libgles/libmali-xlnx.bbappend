# Checksum in Xilinx recipe appears to be wrong
SRC_URI[md5sum] = "8b5b44b3f39f8662ed2755296865bb99"
SRC_URI[sha256sum] = "2fe9f3da94f19ba6a74d7da32f4c719ffddd31e38ada3be167884eba510da0f2"

# Remove the KHR headers, conflicts with mesa-gl
# Install gbm includes for x11 as well, not just for wayland
do_install_append() {
    rm -rf ${D}${includedir}/KHR
    if [ "${USE_X11}" = "yes" ]; then
        install -m 0644 ${S}/${PV}/glesHeaders/GBM/gbm.h ${D}${includedir}/
        install -m 0644 ${WORKDIR}/gbm.pc ${D}${libdir}/pkgconfig/gbm.pc
    fi
}
