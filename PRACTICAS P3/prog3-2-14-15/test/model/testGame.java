package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testGame {

	Game game;
	
	@Before
	public void setUp() throws Exception {
	
		game = new Game(new Coordinate(7, 10));
		//System.out.println(game.toString());	
	}
	
	
	@Test
	public void testRotate() {
		game.nextPiece();
		game.rotateCurrentPieceCounterclockwise();
		assertEquals("Rot Antihorario", "····▒·····\n····▒·····\n····▒·····\n····▒·····\n··········\n··········\n··········\n", game.toString());
		game.rotateCurrentPieceClockwise();
		assertEquals("Rot Antihorario", "··········\n···▒▒▒▒···\n··········\n··········\n··········\n··········\n··········\n", game.toString());
	}
	
	@Test
	public void moverPieza() {
		game.nextPiece();
		game.moveCurrentPieceDown();
		assertEquals("Abajo", "··········\n··········\n···▒▒▒▒···\n··········\n··········\n··········\n··········\n", game.toString());
		game.moveCurrentPieceLeft();
		assertEquals("Izquierda", "··········\n··········\n··▒▒▒▒····\n··········\n··········\n··········\n··········\n", game.toString());
		game.moveCurrentPieceRight();
		assertEquals("Derecha", "··········\n··········\n···▒▒▒▒···\n··········\n··········\n··········\n··········\n", game.toString());
	}
	
	@Test
	public final void jugarHastaFijar()
	{
		/*
		 * Voy a colocar varias piezas hasta que se monten unas sobre otras, me aseguro de que se fijan
		 */
		
		game.nextPiece();
		game.rotateCurrentPieceClockwise();
		game.moveCurrentPieceDown();
		game.moveCurrentPieceDown();
		game.moveCurrentPieceDown();
		
		assertEquals("Aun no fija", false, game.isCurrentPieceFixed());
		
		game.moveCurrentPieceDown();
		assertEquals("Ahora si fija", true, game.isCurrentPieceFixed());

		//Siguiente pieza que se deberia parar al tocar con la pieza anterior
		
		game.nextPiece();
		assertEquals("Aun no fija", false, game.isCurrentPieceFixed());
		
		game.moveCurrentPieceDown();
		game.moveCurrentPieceDown();
		
		assertEquals("Deberia fijarse a la anterior", true, game.isCurrentPieceFixed());
		
		//System.out.println(game.toString());
		
	}
}
