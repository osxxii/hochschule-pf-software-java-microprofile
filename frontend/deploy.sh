#!/usr/bin/env bash
# java -jar ./payara-micro.jar --port 8092 --deploy ./target/frontend.war
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9092 -jar ./payara-micro.jar --port 8092 --deploy ./target/frontend.war