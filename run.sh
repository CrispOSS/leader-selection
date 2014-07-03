#!/bin/bash

mvn exec:java -Dexec.mainClass=com.github.crisposs.leaderselection.Main -Dexec.args="$1"

