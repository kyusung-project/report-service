kill $(cat /home/leekyusung/report-service/pid.file) &&
  rm /home/leekyusung/report-service/pid.file | true
nohup ./start.sh &
