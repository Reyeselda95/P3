notaCoordinateTest=$(cat TEST-model.CoordinateTest.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaPieceTestP2=$(cat TEST-model.PieceTestP2.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.3; }')
notaGameboardTestP2=$(cat TEST-model.GameboardTestP2.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.4; }')
notaGameTestP2=$(cat TEST-model.GameTestP2.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.4; }')
notaJPieceTestP3=$(cat TEST-model.JPieceTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaLPieceTestP3=$(cat TEST-model.LPieceTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaOPieceTestP3=$(cat TEST-model.OPieceTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaSPieceTestP3=$(cat TEST-model.SPieceTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaZPieceTestP3=$(cat TEST-model.ZPieceTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaTPieceTestP3=$(cat TEST-model.TPieceTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaGameboardTestP3=$(cat TEST-model.GameboardTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.5; }')
notaGameTestP3=$(cat TEST-model.GameTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*1.0; }')
notaGameboardExceptionsTestP3=$(cat TEST-model.GameboardExceptionsTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.5; }')
notaGameExceptionsTestP3=$(cat TEST-model.GameExceptionsTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.5; }')
notaPieceFactoryTestP3=$(cat TEST-model.PieceFactoryTestP3.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.05; }')
notaGamePlayTestP4=$(cat TEST-model.io.GamePlayTestP4.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*2.5; }')
notaPlayerFactoryTestP4=$(cat TEST-model.io.PlayerFactoryTestP4.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.5; }')
notaPlayerFileTestP4=$(cat TEST-model.io.PlayerFileTestP4.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.5; }')
notaPlayerRandomTestP4=$(cat TEST-model.io.PlayerRandomTestP4.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.25; }')
notaPlayerStringTestP4=$(cat TEST-model.io.PlayerStringTestP4.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.5; }')
notaVisualizerConsoleTestP4=$(cat TEST-model.io.VisualizerConsoleTestP4.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.5; }')
notaVisualizerFactoryTestP4=$(cat TEST-model.io.VisualizerFactoryTestP4.txt | grep -ai failures | awk 'BEGIN { FS = "[,:]" } ; { nota= 1 - ($4+$6) / $2; if (nota < 0) nota = 0; print nota*0.25; }')


#echo $notaP1 $notaReglaConwayTest $notaPatronTest $notaTableroTest $notaJuegoTest

nota_junit=$(echo "($notaCoordinateTest + $notaPieceTestP2 + $notaGameboardTestP2 + $notaGameTestP2 + $notaJPieceTestP3 + $notaLPieceTestP3 + $notaOPieceTestP3 + $notaSPieceTestP3 + $notaZPieceTestP3 + $notaTPieceTestP3 + $notaGameboardTestP3 + \
 $notaGameExceptionsTestP3 + $notaGameboardExceptionsTestP3 + $notaGameTestP3 + \
$notaPieceFactoryTestP3+$notaGamePlayTestP4 + $notaPlayerFactoryTestP4 + $notaPlayerFileTestP4 + $notaPlayerRandomTestP4 + $notaPlayerStringTestP4 + \
$notaVisualizerConsoleTestP4 + $notaVisualizerFactoryTestP4)" | bc)

#echo $nota_junit

#docs=$(cat errores_javadoc.txt | grep -c "checkstyle")
docs=$(cat anterror | grep -iv date | grep -c checkstyle)
notadocs=$(echo "1.0 - $docs * 0.1" | bc)
if [ $(echo "$notadocs < 0"|bc) -eq 1 ]; then
	notadocs=0

#echo $nota_junit
#echo $notadocs

fi

echo NOTAS JUNIT > nota_junit.txt
echo "  "model.CoordinateTest: $notaCoordinateTest "(max. 0.05)" >> nota_junit.txt
echo "  "model.PieceTestP2: $notaPieceTestP2 "(max. 0.3)" >> nota_junit.txt
echo "  "model.GameboardTestP2: $notaGameboardTestP2 "(max. 0.4)" >> nota_junit.txt
echo "  "model.GameTestP2: $notaGameTestP2 "(max. 0.4)" >> nota_junit.txt
echo "  "model.JPieceTestP3: $notaJPieceTestP3 "(max. 0.05)" >> nota_junit.txt
echo "  "model.LPieceTestP3: $notaLPieceTestP3 "(max. 0.05)" >> nota_junit.txt
echo "  "model.OPieceTestP3: $notaOPieceTestP3 "(max. 0.05)" >> nota_junit.txt
echo "  "model.SPieceTestP3: $notaSPieceTestP3 "(max. 0.05)" >> nota_junit.txt
echo "  "model.ZPieceTestP3: $notaZPieceTestP3 "(max. 0.05)" >> nota_junit.txt
echo "  "model.TPieceTestP3: $notaTPieceTestP3 "(max. 0.05)" >> nota_junit.txt
echo "  "model.GameboardTestP3: $notaGameboardTestP3 "(max. 0.5)" >> nota_junit.txt
echo "  "model.GameTestP3: $notaGameTestP3 "(max. 1.0)" >> nota_junit.txt
echo "  "model.PieceFactoryTestP3: $notaPieceFactoryTestP3 "(max. 0.05)" >> nota_junit.txt
echo "  "model.GameboardExceptionsTestP3: $notaGameboardExceptionsTestP3 "(max. 0.5)" >> nota_junit.txt
echo "  "model.GameExceptionsTestP3: $notaGameExceptionsTestP3 "(max. 0.5)" >> nota_junit.txt
echo "  "model.io.GamePlayTestP4: $notaGamePlayTestP4 "(max. 2.5)" >> nota_junit.txt
echo "  "model.io.PlayerFactoryTestP4: $notaPlayerFactoryTestP4 "(max. 0.5)" >> nota_junit.txt
echo "  "model.io.PlayerFileTestP4: $notaPlayerFileTestP4 "(max. 0.5)" >> nota_junit.txt
echo "  "model.io.RandomPlayerTestP4: $notaPlayerRandomTestP4 "(max. 0.25)" >> nota_junit.txt
echo "  "model.io.PlayerStringTestP4: $notaPlayerStringTestP4 "(max. 0.5)" >> nota_junit.txt
echo "  "model.io.VisualizerConsoleTestP4: $notaVisualizerConsoleTestP4 "(max. 0.5)" >> nota_junit.txt
echo "  "model.io.VisualizerFactoryTestP4: $notaVisualizerFactoryTestP4 "(max. 0.25)" >> nota_junit.txt
echo "  "TOTAL tests JUnit: $nota_junit >> nota_junit.txt
echo NOTA JAVADOC > nota_doc.txt
echo $notadocs >> nota_doc.txt
notafinal=$(echo "($nota_junit + $notadocs)" | bc)
if [ $(echo "$notafinal < 0.0"|bc) -eq 1 ]; then
	notafinal=0.0
fi
echo NOTA TOTAL > nota_total.txt
echo $notafinal >> nota_total.txt

