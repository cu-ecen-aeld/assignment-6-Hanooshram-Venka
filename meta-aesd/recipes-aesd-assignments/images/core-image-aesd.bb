inherit core-image

# Add custom package to the image.
# Attribution: Logic referenced from AESD coursera lecture slides
CORE_IMAGE_EXTRA_INSTALL += "aesd-assignments"

# Add SSH support as required by Assignment 6 specs
CORE_IMAGE_EXTRA_INSTALL += "openssh"
