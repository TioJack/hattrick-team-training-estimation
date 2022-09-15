# hattrick-team-training-estimation

SDK:
Amazon Corretto 18
https://docs.aws.amazon.com/corretto/latest/corretto-18-ug/downloads-list.html

Docker:

`docker run -it \
--restart=unless-stopped \
--privileged \
--name htte \
-v /home/atom/htte:/home:Z \
-p 8090:8080 \
amazoncorretto:18 \
/bin/bash -c '/usr/bin/java -jar /home/htte.jar'`
