#!/bin/bash
set -e
javacc ProjLang.jj
mkdir -p output
cp -r ProjSuppliedFiles/* output
cp -r src/* output
rm -rf bin
mkdir -p bin
javac -cp output -d bin output/*.java ScanMain.java ParseMain.java
cp -r ProjSuppliedFiles/*.class bin

