#!/bin/bash

APP_NAME=vuln

function r_num {

	num=$RANDOM 
	mod=$1 #12

	if [ "$mod" == "" ]; then
		mod=99
	fi

	#On prend le modulo par 12
	let "num %= $mod"

	if [ "$num" == 0 ]; then
		num=1
	fi
	echo $num
}

function r_str {

	len=$1

	if [ "$len" == "" ]; then
		len=99
	fi

	echo $(for((i=1;i<=$len;i++)); do printf '%s' "${RANDOM:0:1}"; done) | tr '[0-9]' '[a-zA-Z]'
}

function r_str_upper {

	len=$1

	if [ "$len" == "" ]; then
		len=99
	fi

	echo $(for((i=1;i<=$len;i++)); do printf '%s' "${RANDOM:0:1}"; done) | tr '[0-9]' '[A-Z]'
}

function r_env {

	cnt=$1 #12

	if [ "$cnt" == "" ]; then
                cnt=24
        fi

	for i in `seq 1 $(r_num $cnt)`;
	do
        	n=$(r_str_upper $(r_num 12))
        	v=$(r_str $(r_num 32))
        	export $n=$v
	done
}

DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)

for i in `seq 1 10`;
	do
		r_env $i
	done

ulimit -S -c 0 > /dev/null 2>&1

./$APP_NAME
