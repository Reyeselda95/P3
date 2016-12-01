package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PieceTest {
	Piece p;

	@Before
	public void setUp() throws Exception {
		p = new Piece();
	}

	@Test
	public void testPiece() {
		assertEquals(Rotation.D0, p.getOrientation());
		assertFalse(p.isFixed());
		assertEquals('▒', p.getBlockSymbol());
	}

	@Test
	public void testPiecePiece() {
		p.setOrientation(Rotation.D90);
		p.setFixed(true);
		assertEquals(Rotation.D90, p.getOrientation());
		assertTrue(p.isFixed());
		assertEquals('▒', p.getBlockSymbol());
	}

	@Test
	public void testIsFixed() {
		p.setFixed(true);
		assertTrue(p.isFixed());
		p.setFixed(false);
		assertFalse(p.isFixed());
	}

	@Test
	public void testGetOrientation() {
		assertEquals("Piece p.getOrientation()", Rotation.D0, p.getOrientation());
	}

	@Test
	public void testGetBlockSymbol() {
		assertEquals("Piece p.getBlockSymbol()", '▒', p.getBlockSymbol());
	}

	@Test
	public void testSetOrientation() {
		p.setOrientation(Rotation.D0);
		assertEquals("Piece p.setOrientation(D0)", Rotation.D0, p.getOrientation());
		p.setOrientation(Rotation.D90);
		assertEquals("Piece p.setOrientation(D90)", Rotation.D90, p.getOrientation());
		p.setOrientation(Rotation.D180);
		assertEquals("Piece p.setOrientation(D180)", Rotation.D180, p.getOrientation());
		p.setOrientation(Rotation.D270);
		assertEquals("Piece p.setOrientation(D270)", Rotation.D270, p.getOrientation());
	}

	@Test
	public void testRotateClockwise() {
		p.rotateClockwise();
		assertEquals("Piece p.rotateClockwise()", Rotation.D270, p.getOrientation());
		p.rotateClockwise();
		assertEquals("Piece p.rotatelockwise()", Rotation.D180, p.getOrientation());
		p.rotateClockwise();
		assertEquals("Piece p.rotateClockwise()", Rotation.D90, p.getOrientation());
		p.rotateClockwise();
		assertEquals("Piece p.rotateClockwise()", Rotation.D0, p.getOrientation());
	}

	@Test
	public void testRotateCounterclockwise() {
		p.rotateCounterclockwise();
		assertEquals("Piece p.rotateCounterclockwise()", Rotation.D90, p.getOrientation());
		p.rotateCounterclockwise();
		assertEquals("Piece p.rotateCounterclockwise()", Rotation.D180, p.getOrientation());
		p.rotateCounterclockwise();
		assertEquals("Piece p.rotateCounterclockwise()", Rotation.D270, p.getOrientation());
		p.rotateCounterclockwise();
		assertEquals("Piece p.rotateCounterclockwise()", Rotation.D0, p.getOrientation());
	}

	@Test
	public void testGetAbsoluteCells() {
		Coordinate c=new Coordinate(2,4);
		p.setOrientation(Rotation.D0);
		Set <Coordinate> squares= new HashSet<Coordinate>();
		squares.add(new Coordinate(3, 4));
		squares.add(new Coordinate(3, 5));
		squares.add(new Coordinate(3, 6));
		squares.add(new Coordinate(3, 7));
		assertEquals(squares, p.getAbsoluteCells(c));
		squares.clear();
		p.setOrientation(Rotation.D90);
		squares.add(new Coordinate(2, 5));
		squares.add(new Coordinate(3, 5));
		squares.add(new Coordinate(4, 5));
		squares.add(new Coordinate(5, 5));
		assertEquals(squares, p.getAbsoluteCells(c));
		squares.clear();
		p.setOrientation(Rotation.D180);
		squares.add(new Coordinate(4, 4));
		squares.add(new Coordinate(4, 5));
		squares.add(new Coordinate(4, 6));
		squares.add(new Coordinate(4, 7));
		assertEquals(squares, p.getAbsoluteCells(c));
		squares.clear();
		p.setOrientation(Rotation.D270);
		squares.add(new Coordinate(2, 6));
		squares.add(new Coordinate(3, 6));
		squares.add(new Coordinate(4, 6));
		squares.add(new Coordinate(5, 6));
		assertEquals(squares, p.getAbsoluteCells(c));
	}

	@Test
	public void testSetFixed() {
		p.setFixed(false);
		assertFalse(p.isFixed());
		p.setFixed(true);
		assertTrue(p.isFixed());
	}

	@Test
	public void testToString() {
		Piece p = new Piece();
		assertEquals("Piece p.toString()", "····\n▒▒▒▒\n····\n····\n", p.toString());
		p.setOrientation(Rotation.D90);
		assertEquals("Piece p.toString()", "·▒··\n·▒··\n·▒··\n·▒··\n", p.toString());
		p.setOrientation(Rotation.D180);
		assertEquals("Piece p.toString()", "····\n····\n▒▒▒▒\n····\n", p.toString());
		p.setOrientation(Rotation.D270);
		assertEquals("Piece p.toString()", "··▒·\n··▒·\n··▒·\n··▒·\n", p.toString());
	}

}
