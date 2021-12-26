@ECHO OFF
docker image build -t coconutsrule/outletsapi .
docker push coconutsrule/outletsapi
@REM docker compose down