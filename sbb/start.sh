#!/bin/bash

JAR=sbb-0.0.2-SNAPSHOT.jar
LOG=/home/bae/sbb/sbb.log

java -Dspring.profiles.active=prod -jar $JAR > $LOG 2>&1 &
