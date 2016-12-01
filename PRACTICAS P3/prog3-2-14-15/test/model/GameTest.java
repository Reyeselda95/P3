package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class GameTest {
	Game game;

	@Before
	public void setUp() throws Exception {
		Coordinate c = new Coordinate(7, 10);
		game = new Game(c);
		assertEquals("··········\n··········\n··········\n··········\n··········\n··········\n··········\n", game.toString());

	}

	@Test
	public void testGame() {
		Coordinate c = new Coordinate(7, 10);
		game = new Game(c);
		assertFalse(game.isGameEnded());
	}

	@Test
	public void testNextPiece() {

	}

	@Test
	public void testMoveCurrentPieceLeft() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveCurrentPieceRight() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveCurrentPieceDown() {
		fail("Not yet implemented");
	}

	@Test
	public void testRotateCurrentPieceCounterclockwise() {
		fail("Not yet implemented");
	}

	@Test
	public void testRotateCurrentPieceClockwise() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		assertEquals("··········\n··········\n··········\n··········\n··········\n··········\n··········\n", game.toString());
		game.nextPiece();
		assertEquals("··········\n···▒▒▒▒···\n··········\n··········\n··········\n··········\n··········\n", game.toString());

	}

	@Test
	public void testIsCurrentPieceFixed() {
		game.nextPiece();
		assertFalse(game.isCurrentPieceFixed());
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

		// Siguiente pieza que se deberia parar al tocar con la pieza anterior

		game.nextPiece();
		assertEquals("Aun no fija", false, game.isCurrentPieceFixed());

		game.moveCurrentPieceDown();
		game.moveCurrentPieceDown();

		// System.out.println(game.toString());
		assertTrue(game.isCurrentPieceFixed());
	}

	@Test
	public void testIsGameEnded() {
		assertFalse(game.isGameEnded());
		game.nextPiece();
		game.nextPiece();
		assertTrue(game.isGameEnded());

	}

}
