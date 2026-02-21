# Recipe created by recipetool
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Source repository configuration per Assignment 6 specification.
# Utilizes SSH protocol for private repository access.
SRC_URI = "git://git@github.com:cu-ecen-aeld/assignments-3-and-later-Hanooshram-Venka.git;protocol=ssh;branch=main"
PV = "1.0+git${SRCPV}"

# SRCREV pinned to the A6 Par- 1 completion commit.
SRCREV = "13515977fccd34c41855895d21745e7c5793c3cd"

# Set source directory to the 'server' subdirectory containing the Makefile and C source.
S = "${WORKDIR}/git/server"

# Init script configuration.
# Attribution: Logic referenced from "Start Script Implementation in Yocto" AESD  lecture slides
inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"

do_configure () {
    :
}

do_compile () {
    oe_runmake
}

# Installation execution
do_install () {
    # Install compiled aesdsocket binary to /usr/bin/
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/
    
    # Install daemon start-stop script to /etc/init.d/
    # Attribution: Installation paths and syntax referenced from AESD lecture slide.
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/
}
