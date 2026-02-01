#!/bin/bash

javadoc -author -version -private \
-encoding UTF-8 \
-docencoding UTF-8 \
-sourcepath ./src -d doc \
-classpath ./lib/*:./bin \
-link https://docs.oracle.com/en/java/javase/22/docs/api/ \
-overview overview.html \
-subpackages noventagrados
