kill $(cat /home/leekyusung/service/report/pid.file) &&
  rm /home/leekyusung/service/report/pid.file | true
nohup ./start.sh &
