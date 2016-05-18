#!/usr/bin/python
import sys
import os
import struct

def flip32(data):
	sl = struct.Struct('<I')
	sb = struct.Struct('>I')
	b = buffer(data)
	d = bytearray(len(data))
	for offset in xrange(0, len(data), 4):
		 sb.pack_into(d, offset, sl.unpack_from(b, offset)[0])
	return d

import argparse
parser = argparse.ArgumentParser(description='Convert FPGA bit files to raw bin format suitable for flashing')
parser.add_argument('-f', '--flip', dest='flip', action='store_true', default=False, help='Flip 32-bit endianess (needed for Zynq)')
parser.add_argument("bitfile", help="Input bit file name")
parser.add_argument("binfile", help="Output bin file name")
args = parser.parse_args()

short = struct.Struct('>H')
ulong = struct.Struct('>I')

bitfile = open(args.bitfile, 'rb')

l = short.unpack(bitfile.read(2))[0]
if l != 9:
	raise Exception, "Missing <0009> header (0x%x), not a bit file" % l
bitfile.read(l)
l = short.unpack(bitfile.read(2))[0]
d = bitfile.read(l)
if d != 'a':
	raise Exception, "Missing <a> header, not a bit file"

l = short.unpack(bitfile.read(2))[0]
d = bitfile.read(l)
print "Design name:", d

# If bitstream is a partial bitstream, get some information from filename and header 
if d.find("PARTIAL=TRUE") != -1:
	print "Partial bitstream"
	partial = True;
	
	# Get node_nr from filename (last (group of) digits)
	for i in range (len(args.bitfile) - 1, 0, -1):
		if args.bitfile[i].isdigit():
			pos_end = i + 1
			for j in range (i - 1, 0, -1):
				if not args.bitfile[j].isdigit():
					pos_start = j + 1
					break
			break
	if pos_end != 0 and pos_end != 0:
		node_nr = int(args.bitfile[pos_start:pos_end])
	else:
		node_nr = 0
	print "NodeID:", node_nr
	
	# Get 16 least significant bits of UserID in design name
	pos_start = d.find("UserID=") 
	if pos_start != -1:
		pos_end = d.find(";", pos_start)
		pos_start = pos_end - 4
		userid = d[pos_start:pos_end]
		print "UserID:", userid	
	
else:
	print "Full bitstream"
	partial = False
	node_nr = 0

KEYNAMES = {'b': "Partname", 'c': "Date", 'd': "Time"}

while 1:
	k = bitfile.read(1)
	if not k:
		bitfile.close()
		raise Exception, "unexpected EOF"
	elif k == 'e':
		l = ulong.unpack(bitfile.read(4))[0]
		print "Found binary data:", l
		d = bitfile.read(l)
		if args.flip:
			print "Flipping data..."
			d = flip32(d)
		# Open bin file
		binfile = open(args.binfile, 'wb')
		# Write header if it is a partial
		if partial:
			binfile.write(struct.pack("B", 0))
			binfile.write(struct.pack("B", node_nr))
			binfile.write(struct.pack(">H", int("0x" + userid, 16)))
		# Write the converted bit-2-bin data
		print "Writing data..."
		binfile.write(d)
		binfile.close()
		break
	elif k in KEYNAMES:
		l = short.unpack(bitfile.read(2))[0]
		d = bitfile.read(l)
		print KEYNAMES[k], d
	else:
		print "Unexpected key: ", k
		l = short.unpack(bitfile.read(2))[0]
		d = bitfile.read(l)
		
bitfile.close()
