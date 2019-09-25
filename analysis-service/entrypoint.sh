#!/bin/sh

DEFAULT_PRESIDIO_API_SVC_ADDRESS=presidio-api:8080

if [ -z "$PRESIDIO_API_SVC_ADDRESS" ]; then
  PRESIDIO_API_SVC_ADDRESS=$DEFAULT_PRESIDIO_API_SVC_ADDRESS
fi

# TODO: add api service polling as readiness probe
sleep 30

exec java -jar /usr/local/lib/analysis-service.jar
