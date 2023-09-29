#!/bin/bash
cp build/libs/*.jar report-service.jar
java -jar report-service.jar --spring.profiles.active=prod &
echo $! >/home/leekyusung/service/report/pid.file &