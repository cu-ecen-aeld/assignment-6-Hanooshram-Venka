# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

# Inherit module and update-rc.d for the init script added for A7 extra credit
inherit module update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "misc-modules_init"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-Hanooshram-Venka.git;protocol=ssh;branch=main \
           file://0001-Edited-Makefile-to-build-only-scull-and-misc-modules.patch \
           file://misc-modules_init \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "ef0f02cbef5155f390081caf137b73ea9704bc58"

S = "${WORKDIR}/git"


# Modified EXTRA_OEMAKE to point to misc subdirectory
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"
# Defines the install step to copy the module and the init script for misc-module
do_install () {
    # Install the kernel modules
    module_do_install

    # Install the helper scripts
    install -d ${D}${bindir}
    install -m 0755 ${S}/misc-modules/module_load ${D}${bindir}/
    install -m 0755 ${S}/misc-modules/module_unload ${D}${bindir}/

    # Install the init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/misc-modules_init ${D}${sysconfdir}/init.d/
}

FILES:${PN} += "${sysconfdir}/init.d/misc-modules_init"
FILES:${PN} += "${bindir}/module_load"
FILES:${PN} += "${bindir}/module_unload"
