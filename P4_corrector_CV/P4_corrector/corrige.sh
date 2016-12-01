#!/bin/bash

if [ $# -ne 1 ]; then
	echo "Falta el nombre del fichero .tgz"
	exit 1
fi

rm TEST* anterror
rm -rf src 2> /dev/null
rm -f test/files/P2/*.alu 2>/dev/null
rm -f test/files/P3/*.alu 2>/dev/null
rm -f test/files/P4/*.alu 2>/dev/null
mkdir src
cp -R org src
cp $1 src/practica.tgz
cd src
tar xvzf practica.tgz model/ 
cd ..
export ANT_OPTS="-Xms256m -Xmx1024m"
#./apache-ant-1.8.2/bin/ant -f build-utf-8.xml 2> anterror
./apache-ant-1.8.2/bin/ant 2> anterror
sh ./nota.sh #> nota_alumno.txt
echo "Resultado del alumno: "
cat nota_junit.txt
cat nota_doc.txt
cat nota_total.txt
