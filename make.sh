#!/bin/bash

cd src

javac *.java

java Main.java > saida.txt

rm -rf *.class