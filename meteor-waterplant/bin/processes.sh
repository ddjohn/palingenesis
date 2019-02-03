#!/bin/bash

fkn() {
	ps -ef | wc -l
}


while :
do
	./insertdb.sh processes $(fkn)
	sleep 1
done
