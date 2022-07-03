#!/usr/bin/env bash
# java -jar ./payara-micro.jar --port 8094 --deploy ./target/profileservice.war
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9094 -jar ./payara-micro.jar --port 8094 --deploy ./target/profileservice.war