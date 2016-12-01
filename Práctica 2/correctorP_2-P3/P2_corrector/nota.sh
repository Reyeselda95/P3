L1=$LANG
L2=$LC_ALL 
export LANG=C
export LC_ALL=C

notaP1=$(cat TEST-model.CoordinateTest.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*1; }')
#echo $notaP1
notaPieceTest=$(cat TEST-model.PieceTestP2.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*2.0; }')
#echo $notaPieceTest
notaGameboardTest=$(cat TEST-model.GameboardTestP2.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*2.5; }')
notaGameTest=$(cat TEST-model.GameTestP2.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*3.5; }')
#echo $notaP1 $notaPieceTest $notaGameboardTest $notaGameTest 

if [ -z $notaP1 ] ; then
  notaP1=0
fi

#echo $notaP1

if [ -z $notaPieceTest ] ; then
  notaPieceTest=0
fi
if [ -z $notaGameboardTest ] ; then
  notaGameboardTest=0
fi
if [ -z $notaGameTest ] ; then
  notaGameTest=0
fi

nota_junit=$(echo "($notaP1 + $notaPieceTest + $notaGameboardTest + $notaGameTest)" | bc)

#docs=$(cat errores_javadoc.txt | grep -c "checkstyle")
docs=$(awk '/BUILD FAILED/ { getline; print }' anterror | grep -c "Compile failed")
#echo COMPILE $docs
if [ $docs -eq 1 ]; then
  docs=10 # equivale a maximo numero de errores
else
  docs=$(cat anterror | grep -iv date | grep -c checkstyle)

  if [ -z $docs ]; then
    docs=10
  fi
fi

notadocs=$(echo "1.0 - $docs * 0.1" | bc)
if [ $(echo "$notadocs < 0"|bc) -eq 1 ]; then
	notadocs=0
fi


#echo JUNIT $nota_junit
#echo DOCS $notadocs

echo NOTAS JUNIT > nota_junit.txt
echo "  "model.CoordinateTest: $notaP1 "(max. 1)" >> nota_junit.txt
echo "  "model.PieceTestP2: $notaPieceTest "(max. 2.0)" >> nota_junit.txt
echo "  "model.GameboardTestP2: $notaGameboardTest "(max. 2.5)" >> nota_junit.txt
echo "  "model.GameTestP2: $notaGameTest "(max. 3.5)" >> nota_junit.txt
echo "  "TOTAL tests JUnit: $nota_junit >> nota_junit.txt
echo NOTA JAVADOC > nota_doc.txt
echo $notadocs >> nota_doc.txt
notafinal=$(echo "($nota_junit + $notadocs)" | bc)
#echo NOTA $notafinal

if [ $(echo "$notafinal < 0.0"|bc) -eq 1 ]; then
	notafinal=0.0
fi
echo NOTA TOTAL > nota_total.txt
echo $notafinal >> nota_total.txt

export LANG=$L1
export LC_ALL=$L2
