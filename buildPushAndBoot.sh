#!/bin/bash
# bash mvnw clean install -DskipTests;
sudo docker image build -t coconutsrule/outletsapi .;
sudo docker push coconutsrule/outletsapi;
sudo docker-compose down;
# sudo docker stop outletsapi
# sudo docker stop outletsapi-db
# sudo docker rm outletsapi
# sudo docker rm outletsapi-db
# sudo docker volume remove outletsapi_outletsapi-db-data
sudo docker-compose pull;
sudo docker-compose up -d;
sudo docker logs -f outletsapi;
# while ! sudo docker logs outletsapi | grep -q "JVM running"
# do
#   sleep 0.7;
# done
# sudo docker logs outletsapi