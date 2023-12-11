#!/bin/bash

end=$1
for number in $(seq 1 $end)
do
    exec java Client $number
done
exec java Client -1
exit 0
