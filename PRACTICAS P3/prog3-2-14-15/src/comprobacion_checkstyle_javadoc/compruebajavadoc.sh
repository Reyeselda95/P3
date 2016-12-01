#!/bin/bash

if [ $# -ne 1 ]; then
	echo "Falta el nombre del directorio raíz donde están los fuentes"
	exit 1
fi

rm TEST* anterror
export ANT_OPTS="-Xms256m -Xmx1024m"
./apache-ant-1.8.2/bin/ant -Dsrc=${1}
cat anterror
