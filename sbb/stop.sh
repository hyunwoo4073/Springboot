#!/bin/bash

# 'ps -ef' 명령은 현재 실행 중인 프로세스를 출력
# 'grep java | grep sbb' 명령은 출력된 문자열에서 문장이 포함된 프로세스만 필터링
# awk '{pring $2}' 명령은 출력 문자열의 2번째 항목인 프로세스 아이디만 뽑아내는 역할을 함
SBB_UID=$(ps -ef | grep java | grep sbb | awk '{pring $2}')

if [ -z "$SBB_PID" ];
then
	echo "SBB is not running"
else
	kill -9 $SBB_UID
	echo "SBB stopped"
fi
