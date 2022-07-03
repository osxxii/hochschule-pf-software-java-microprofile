#!/usr/bin/env bash
# java -jar ./payara-micro.jar --port 8091 --deploy ./target/authservice.war
mvn clean install
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9091 -jar ./payara-micro.jar --port 8091 --deploy ./target/authservice.war
