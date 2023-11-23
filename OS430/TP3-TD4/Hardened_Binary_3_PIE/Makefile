all:
	gcc -o vuln -fno-stack-protector  vuln.c -ldl
	gcc -o vuln2 -fno-stack-protector -static vuln.c -no-pie
