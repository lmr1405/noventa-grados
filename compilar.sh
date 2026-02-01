#!/bin/bash

if [ ! -d ./bin ]; then
	mkdir bin
fi

javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d bin \
-sourcepath ./src \
./src/noventagrados/control/*.java \
./src/noventagrados/modelo/*.java \
./src/noventagrados/textui/*.java \
./src/noventagrados/util/*.java
