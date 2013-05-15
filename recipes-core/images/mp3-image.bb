DESCRIPTION = "Image for MP3 playback"

require my-image.bb

IMAGE_FSTYPES += "squashfs"

MY_THINGS += "\
	gst-plugins-base-decodebin2 \
	gst-plugins-ugly-mad \
	gst-plugins-base-alsa \
	"
