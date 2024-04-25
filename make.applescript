-- Applescript
do shell script "javac *.java"
do shell script "java Main > saida.txt"
do shell script "rm -rf *.class"