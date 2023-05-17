#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/action"
JAR_FILE="$PROJECT_ROOT/build/libs/CapstoneProject-0.0.1-SNAPSHOT-plain.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)
PORT=8080

# Get the process ID running on the specified port
PID=$(lsof -t -i:$PORT)

# Kill the process if it exists
if [[ -n $PID ]]; then
    echo "Killing process $PID running on port $PORT"
    kill $PID
else
    echo "No process found running on port $PORT"
fi

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# jar 파일 실행
echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG