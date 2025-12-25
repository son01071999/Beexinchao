#!/bin/sh
cd ../resource
docker compose down
docker compose up -d
sleep 10

echo "Waiting for DB to ready..."
# shellcheck
while [ "$(docker inspect -f {{.State.Health.Status}} my-db-mysql)" != "healthy" ]; do
  sleep 1
done
#---
cd ../
./gradlew :flywayClean :flywayMigrate