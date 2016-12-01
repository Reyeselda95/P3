package model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import model.exceptions.TetrisException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTestP3 {
    Game game;
    Piece p[];
    StringBuilder sbIn = new StringBuilder();
	StringBuilder sbOut = new StringBuilder();
	PrintStream ps;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sbIn = new StringBuilder();
		sbOut = new StringBuilder();
		game = new Game(new Coordinate(9,5));
		p = new Piece[7];
		p[0] = PieceFactory.createPiece("I");
		p[1] = PieceFactory.createPiece("J");
		p[2] = PieceFactory.createPiece("L");
		p[3] = PieceFactory.createPiece("O");
		p[4] = PieceFactory.createPiece("S");
		p[5] = PieceFactory.createPiece("T");
		p[6] = PieceFactory.createPiece("Z");
		for (int i=0; i<7; i++) p[i].setFixed(true);
	}
    
	
	
	//Elimina linea del fondo llena
	@Test
	public void testMoveCurrentPieceDown1() {
		ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown1.alu");
		Gameboard gb = game.getGameboard();
		Piece p = PieceFactory.createPiece("I");
		p.setFixed(true);
		for (int i=1; i<gb.getWidth(); i++)
		gb.setCellContent(new Coordinate(gb.getHeight()-1,i), p);
		saveGame(game);
		try {
			game.nextPiece("Z");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			for (int i=0; i<7; i++)
							game.moveCurrentPieceDown();
		} catch (TetrisException e) {
			fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
		}
		saveGame(game);
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P3/testMoveCurrentPieceDown1.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	}
	
	//Eliminación 4 primeras lineas llenas desde el fondo
	@Test
	public void testMoveCurrentPieceDown2() {
		ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown2.alu");
		Gameboard gb = game.getGameboard();
		Piece p = PieceFactory.createPiece("O");
		p.setFixed(true);
		for (int i=5; i<gb.getHeight();  i++)
			for (int j=1; j<gb.getWidth();j++)
					gb.setCellContent(new Coordinate(i,j), p);
		saveGame(game);
		try {
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();	
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<6; i++){
							game.moveCurrentPieceDown();
							saveGame(game);
			}
		} catch (TetrisException e) {
			fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
		}
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P3/testMoveCurrentPieceDown2.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
		
	}
	
	//Eliminación fila del fondo llena y bajan las de arriba
	@Test
	public void testMoveCurrentPieceDown3() {
			ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown3.alu");
			prepareGameboard(game);
			saveGame(game);
			try {
				game.nextPiece("I");
				saveGame(game);
				game.rotateCurrentPieceCounterclockwise();	
				saveGame(game);
				game.moveCurrentPieceLeft();
				saveGame(game);
				for (int i=0; i<6; i++){
								game.moveCurrentPieceDown();
								saveGame(game);
				}
				assertTrue(game.isCurrentPieceFixed());
			} catch (TetrisException e) {
				fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
			}
			ps.close();
			
			sbIn=readSolutionFromFile("test/files/P3/testMoveCurrentPieceDown3.sol");
			assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
			
		}
	
	//Eliminación fila intermedia llena y bajan las de arriba
	@Test
	public void testMoveCurrentPieceDown4() {
			ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown4.alu");
			Gameboard gb = game.getGameboard();
			prepareGameboard(game);
			gb.setCellContent(new Coordinate(7,0),p[4]);
			gb.setCellContent(new Coordinate(6,1), p[3]);
			saveGame(game);
			try {
				game.nextPiece("I");
				saveGame(game);
				game.rotateCurrentPieceCounterclockwise();	
				saveGame(game);
				game.moveCurrentPieceLeft();
				saveGame(game);
				for (int i=0; i<4; i++){
								game.moveCurrentPieceDown();
								saveGame(game);
				}
				assertTrue(game.isCurrentPieceFixed());
			} catch (TetrisException e) {
				fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
			}
			ps.close();
			
			sbIn=readSolutionFromFile("test/files/P3/testMoveCurrentPieceDown4.sol");
			assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
			
		}
	
	//Eliminación 2 filas intermedias separadas llenas y bajan las de arriba
	@Test
	public void testMoveCurrentPieceDown5() {
			ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown5.alu");
			Gameboard gb = game.getGameboard();
			prepareGameboard(game);
			gb.setCellContent(new Coordinate(7,0),p[4]);
			gb.setCellContent(new Coordinate(6,1), p[3]);
			gb.setCellContent(new Coordinate(4,4), p[5]);
			saveGame(game);
			try {
				game.nextPiece("I");
				saveGame(game);
				game.rotateCurrentPieceCounterclockwise();	
				saveGame(game);
				game.moveCurrentPieceLeft();
				saveGame(game);
				for (int i=0; i<4; i++){
								game.moveCurrentPieceDown();
								saveGame(game);
				}
				assertTrue(game.isCurrentPieceFixed());
			} catch (TetrisException e) {
				fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
			}
			ps.close();
			
			sbIn=readSolutionFromFile("test/files/P3/testMoveCurrentPieceDown5.sol");
			assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
		
		}
		
	//Eliminación 4 filas inferiores  llenas y bajan las de arriba
	@Test
	public void testMoveCurrentPieceDown6() {
			ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown6.alu");
			Gameboard gb = game.getGameboard();
			prepareGameboard(game);
			gb.setCellContent(new Coordinate(5,2),p[4]);
			gb.setCellContent(new Coordinate(6,1), p[3]);
			gb.setCellContent(new Coordinate(7,4), p[5]);
			saveGame(game);
			try {
				game.nextPiece("I");
				saveGame(game);
				game.rotateCurrentPieceCounterclockwise();	
				saveGame(game);
				game.moveCurrentPieceLeft();
				saveGame(game);
				for (int i=0; i<6; i++){
								game.moveCurrentPieceDown();
								saveGame(game);
				}
				assertTrue(game.isCurrentPieceFixed());
			} catch (TetrisException e) {
				fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
			}
			ps.close();
			
			sbIn=readSolutionFromFile("test/files/P3/testMoveCurrentPieceDown6.sol");
			assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
		
	}
	
	//Eliminación 3 filas  llenas y bajan las de arriba
	@Test
	public void testMoveCurrentPieceDown7() {
			ps = openFileForWritingStudentOutput("test/files/P3/testMoveCurrentPieceDown7.alu");
			Gameboard gb = game.getGameboard();
			prepareGameboard(game);
			gb.setCellContent(new Coordinate(5,2),p[4]);
			gb.setCellContent(new Coordinate(6,1), p[3]);
			gb.setCellContent(new Coordinate(4,4), p[5]);
			saveGame(game);
			try {
				game.nextPiece("I");
				saveGame(game);
				game.rotateCurrentPieceCounterclockwise();	
				saveGame(game);
				game.moveCurrentPieceLeft();
				saveGame(game);
				for (int i=0; i<6; i++){
								game.moveCurrentPieceDown();
								saveGame(game);
				}
				assertTrue(game.isCurrentPieceFixed());
			} catch (TetrisException e) {
				fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
			}
			ps.close();
			
			sbIn=readSolutionFromFile("test/files/P3/testMoveCurrentPieceDown7.sol");
			assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
		
	}
	
	//Partida de tetris
	@Test
	public void testPartida1() {
		ps = openFileForWritingStudentOutput("test/files/P3/testPartida1.alu");
		try {
			saveGame(game);
			game.nextPiece("J");
			saveGame(game);
			for (int i=0; i<8; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("J");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<7; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("L");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			for (int i=0; i<3; i++) game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<7; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//se llenaron las filas 7 y 8 y se eliminaron
			game.nextPiece("O");
			saveGame(game);
			for (int i=0; i<2; i++) game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<7; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("J");
			saveGame(game);
			for (int i=0; i<8; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//se llenó y eliminó fila 8
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("O");
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<7; i++) game.moveCurrentPieceDown();
			saveGame(game);		
			//se llenó y eliminó la fila 7
	
			game.nextPiece("L");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("O");
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<7; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//se llenaron y eliminaron las fila 6 y 7
			
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("Z");
			saveGame(game);
			//Fin de la partida
			assertTrue(game.isGameEnded());
			
		} catch (TetrisException e) {
			fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
		}
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P3/testPartida1.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	
	}
	@Test
	public void testPartida2() {
		ps = openFileForWritingStudentOutput("test/files/P3/testPartida2.alu");
		Gameboard gb = game.getGameboard();
		Piece piece = PieceFactory.createPiece("O");
		piece.setFixed(true);
		gb.setCellContent(new Coordinate(8,0), piece);
		saveGame(game);
		try {	
			game.nextPiece("L");
			saveGame(game);
			for (int i=0; i<2; i++) game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<8; i++) game.moveCurrentPieceDown();
			game.nextPiece("T");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<6; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceDown();
			saveGame(game);	
			//se llenó la fila 8 y se eliminó
			
			game.nextPiece("O");
			saveGame(game);
			game.moveCurrentPieceLeft();
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("Z");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceDown();
			saveGame(game);
			//se llenó la fila 8 y se eliminó
			game.nextPiece("J");
			saveGame(game);
			for (int i=0; i<3; i++) game.rotateCurrentPieceClockwise();	
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("Z");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("J");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<4; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("T");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<4; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);		
			game.nextPiece("O");
			saveGame(game);
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("T");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<6; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("I");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<3; i++)	game.moveCurrentPieceDown();
			saveGame(game);
			//Se eliminaron la filas 5,6,7 y 8
			game.nextPiece("L");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("Z");
			saveGame(game);
			//Fin de la partida
			assertTrue(game.isGameEnded());

	} catch (TetrisException e) {
			fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
		}
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P3/testPartida2.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	
	}
	
	
	//Partida de tetris
	@Test
	public void testPartida3() {
		ps = openFileForWritingStudentOutput("test/files/P3/testPartida3.alu");
		Gameboard gb = game.getGameboard();
		Piece p = PieceFactory.createPiece("O");
		p.setFixed(true);
		gb.setCellContent(new Coordinate(9,0), p);
		saveGame(game);
		try {
			game.nextPiece("I");
			saveGame(game);
			for (int i=0; i<8; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("S");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<2; i++) game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<7; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//Se llenó y borro la fila 8
			
			game.nextPiece("O");
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<8; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("S");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("Z");
			saveGame(game);
			for (int i=0; i<6; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("T");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<4; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("L");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			for (int i=0; i<3; i++) game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<4; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//Se llenó la fila 5, se borra y baja la parte de arriba
			
			game.nextPiece("T");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<4; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//Se llenó la fila 5, se borra y baja la parte de arriba
			
			game.nextPiece("O");
			saveGame(game);
			for (int i=0; i<2; i++) game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<4; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("S");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<4; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//Se borran filas 4 y 5
			
			game.nextPiece("Z");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			for (int i=0; i<4; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("L");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<3; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<3; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//Se elimina la fila 5 y 6
			
			game.nextPiece("J");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//se eliminó la fila 6
			
			game.nextPiece("L");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			for (int i=0; i<3; i++) game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//se eliminaron filas 5 y 6
			
			game.nextPiece("J");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<6; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//se eliminaron las filas 6 y 7
			
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("O");
			saveGame(game);
			for (int i=0; i<3; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("O");
			saveGame(game);
			game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("T");
			saveGame(game);
			//Fin de la partida
			assertTrue(game.isGameEnded());	
			
		} catch (TetrisException e) {
			fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
		}
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P3/testPartida3.sol");
		assertEquals("solucion == alumno",sbIn.toString().trim(),sbOut.toString().trim());
	
	}	
			
	//Partida de tetris
	@Test
	public void testPartida4() {
		ps = openFileForWritingStudentOutput("test/files/P3/testPartida4.alu");
		Gameboard gb = game.getGameboard();
		Piece p = PieceFactory.createPiece("O");
		p.setFixed(true);
		gb.setCellContent(new Coordinate(8,1), p);
		gb.setCellContent(new Coordinate(8,3), p);
		gb.setCellContent(new Coordinate(7,3), p);
		saveGame(game);
		try {		
			game.nextPiece("O");
			saveGame(game);
			for (int i=0; i<7; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			for (int i=0; i<6; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("J");
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<5; i++) game.moveCurrentPieceDown();
			saveGame(game);
	
			game.nextPiece("T");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<4; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("S");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			game.rotateCurrentPieceClockwise();
			saveGame(game);
			for (int i=0; i<3; i++) game.moveCurrentPieceDown();
			saveGame(game);
			//se elimino la fila 4
			game.nextPiece("I");
			saveGame(game);
			game.rotateCurrentPieceCounterclockwise();
			saveGame(game);
			game.moveCurrentPieceLeft();
			saveGame(game);
			game.moveCurrentPieceDown();
			game.moveCurrentPieceDown();
			saveGame(game);
			//Se eliminó la linea 4
			game.nextPiece("Z");
			saveGame(game);
			game.moveCurrentPieceRight();
			saveGame(game);
			for (int i=0; i<2; i++) game.moveCurrentPieceDown();
			saveGame(game);
			game.nextPiece("O");
			saveGame(game);
			//Fin de partida
			assertTrue(game.isGameEnded());
					
		} catch (TetrisException e) {
			fail("Error: se produjo la excepción "+e.getClass().getSimpleName());
		}
	
		ps.close();
		
		sbIn=readSolutionFromFile("test/files/P3/testPartida4.sol");
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
	
	private void prepareGameboard (Game game) {
		Gameboard gb = game.getGameboard();
		gb.setCellContent(new Coordinate(1,4), p[0]);
		gb.setCellContent(new Coordinate(2,2), p[1]);
		gb.setCellContent(new Coordinate(2,3), p[5]);
		gb.setCellContent(new Coordinate(2,4), p[2]);
		gb.setCellContent(new Coordinate(3,2), p[4]);
		gb.setCellContent(new Coordinate(3,3), p[4]);
		gb.setCellContent(new Coordinate(4,1), p[3]);
		gb.setCellContent(new Coordinate(4,2), p[3]);
		gb.setCellContent(new Coordinate(4,3), p[6]);
		gb.setCellContent(new Coordinate(5,1), p[2]);
		gb.setCellContent(new Coordinate(5,3), p[2]);
		gb.setCellContent(new Coordinate(5,4), p[3]);
		gb.setCellContent(new Coordinate(6,2), p[3]);
		gb.setCellContent(new Coordinate(6,3), p[3]);
		gb.setCellContent(new Coordinate(6,4), p[6]);
		gb.setCellContent(new Coordinate(7,1), p[4]);
		gb.setCellContent(new Coordinate(7,2), p[1]);
		gb.setCellContent(new Coordinate(7,3), p[2]);
		gb.setCellContent(new Coordinate(8,1), p[3]);
		gb.setCellContent(new Coordinate(8,2), p[0]);
		gb.setCellContent(new Coordinate(8,3), p[0]);
		gb.setCellContent(new Coordinate(8,4), p[4]);		
	}

}



