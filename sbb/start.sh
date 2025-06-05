#!/bin/bash

# 배포파일 입력
JAR=/home/bae/sbb/target/sbb-0.0.3-SNAPSHOT.jar
# 출력할 로그파일 입력력
# LOG=/home/bae/sbb/sbb.log

# /dev/null로 지정하면 콘솔출력이 무시됨
# 로깅 설정을 했기 때문에 변경
LOG=/dev/null
export spring_profiles_active=prod


# java -Dspring.profiles.active=prod -jar $JAR > $LOG 2>&1 &
java -jar $JAR > $LOG 2>&1 &
