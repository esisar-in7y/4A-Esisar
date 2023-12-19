import re
import sys
import subprocess
clients={
    "Tp-LinkT_00:b2:98":"client",
    "192.168.130.206":"client",
    "a2:aa:1b:7a:67:c3":"server",
    "192.168.130.25":"server",
    "127.0.0.1":"local"
}
more={
    "local":"RADIUS Server",
    "client":"(Notre Machine)",
}

command=f'tshark -r {sys.argv[1]} -z flow,any,standard -f "ppp || pppoe || eap || radius || chap"'
lines=subprocess.check_output(['tshark', '-r', sys.argv[1], '-z', 'flow,any,standard', '-Y', 'ppp || pppoe || eap || radius || chap']).decode("utf-8")

regex = re.compile(r"^\s+\d+\s+\d+\.\d+\s+(?P<src>\S+)\s+→ (?P<dst>\S+)?\s+(?P<proto>.+?)\s+(?P<length>\d+)\s+(?P<info>.*?(?P<infoplus>\(.+\))?)$")
result=""
used=set()
for line in lines.splitlines():
    data=regex.match(line)
    if data is None:
        continue
    src_name=clients.get(data.group("src"),data.group("src"))
    dst_name=clients.get(data.group("dst"),data.group("dst"))
    if not dst_name:
        if src_name == "client":
            dst_name = "server"
        else:
            dst_name = "client"
    arrow="->>"
    if " Ack" in data.group("info") or " Nak" in data.group("info"):
        arrow="-->"
    result+="    {} {} {}: {} | {}\n".format(src_name,arrow,dst_name,data.group("proto"),data.group("info"))
    used.add(src_name)
    used.add(dst_name)

result_start="""
```mermaid
sequenceDiagram
"""
#combine same client.name
for name in used:
    reverse_name=[x for x in clients if clients[x]==name]
    result_start+="    participant {} as {}{}\n".format(name,more.get(name,"")," / ".join(reverse_name))

result=result_start+result+"```"
print(result)