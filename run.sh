#!/bin/bash

echo "Building"
mvn package
echo "running"
mvn spring-boot:run
echo "done"