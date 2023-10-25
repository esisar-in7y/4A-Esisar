#!/usr/bin/env python

from __future__ import print_function

import sys

import pcapng
from pcapng.blocks import *
from pcapng.structs import *

from scapy.layers.inet import *
from scapy.layers.l2 import Ether

class Couche2:
    PPP = 0x8864
    IP = 0x0800

class Couche3:
    CHAP = 0xc223
    UDP = 17

INT_FORMATS = {8: 'b', 16: 'h', 32: 'i', 64: 'q'}
def read_int(data, signed=False,):
    size = len(data)*8
    fmt = INT_FORMATS.get(size)
    fmt = fmt.lower() if signed else fmt.upper()
    endianness = '!'
    fmt = endianness + fmt
    size_bytes = size // 8
    return struct.unpack(fmt, data)[0]


def Decode_CHAP(data):
    Code = int(data[0])
    Identifier = int(data[1])
    Len = read_int(data[2:4])
    print(Code)
    print(Identifier)
    print(Len)
    match Code:
        case [1,2]: # Challenge
            Data_ValueSize = int(data[4])
            Data_Value = data[5:5+Data_ValueSize]
            Data_Name = str(data[5+Data_ValueSize:Len])
            print(Data_ValueSize)
            print(Data_Value)
            print(Data_Name)
        case 3: # Success
            pass

def Decode_Radius(data):
    Code = int(data[0])
    PacketIdentifier(data[1])
    Length = read_int(data[2:4])
    Authenticator = str(data[4:20])
    #Section AVP (Attribute Value Pairs)

def Decode_UDP(data):
    SrcPort = read_int(data[0:2])
    DestPort = read_int(data[2:4])
    Length = read_int(data[4:6])
    Checksum = read_int(data[6:8])
    Protocol = min(SrcPort,DestPort)
    match Protocol:
        case [1645,1646,1812,1813]:
            Decode_Radius(data[8:])#40-8

def Decode_PPPoE(data):
    PPP_Type = read_int(data[0:2], signed=False)
    match PPP_Type:
        case CHAP:
            print('CHAP !')
            Decode_CHAP(data[2:])

def Decode_IP(data):
    Version = int(data[0])
    Protocol = int(data[9])
    IPsrc = unpack_ipv4(data[12:16])
    IPdst = unpack_ipv4(data[16:20])
    print(Version)
    print(Protocol)
    print( IPsrc + ' -> ' + IPdst )
    match Protocol:
        case UDP:
            print('UDP')
            Decode_UDP(data[20:])

def Analyse(data):
    dest = pcapng.utils.unpack_macaddr(data[0:6])
    src = pcapng.utils.unpack_macaddr(data[6:12])
    print(src + ' -> ' + dest)
    Eth_Type = read_int(data[12:14], signed=False)
    print(Eth_Type)
    match Eth_Type:
        case Couche2.PPP:
            print('PPPoE !')
            Decode_PPPoE(data[20:])
        case Couche2.IP:
            print('IP !')
            Decode_IP(data[20:])
        case other:
            print('unimplemented')
    
            


def dump_information(scanner):
    for block in scanner:
        if isinstance(block,EnhancedPacket):
            #print(block.packet_data,end='\n\n')
            Analyse(block.packet_data)
            print('-----------')


if __name__ == "__main__":
    if len(sys.argv) > 0:
        #with open(sys.argv[1], "rb") as fp:
        with open('test-py2.pcapng', "rb") as fp:
            scanner = pcapng.FileScanner(fp)
            dump_information(scanner)

    else:
        scanner = pcapng.FileScanner(sys.stdin)
        dump_information(scanner)
