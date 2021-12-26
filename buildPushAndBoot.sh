#!/bin/bash
# bash mvnw clean install -DskipTests;
sudo docker image build -t coconutsrule/outletsapi .;
sudo docker push coconutsrule/outletsapi;
sudo docker-compose down;
# sudo docker rm outletsapi-db
# sudo docker rm outletsapi
# sudo docker volume remove blog_outletsapi-db-data
# sudo docker-compose pull;
# sudo docker-compose up -d;
# while ! sudo docker logs outletsapi | grep -q "JVM running"
# do
#   sleep 0.7;
# done
# sudo docker logs outletsapi