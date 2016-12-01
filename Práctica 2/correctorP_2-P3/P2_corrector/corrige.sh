#!/bin/bash

ANT=./lib/apache-ant-1.9.2/bin/ant

if [ $# -ne 1 ]; then
	echo "Falta el nombre del fichero .tgz"
	exit 1
fi

rm -rf src nota_junit.txt penalizacion_doc.txt nota_total.txt anterror salida.out error.out errores_javadoc.txt TEST* 2> /dev/null
mkdir -p src/mains
cp Main2C1415.java src/mains
cp $1 src/practica.tgz
cd src
tar xvzf practica.tgz model/Coordinate.java model/Piece.java model/Gameboard.java model/Game.java model/Rotation.java 
cd ..
export ANT_OPTS="-Xms256m -Xmx1024m"
#./apache-ant-1.8.2/bin/ant 2> anterror
$ANT 2> anterror
cat anterror | grep -v date | grep [checkstyle] > errores_javadoc.txt
sh ./nota.sh #> nota_alumno.txt
echo "Resultado del alumno: "
cat nota_junit.txt
cat nota_doc.txt
cat nota_total.txt
