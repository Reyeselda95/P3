package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameboardTest {
	Coordinate c;
	Gameboard gameboard;
	Piece p;

	@Before
	public void setUp() throws Exception {
		c = new Coordinate(7, 10);
		gameboard = new Gameboard(c);
		p = new Piece();
	}

	@Test
	public void testGameboard() {
		gameboard = new Gameboard(c);
		assertEquals(c.getRow(), gameboard.getHeight());
		assertEquals(c.getColumn(), gameboard.getWidth());
	}

	@Test
	public void testPutPiece() {
		assertEquals(7, gameboard.getHeight());
		assertEquals(10, gameboard.getWidth());
	}

	@Test
	public void testGetWidth() {
		Coordinate c = new Coordinate(20, 56);
		gameboard = new Gameboard(c);
		assertEquals(56, gameboard.getWidth());
	}

	@Test
	public void testGetHeight() {
		Coordinate c = new Coordinate(20, 56);
		gameboard = new Gameboard(c);
		assertEquals(20, gameboard.getHeight());
	}

	@Test
	public void testIsPlaceValid() {
		Coordinate c1 = new Coordinate(0, 5);
		Coordinate c2 = new Coordinate(0, -1);
		Coordinate c3 = new Coordinate(0, 20);
		Coordinate c4 = new Coordinate(20, 0);
		Coordinate c5 = new Coordinate(-2, 0);
		Piece p = new Piece();
		assertFalse(gameboard.isPlaceValid(c2, p));
		assertFalse(gameboard.isPlaceValid(c3, p));
		assertFalse(gameboard.isPlaceValid(c4, p));
		assertFalse(gameboard.isPlaceValid(c5, p));
		assertTrue(gameboard.isPlaceValid(c1, p));
	}

	@Test
	public void testIsPlaceFree() {
		Coordinate c= new Coordinate(2,4);
		Coordinate c1 = new Coordinate(3, 4);
		Piece p= new Piece();
		assertTrue(gameboard.isPlaceFree(c, p));
		gameboard.putPiece(c, p);
		assertFalse(gameboard.isPlaceFree(c, p));
		assertTrue(gameboard.isPlaceFree(c1, p));

	}

	@Test
	public void testRemovePiece() {
		Coordinate c = new Coordinate(2, 4);
		Piece p = new Piece();
		gameboard.putPiece(c, p);
		assertFalse(gameboard.isPlaceFree(c, p));
		gameboard.removePiece(p);
		assertTrue(gameboard.isPlaceFree(c, p));
	}

	@Test
	public void testGetCellContent() {
		Coordinate c1 = new Coordinate(2, 4);
		Coordinate c2 = new Coordinate(3, 4);
		Piece p = new Piece();
		gameboard.putPiece(c1, p);
		assertEquals(p, gameboard.getCellContent(c2));
	}

	@Test
	public void testSetCellContent() {
		Coordinate c = new Coordinate(2, 4);
		Piece p = new Piece();
		gameboard.setCellContent(c, p);
		assertEquals(p, gameboard.getCellContent(c));
	}

	@Test
	public void testToString() {
		Coordinate c1 = new Coordinate(0, 0);
		Coordinate c2 = c1.add(new Coordinate(1, 0));
		Piece p = new Piece();
		gameboard.putPiece(c1, p);
		assertEquals("··········\n▒▒▒▒······\n··········\n··········\n··········\n··········\n··········\n", gameboard.toString());
		gameboard.putPiece(c2, p);
		assertEquals("··········\n▒▒▒▒······\n▒▒▒▒······\n··········\n··········\n··········\n··········\n", gameboard.toString());
	}

}
