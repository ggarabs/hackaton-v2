#!/bin/bash

if ! command -v dot &> /dev/null
then
    echo "Instalando graphviz..."
    sudo apt update
    sudo apt install graphviz
fi

cd src

javac *.java

java Main.java > saida.txt

rm -rf *.class

mv AdjGraph.png ./..

rm AdjGraph.dot