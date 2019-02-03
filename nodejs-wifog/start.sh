#!/bin/bash

while :
do
	yes | head -${1-10} | while read line
	do
		./wifog.js
		rm -rf /tmp/.org.chromium.Chromium.*
#		sleep 60
	done
	sleep 3600
done
