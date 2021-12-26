#!/bin/bash

REPOSITORY=/home/ubuntu/PICO
PROJECT_NAME=pico-back

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$(pgrep java)" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다.";
else
     echo "> kill -15 $(pgrep java)"
  kill -15 $(pgrep java);
fi

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x /home/ubuntu/PICO/pico-0.0.1-SNAPSHOT.jar

echo "> $JAR_NAME 실행"

nohup java -jar /home/ubuntu/PICO/pico-0.0.1-SNAPSHOT.jar /home/ubuntu/PICO/nohup.out 2>&1 &