#!/usr/bin/python
import sys

# Fetch RGB data (skip 54 byte header)
d = open(sys.argv[1], 'rb').read()[54:]

assert len(d) == 3 * 32 * 128

# Only look at the "green" channel
g = [ord(d[i+1]) for i in range(0, len(d), 3)]

# create the bitmap in matrix printer format
result = []

for band in range(4):
	for x in range(128):
		y = 24 - (band * 8)
		v = 0
		mask = 0x80
		while mask:
			if g[127 - x + (y * 128)] >= 128:
				v |= mask
			mask = mask >> 1
			y += 1
		result.append(chr(v))

sys.stdout.write(''.join(result))