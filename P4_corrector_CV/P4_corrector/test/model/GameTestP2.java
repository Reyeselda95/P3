package model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import model.exceptions.CollisionMovementException;
import model.exceptions.CurrentPieceNotFixedException;
import model.exceptions.FixedPieceMovementException;
import model.exceptions.GameEndedMovementException;
import model.exceptions.NoCurrentPieceException;
import model.exceptions.OffBoardMovementException;
import model.exceptions.TetrisException;
import model.exceptions.WrongSizeException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTestP2 {
	Game gameMinimal, gameMiniTest;
	StringBuilder sbIn = new StringBuilder();
	StringBuilder sbOut = new StringBuilder();
	PrintStream ps;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		gameMinimal = new Game(new Coordinate(4,4));
		gameMiniTest = new Game (new Coordinate (7,5));
		sbIn = new StringBuilder();
		sbOut = new StringBuilder();
	}


	//Prueba del constructor Game. Debe construir un board
	@Test
	public void testGame() throws TetrisException {
		//Comprobamos que board ha sido iniciada.
		Game game = new Game(new Coordinate(7,10));
		try {
			game.toString();
		} catch (NullPointerException e) {
			fail ("Error: board está a NULL");
		}
		assertFalse("gameEnded == false ",game.isGameEnded());
	}
	
	//Tablero donde no se puede poner ninguna pieza inicial
	@Test
	public void testNextPiece1() throws TetrisException{
	   
		Game gameImposible=null;
		try {
			gameImposible = new Game(new Coordinate(1,4));
		} catch (WrongSizeException e) {
			assertNull(gameImposible);
		}
	}
	
	
	/* Tablero (4,4) con solo una posición posible inicial para la pieza
	 * Movimientos imposibles.
	 */
	
	@Test
	public void testNextPiece2() throws TetrisException {
		
		ps = openFileForWritingStudentOutput("test/files/P2/nextpiece2.alu");
		
		saveGame(gameMinimal);
		assertTrue(gameMinimal.nextPiece("I"));
		saveGame(gameMinimal);
		assertFalse(gameMinimal.isCurrentPieceFixed());
		assertFalse(gameMinimal.isGameEnded());	
		gameMinimal.moveCurrentPieceDown();
		gameMinimal.moveCurrentPieceDown();//Sacabó
		assertFalse(gameMinimal.isGameEnded());
		try {
				assertFalse(gameMinimal.nextPiece("I"));
		} catch (CurrentPieceNotFixedException e) {
				
		}
		saveGame(gameMinimal);
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/nextpiece2.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	}

	
	
	//Tablero (4,4) ningun movimiento es posible
	@Test
	public void testNextPiece3() throws TetrisException{
		
		ps = openFileForWritingStudentOutput("test/files/P2/nextpiece3.alu");
		
		saveGame(gameMinimal);
		assertTrue(gameMinimal.nextPiece("I"));
		saveGame(gameMinimal);
		
		try {
			gameMinimal.moveCurrentPieceLeft();
		} catch (OffBoardMovementException e) {
			
		} 
		saveGame(gameMinimal);//No se movió
			
		try {
			gameMinimal.moveCurrentPieceRight();
		} catch (OffBoardMovementException e) {
			
		}
		saveGame(gameMinimal);//No se movió
		gameMinimal.moveCurrentPieceDown();
		gameMinimal.moveCurrentPieceDown();
		try {
			gameMinimal.rotateCurrentPieceCounterclockwise();
		} catch (OffBoardMovementException e) {
			
		}
		saveGame(gameMinimal);//No se movió
		try {
			gameMinimal.rotateCurrentPieceClockwise();
		} catch (OffBoardMovementException e) {
			
		}
		saveGame(gameMinimal);//No se movió
		//La partida no se ha acabado todavía
		assertFalse(gameMinimal.isCurrentPieceFixed());
		assertFalse(gameMinimal.isGameEnded());
	
		saveGame(gameMinimal);
			
		ps.close();
			
		sbIn=readSolutionFromFile("test/files/P2/nextpiece3.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());			
	}
		
	//Comprobamos posicionamiento inicial correcto
	@Test
	public void testNextPiece4() throws TetrisException {
		ps = openFileForWritingStudentOutput("test/files/P2/nextpiece4.alu");
		
		assertTrue(gameMinimal.nextPiece("I"));
		saveGame(gameMinimal);
		assertFalse(gameMinimal.isCurrentPieceFixed());
		assertFalse(gameMinimal.isGameEnded());	
		ps.close();
		//Comprobamos que currentPiece esta bien situada en el tablero.
		sbIn=readSolutionFromFile("test/files/P2/nextpiece4.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	}
	
	//Intentamos mover pieza fija.
	@Test
	public void testMovimentsInPieceFixed() throws TetrisException {
		ps = openFileForWritingStudentOutput("test/files/P2/movementsinpiecefixed.alu");
	
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.nextPiece("I"));
		for (int i=0; i<5; i++) {
			gameMiniTest.moveCurrentPieceDown();
			saveGame(gameMiniTest);
		}
		assertFalse(gameMiniTest.isCurrentPieceFixed());
		gameMiniTest.moveCurrentPieceDown(); //La hacemos fija
		assertTrue(gameMiniTest.isCurrentPieceFixed());
		for (int i=0; i<5; i++) {
		  try {
			gameMiniTest.moveCurrentPieceLeft();
			gameMiniTest.rotateCurrentPieceClockwise();
			gameMiniTest.moveCurrentPieceRight();
			gameMiniTest.rotateCurrentPieceCounterclockwise();
		  }catch (FixedPieceMovementException e) {
			  
		  }
		  saveGame(gameMiniTest);
		}
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movementsinpiecefixed.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
	}

	
	//Intentamos mover pieza con partida terminada.
	@Test
	public void testMovimentsInGameEnded() throws TetrisException {
		ps = openFileForWritingStudentOutput("test/files/P2/movementsingameended.alu");
	
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.nextPiece("I"));
		gameMiniTest.rotateCurrentPieceCounterclockwise();
		saveGame(gameMiniTest);
		for (int i=0; i<4; i++) {
			gameMiniTest.moveCurrentPieceDown();
			saveGame(gameMiniTest);
		}
		assertTrue(gameMiniTest.isCurrentPieceFixed());
		
		assertTrue(gameMiniTest.nextPiece("I"));
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.nextPiece("I"));
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		assertFalse(gameMiniTest.nextPiece("I"));
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceRight();
		}catch (GameEndedMovementException e){
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceRight();
		} catch (GameEndedMovementException e) {
			
		}
		saveGame(gameMiniTest);
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movementsingameended.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
	}
	
	/* Movemos pieza D90 izquierda hasta lateral sin sobrepasarlo y 
	 * sin poder girarla */
	@Test
	public void testMoveCurrentPieceLeft1() throws TetrisException {
	
		ps = openFileForWritingStudentOutput("test/files/P2/movecurrentpieceleft1.alu");
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.nextPiece("I"));
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceCounterclockwise();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceLeft();
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceLeft(); //no debe pasar el borde
		} catch (OffBoardMovementException e) {
	
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceCounterclockwise(); //no giro a D180
		}catch (OffBoardMovementException e) {
	
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //no giro a D0
		} catch (OffBoardMovementException e) {
				
		}
		saveGame(gameMiniTest);
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movecurrentpieceleft1.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
		
	}

	/* Movemos pieza D90 izquierda hasta chocar con otra fija sin sobrepasarla
	   y sin poder girarla */
		@Test
	public void testMoveCurrentPieceLeft2() throws TetrisException {
		//Ubicamos primero la fija en la parte inferior izquierda
		//assertTrue(gameMiniProbes.nextPiece());
		
		gameMiniTest.nextPiece("I");
		gameMiniTest.rotateCurrentPieceCounterclockwise();
		gameMiniTest.moveCurrentPieceLeft();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		assertTrue(gameMiniTest.isCurrentPieceFixed());
			
		ps = openFileForWritingStudentOutput("test/files/P2/movecurrentpieceleft2.alu");
		//Probamos ahora con una nueva pieza
		saveGame(gameMiniTest);
		gameMiniTest.nextPiece("I");
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceCounterclockwise();
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceLeft(); //no debe moverse
		} catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //bajamos pieza
		saveGame(gameMiniTest);
		try {
		gameMiniTest.moveCurrentPieceLeft(); //no debe moverse
		} catch (CollisionMovementException e){
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceCounterclockwise(); //no debe girar
		} catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //bajamos pieza
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceLeft(); //no debe moverse
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceCounterclockwise(); //no debe girar
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //no debe girar
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //bajamos pieza
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceLeft(); //no debe moverse
		} catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceCounterclockwise(); //no debe girar
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //no debe girar
		} catch (CollisionMovementException e) {
			
		}
		assertFalse(gameMiniTest.isCurrentPieceFixed()); //No es fija todavia
		gameMiniTest.moveCurrentPieceDown(); //No puede bajar más
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.isCurrentPieceFixed()); //Ya es fija
		ps.close();
			
		sbIn=readSolutionFromFile("test/files/P2/movecurrentpieceleft2.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
	}
	
		/* Movemos pieza D270 derecha hasta lateral sin sobrepasarlo y 
		 * sin poder girarla */	
	@Test
	public void testMoveCurrentPieceRight1() throws TetrisException {
		ps = openFileForWritingStudentOutput("test/files/P2/movecurrentpieceright1.alu");
		
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.nextPiece("I"));
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceClockwise();
		saveGame(gameMiniTest);
		
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);
		
		gameMiniTest.moveCurrentPieceRight(); //no debe pasar el borde
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceCounterclockwise(); //no giro a D0
		} catch (OffBoardMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //no giro a D180
		} catch (OffBoardMovementException e){
			
		}
		saveGame(gameMiniTest);
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movecurrentpieceright1.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
		
	}
	
	/* Movemos pieza D270 derecha hasta chocar con otra fija sin sobrepasarla
	   y sin poder girarla */
	@Test
	public void testMoveCurrentPieceRight2() throws TetrisException {

		//Ubicamos primero la fija en la parte inferior derecha
		//assertTrue(gameMiniProbes.nextPiece());
		
		gameMiniTest.nextPiece("I");
		gameMiniTest.rotateCurrentPieceCounterclockwise();
		gameMiniTest.moveCurrentPieceRight();
		gameMiniTest.moveCurrentPieceRight();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		assertTrue(gameMiniTest.isCurrentPieceFixed());		
		
		ps = openFileForWritingStudentOutput("test/files/P2/movecurrentpieceright2.alu");
		//Probamos ahora con una nueva pieza
		saveGame(gameMiniTest);
		gameMiniTest.nextPiece("I");
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceClockwise();
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceRight(); //no debe moverse
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //bajamos pieza
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceRight(); //no debe moverse
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //no debe girar
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //bajamos pieza
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceRight(); //no debe moverse
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceCounterclockwise(); //no debe girar
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //no debe girar
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //bajamos pieza
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceRight(); //no debe moverse
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceCounterclockwise(); //no debe girar
		}catch (CollisionMovementException e){
			
		}
		saveGame(gameMiniTest);
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //no debe girar
		}catch (CollisionMovementException e){
			
		}
		assertFalse(gameMiniTest.isCurrentPieceFixed()); //No es fija todavia
		gameMiniTest.moveCurrentPieceDown(); //No puede bajar más
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.isCurrentPieceFixed()); //Ya es fija
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movecurrentpieceright2.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
	}

	/* Movemos una pieza hasta ponerla encima de una fija. La desplazamos
	 * a la derecha hasta que pueda bajar en la unica posición que queda.
	 * Al bajar llena la última fila.
	 */
	@Test
	public void testMoveCurrentPieceDown1() throws TetrisException {

		//Ubicamos primero la fija en la parte inferior izquierda
		
		gameMiniTest.nextPiece("I");
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		gameMiniTest.moveCurrentPieceDown();
		assertTrue(gameMiniTest.isCurrentPieceFixed());
		
		/* Situamos una nueva pieza girada D90 en la parte izquierda justo encima 
		 * de la fija. La desplazamos a la derecha hasta llegar a la altura del único hueco
		 * libre y la bajamos hasta hacerla fija y rellenando la última fila del tablero.
		 */
		ps = openFileForWritingStudentOutput("test/files/P2/movecurrentpiecedown1.alu");
		
		saveGame(gameMiniTest);
		gameMiniTest.nextPiece("I");
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceCounterclockwise();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceLeft();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //La ponemos encima de la fija
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);
		
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceRight(); //La ponemos justo encima del hueco libre
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Llenamos la última fila
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija
		assertTrue(gameMiniTest.isCurrentPieceFixed());
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movecurrentpiecedown1.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
	}

	/* Bajamos piezas horizontales formando un castillo de piezas hasta que el juego
	 * acaba por no poderse poner más piezas. */
	
	@Test
	public void testMoveCurrentPieceDown2() throws TetrisException {

		ps = openFileForWritingStudentOutput("test/files/P2/movecurrentpiecedown2.alu");
		
		assertTrue(gameMiniTest.nextPiece("I"));
		
		saveGame(gameMiniTest);
		/* En la sexta iteración la pieza se hace fija. En las dos siguientes
		 * no pasa nada*/
		for (int i=0; i<8; i++) { 
			try {
				gameMiniTest.moveCurrentPieceDown();	
				if (i>=5)
					assertTrue(gameMiniTest.isCurrentPieceFixed());
				else 
					assertFalse(gameMiniTest.isCurrentPieceFixed());
			} catch (FixedPieceMovementException e) {
				
			} 
			saveGame(gameMiniTest);
		}
		try {
			assertTrue(gameMiniTest.isCurrentPieceFixed());
		} catch (NoCurrentPieceException e) {
			
		}
		assertTrue(gameMiniTest.nextPiece("I")); //Siguiente pieza
		saveGame(gameMiniTest);
		
		/* En la quinta iteración la pieza se hace fija. En las tres siguientes
		 * no pasa nada*/
		for (int i=0; i<8; i++) { 
			try {
				gameMiniTest.moveCurrentPieceDown();			
				if (i>=4)
					assertTrue(gameMiniTest.isCurrentPieceFixed());
				else 
					assertFalse(gameMiniTest.isCurrentPieceFixed());
			} catch (FixedPieceMovementException e) {
				
			} 
			saveGame(gameMiniTest);
		}
		
		assertTrue(gameMiniTest.nextPiece("I")); //Siguiente pieza
		saveGame(gameMiniTest);
		/* En la cuarta iteración la pieza se hace fija. En las cuatro siguientes
		 * no pasa nada*/
		for (int i=0; i<8; i++) { 
			try {
				gameMiniTest.moveCurrentPieceDown();		
			if (i>=3)
				assertTrue(gameMiniTest.isCurrentPieceFixed());
			else 
				assertFalse(gameMiniTest.isCurrentPieceFixed());
			} catch (FixedPieceMovementException e){
			}
			saveGame(gameMiniTest);
		}
		
		assertTrue(gameMiniTest.nextPiece("I")); //Siguiente pieza
		saveGame(gameMiniTest);
		/* En la tercera iteración la pieza se hace fija. En las cinco siguientes
		 * no pasa nada*/
		for (int i=0; i<8; i++) { 
			try {
				gameMiniTest.moveCurrentPieceDown();
				if (i>=2)
					assertTrue(gameMiniTest.isCurrentPieceFixed());
				else 
					assertFalse(gameMiniTest.isCurrentPieceFixed());
			} catch (FixedPieceMovementException e) {

			}
			saveGame(gameMiniTest);
		}
		
		assertTrue(gameMiniTest.nextPiece("I")); //Siguente pieza
		saveGame(gameMiniTest);
		
		/* En la segunda iteración la pieza se hace fija. En las seis siguientes
		 * no pasa nada*/
		for (int i=0; i<8; i++) { 
			try {
				gameMiniTest.moveCurrentPieceDown();			
			if (i>=1)
				assertTrue(gameMiniTest.isCurrentPieceFixed());
			else 
				assertFalse(gameMiniTest.isCurrentPieceFixed());
			} catch (FixedPieceMovementException e) {
			
			}
			saveGame(gameMiniTest);
		}
		
		assertTrue(gameMiniTest.nextPiece("I")); //Siguiente pieza
		saveGame(gameMiniTest);
		try {
			gameMiniTest.moveCurrentPieceDown(); //Aqui se hace fija
			assertTrue(gameMiniTest.isCurrentPieceFixed());
			gameMiniTest.moveCurrentPieceDown(); //No hace nada ya es fija
			gameMiniTest.moveCurrentPieceDown(); //No hace nada ya es fija
		} catch (FixedPieceMovementException e) {
		
		}
	
		try {
			assertFalse(gameMiniTest.nextPiece("I")); //Siguiente pieza no se puede poner
	
			//Se hace fija y se acaba la partida.
			saveGame(gameMiniTest);
			assertTrue(gameMiniTest.isGameEnded());
			gameMiniTest.moveCurrentPieceDown(); //No hace nada ya es fija
			gameMiniTest.moveCurrentPieceDown(); //No hace nada ya es fija
		} catch (GameEndedMovementException e) {
			
		}
		saveGame(gameMiniTest);
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movecurrentpiecedown2.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());	
	}
	
	/* Girando y moviendo colocamos 5 piezas verticales en las 4 últimas filas llenándolas. 
	 * La siguientes piezas que aparecen no las podemos girar y las bajamos hasta que acaba 
	 * el juego.
	 */
	@Test
	public void testMoveCurrentPieceDown3() throws TetrisException {
		ps = openFileForWritingStudentOutput("test/files/P2/movecurrentpiecedown3.alu");

		assertTrue(gameMiniTest.nextPiece("I")); //Pieza 1
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceCounterclockwise();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceLeft();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();//Limita con el fondo del tablero
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija
		
		assertTrue(gameMiniTest.nextPiece("I")); //Pieza  2
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceCounterclockwise(); 
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();//Limita con el fondo del tablero
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija
		
		assertTrue(gameMiniTest.nextPiece("I")); //Pieza 3
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);	
		gameMiniTest.rotateCurrentPieceClockwise();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);	
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();//Limita con el fondo del tablero
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija
		
		assertTrue(gameMiniTest.nextPiece("I")); //Pieza 4
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceClockwise();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();//Limita con el fondo del tablero
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija
		
		/*assertTrue(gameMiniTest.nextPiece("I")); //Pieza 5
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceClockwise();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);	
		gameMiniTest.moveCurrentPieceDown();//Limita con el fondo del tablero
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija*/
		
		assertTrue(gameMiniTest.nextPiece("I")); //Pieza 6
		saveGame(gameMiniTest);
		try {
		gameMiniTest.rotateCurrentPieceCounterclockwise(); //No se puede
		gameMiniTest.rotateCurrentPieceClockwise(); //No se puede
		} catch (CollisionMovementException e){
			
		}
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceRight();
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceCounterclockwise(); //No se puede
		saveGame(gameMiniTest);	
		gameMiniTest.rotateCurrentPieceClockwise(); //No se puede
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown(); //Limite con las 5 piezas fijas
		saveGame(gameMiniTest);	
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija
		
		assertTrue(gameMiniTest.nextPiece("I")); //Pieza 7 limite con pieza 6
		saveGame(gameMiniTest);
		try {
		gameMiniTest.rotateCurrentPieceCounterclockwise(); //No se puede
		gameMiniTest.rotateCurrentPieceClockwise(); //No se puede
		}catch (CollisionMovementException e) {
			
		}
		saveGame(gameMiniTest);	
		gameMiniTest.moveCurrentPieceDown(); //Se hace fija
		
		assertFalse(gameMiniTest.nextPiece("I")); //Pieza 8 no se puede poner
		saveGame(gameMiniTest);
		assertTrue(gameMiniTest.isGameEnded());
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/movecurrentpiecedown3.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	}
		
	//Rotamos continuamente una pieza  y la bajamos hasta el final del tablero
	@Test
	public void testRotateClockwise() throws TetrisException{
		
		ps = openFileForWritingStudentOutput("test/files/P2/rotateclockwise.alu");

		assertTrue(gameMiniTest.nextPiece("I")); //Pieza
		saveGame(gameMiniTest);	
		gameMiniTest.rotateCurrentPieceClockwise(); 
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);		
		gameMiniTest.rotateCurrentPieceClockwise(); 
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceClockwise(); 
		saveGame(gameMiniTest);
		gameMiniTest.rotateCurrentPieceClockwise(); 
		saveGame(gameMiniTest);
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);
		for (int i=0; i<4; i++) { //Giro completo
			gameMiniTest.rotateCurrentPieceClockwise(); 
			saveGame(gameMiniTest);
		}
		gameMiniTest.moveCurrentPieceDown();
		saveGame(gameMiniTest);  
		try {
			gameMiniTest.rotateCurrentPieceClockwise(); //No se puede girar
		} catch (OffBoardMovementException e) {
		
		}
		saveGame(gameMiniTest);
		assertFalse (gameMiniTest.isCurrentPieceFixed());
		assertFalse(gameMiniTest.isGameEnded());
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/rotateclockwise.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	}

	//Rotamos continuamente una pieza  y la bajamos hasta el final del tablero
		@Test
		public void testRotateCounterclockwise() throws TetrisException {
			
			ps = openFileForWritingStudentOutput("test/files/P2/rotatecounterclockwise.alu");

			assertTrue(gameMiniTest.nextPiece("I")); //Pieza 
			saveGame(gameMiniTest);	
			gameMiniTest.rotateCurrentPieceCounterclockwise(); 
			saveGame(gameMiniTest);
			gameMiniTest.moveCurrentPieceDown();
			saveGame(gameMiniTest);		
			gameMiniTest.rotateCurrentPieceCounterclockwise(); 
			saveGame(gameMiniTest);
			gameMiniTest.moveCurrentPieceDown();
			saveGame(gameMiniTest);
			gameMiniTest.rotateCurrentPieceCounterclockwise(); 
			saveGame(gameMiniTest);
			gameMiniTest.rotateCurrentPieceCounterclockwise(); 
			saveGame(gameMiniTest);
			gameMiniTest.moveCurrentPieceDown();
			saveGame(gameMiniTest);
			
			try {
				gameMiniTest.rotateCurrentPieceClockwise(); //No se puede
			} catch (OffBoardMovementException e) {
				
			}
			saveGame(gameMiniTest);
			for (int i=0; i<4; i++) { //Giro completo
				gameMiniTest.rotateCurrentPieceCounterclockwise();
				saveGame(gameMiniTest);
			}
			
			assertFalse (gameMiniTest.isCurrentPieceFixed());
			gameMiniTest.moveCurrentPieceDown();
			saveGame(gameMiniTest);
			assertTrue(gameMiniTest.isCurrentPieceFixed());
				
			try {
				gameMiniTest.rotateCurrentPieceCounterclockwise(); //No se puede girar,pieza fija
			} catch (FixedPieceMovementException e) {

			}
			saveGame(gameMiniTest);
			assertFalse(gameMiniTest.isGameEnded());
			ps.close();
			
			sbIn=readSolutionFromFile("test/files/P2/rotatecounterclockwise.sol");
			assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
		}
	
	@Test
	public void testMainP2() throws TetrisException {
	
		Game game= new Game(new Coordinate(7, 10));
		ps = openFileForWritingStudentOutput("test/files/P2/mainP2.alu");
		
		saveGame(game);
		game.nextPiece("I");
		saveGame(game);
		game.rotateCurrentPieceClockwise();
		saveGame(game);
		game.rotateCurrentPieceCounterclockwise();
		saveGame(game);
		game.rotateCurrentPieceCounterclockwise();
		saveGame(game);
		game.moveCurrentPieceLeft();
		saveGame(game);
		game.moveCurrentPieceRight();
		saveGame(game);
		game.moveCurrentPieceRight();
		saveGame(game);
		game.moveCurrentPieceDown();
		saveGame(game);
		game.moveCurrentPieceDown();
		saveGame(game);
		game.moveCurrentPieceDown();
		saveGame(game);
		game.moveCurrentPieceDown();
		saveGame(game);
		assertTrue(game.isCurrentPieceFixed());
		
		sbIn=readSolutionFromFile("test/files/P2/mainP2.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	}
	
	@Test
	public void testPartida1() throws TetrisException {
	
		Game game= new Game(new Coordinate(7, 10));
		ps = openFileForWritingStudentOutput("test/files/P2/partida1.alu");
		saveGame(game);
		game.nextPiece("I"); //Pieza 1
		saveGame(game);
		for (int i=0; i<3; i++) {
			game.moveCurrentPieceLeft(); 
		}
		saveGame(game);
		for (int i=0; i<5; i++) { //La bajamos hasta el límite
			game.moveCurrentPieceDown();
		}
		saveGame(game);
		for (int i=0; i<6; i++) {
			game.moveCurrentPieceRight(); //La movemos sobre la última fila
		}
		saveGame(game);
		game.moveCurrentPieceDown(); //Fija pieza
		saveGame(game);
		
		game.nextPiece("I"); //Pieza 2
		saveGame(game);
		for (int i=0; i<4; i++) { //La bajamos hasta limite con pieza 1
			game.moveCurrentPieceDown();
		}
		saveGame(game);
		game.moveCurrentPieceDown(); //Fija por impedimento de la pieza 1
		saveGame(game);
		
		game.nextPiece("I"); //Pieza 3
		saveGame(game);
		for (int i=0; i<3; i++) {
			game.moveCurrentPieceRight(); 
		}
		saveGame(game);
		for (int i=0; i<4; i++) { //La bajamos hasta hacerla fija
			game.moveCurrentPieceDown();
		}
		saveGame(game);
		
		game.nextPiece("I"); //Pieza 4
		saveGame(game);
		game.moveCurrentPieceLeft();
		saveGame(game);
		for (int i=0; i<4; i++) { //La bajamos hasta hacerla fija
			game.moveCurrentPieceDown();
		}
		saveGame(game);
		
		game.nextPiece("I"); //Pieza 5
		saveGame(game);
		for (int i=0; i<3; i++) { //La movemos hasta el límite izquierdo
			game.moveCurrentPieceLeft(); 
		}
		saveGame(game);
		game.rotateCurrentPieceCounterclockwise();
		saveGame(game);
		for (int i=0; i<3; i++) { //La bajamos hasta límite inferior
			game.moveCurrentPieceDown();
		}
		saveGame(game);
		game.moveCurrentPieceLeft();
		saveGame(game);
		game.moveCurrentPieceDown(); //Pieza fija
		saveGame(game);
		
		game.nextPiece("I"); //Pieza 7
		saveGame(game);
		for (int i=0; i<3; i++) { //La movemos hasta límite derecho
			game.moveCurrentPieceRight();
		}
		saveGame(game);
		for (int i=0; i<3; i++) { //La bajamos hasta hacerla fija 
			game.moveCurrentPieceDown();
		}
		saveGame(game);
		
		game.nextPiece("I"); //Pieza 8
		saveGame(game);
		game.moveCurrentPieceDown();
		saveGame(game);
		for (int i=0; i<3; i++) { //La movemos hasta límite izquierdo
			game.moveCurrentPieceLeft();
		}
		saveGame(game);
		game.moveCurrentPieceDown(); //La hacemos fija
		saveGame(game);
		
		game.nextPiece("I"); //Pieza 9
		saveGame(game);
		game.rotateCurrentPieceCounterclockwise();
		saveGame(game);
		game.moveCurrentPieceRight();
		saveGame(game);
		game.moveCurrentPieceLeft();
		saveGame(game);
		game.moveCurrentPieceDown(); //La hacemos fija
		saveGame(game);
		
		game.nextPiece("I"); //No se puede poner
		saveGame(game);
		assertTrue(game.isGameEnded()); //Fin de partida
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P2/partida1.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	}
	
	
/* *****************************
 * FUNCIONES DE APOYO
 * *****************************/
	
	//Abre un fichero para escribir los tableros que genera el alumno.
	private PrintStream openFileForWritingStudentOutput (String fichero) {
			PrintStream s=null;
			try {
				s = new PrintStream(fichero);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			return s;
		}
		
	//Lee la solución de un fichero y la devuelve en un StringBuilder	
	private StringBuilder readSolutionFromFile(String file) {
		Scanner sc=null;
		try {
				sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		while (sc.hasNext()) 
			sb.append(sc.nextLine()+"\n");			
		sc.close();
		return (sb);
	}
	
	/* Almacena la partida en un fichero como un string para el alumno,
	   y en un StringBuilder para compararla luego con la solución */
	private void saveGame(Game game) {
		sbOut.append(game.toString()+"\n");
		ps.println(game.toString());
	}
}
