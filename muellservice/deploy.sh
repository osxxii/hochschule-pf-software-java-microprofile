#!/usr/bin/env bash
# java -jar ./payara-micro.jar --port 8093 --deploy ./target/muellservice.war
mvn clean install
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9093 -jar ./payara-micro.jar --port 8093 --deploy ./target/muellservice.war