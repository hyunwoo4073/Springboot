#!/bin/bash

SBB_UID=$(ps -ef | grep java | grep sbb | awk '{pring $2}')

if [ -z "$SBB_PID" ];
then
	echo "SBB is not running"
else
	kill -9 $SBB_UID
	echo "SBB stopped"
fi
