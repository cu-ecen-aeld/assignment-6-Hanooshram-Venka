inherit core-image

# Add custom package to the image.
# Attribution: Logic referenced from AESD coursera lecture slides
CORE_IMAGE_EXTRA_INSTALL += "aesd-assignments"

# Add SSH support as required by Assignment 6 specs
CORE_IMAGE_EXTRA_INSTALL += "openssh"

# Added kernel modules for Assignment 7
CORE_IMAGE_EXTRA_INSTALL += "scull misc-modules"

# to set password for qemu as root
inherit extrausers
EXTRA_USERS_PARAMS = "usermod -p '\$6\$CEM0hANiVS9VXN8N\$Q9XK1KTpq2faq2fNbSRLNeeA4mmQsl8g1Gwl3QnTPlRPb5ljCAa./bbhffcthXxUen4jSFL6HKGQPlHZNQkfA/' root;"
