#!/bin/bash

# 배포파일 입력
JAR=/home/bae/sbb/target/sbb-0.0.2-SNAPSHOT.jar
# 출력할 로그파일 입력력
LOG=/home/bae/sbb/sbb.log

java -Dspring.profiles.active=prod -jar $JAR > $LOG 2>&1 &
