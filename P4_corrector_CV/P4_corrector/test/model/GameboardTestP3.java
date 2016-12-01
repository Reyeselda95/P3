package model;

import static org.junit.Assert.*;

import model.exceptions.WrongSizeException;

import org.junit.Before;
import org.junit.Test;

public class GameboardTestP3 {
	Gameboard gb;
	Piece p[],ip, jp, lp, op, sp, tp, zp;

	@Before
	public void setUp() throws Exception {
		p = new Piece[7];
		p[0] = PieceFactory.createPiece("I");
		p[1] = PieceFactory.createPiece("J");
		p[2] = PieceFactory.createPiece("L");
		p[3] = PieceFactory.createPiece("O");
		p[4] = PieceFactory.createPiece("S");
		p[5] = PieceFactory.createPiece("T");
		p[6] = PieceFactory.createPiece("Z");
		gb = new Gameboard(new Coordinate(4,7));
		gb = fullGameboard();
		
		
	}

	
	@Test
	public void testFirstRowFullFromBottom1() {
		//Ninguna pieza es fija
		assertEquals(-1,gb.firstRowFullFromBottom());
		gb.setCellContent(new Coordinate(3,6), p[6]);
		assertEquals(-1,gb.firstRowFullFromBottom());
		gb.setCellContent(new Coordinate(2,0), p[0]);
		assertEquals(-1,gb.firstRowFullFromBottom());
		gb.setCellContent(new Coordinate(3,0), p[0]);
		assertEquals(-1,gb.firstRowFullFromBottom());	
		
	}
	@Test
	public void testFirstRowFullFromBottom2() {
		
		//Hacemos fijas a todas las piezas
		for (int i=0; i<7; i++)
					p[i].setFixed(true);
		
		assertEquals(-1,gb.firstRowFullFromBottom());
		gb.setCellContent(new Coordinate(0,6), p[6]);
		assertEquals(0,gb.firstRowFullFromBottom());
		gb.setCellContent(new Coordinate(2,0), p[0]);
		assertEquals(2,gb.firstRowFullFromBottom());
		gb.setCellContent(new Coordinate(3,0), p[0]);
		assertEquals(3,gb.firstRowFullFromBottom());	
		
	}
		

	@Test
	public void testClearRow() {
		for (int i=0; i<gb.getHeight(); i++) {
			gb.clearRow(i);
			for (int j=0; j<gb.getWidth(); j++) assertNull(gb.getCellContent(new Coordinate(i,j)));
		}
	}

	@Test
	public void testMakeUpperRowsFall1() {
		Gameboard gbaux = fullGameboard();
		gb.makeUpperRowsFall(0); //No pasa nada
		Coordinate c;
		for (int i=0; i<gb.getHeight(); i++) 
			for (int j=0; j<gb.getWidth(); j++) {
				c = new Coordinate(i,j);
				assertEquals(gbaux.getCellContent(c), gb.getCellContent(c));
			}
		gb.makeUpperRowsFall(1);
		gb.makeUpperRowsFall(2);
		gb.makeUpperRowsFall(3);
		for (int i=0; i<gb.getHeight(); i++) 
			for (int j=0; j<gb.getWidth(); j++) {
				c = new Coordinate(i,j);
				if (i==3) assertEquals(p[j], gb.getCellContent(c));
				else  assertNull(gb.getCellContent(c));
			}		
	}
	
	@Test
	public void testMakeUpperRowsFall2() {
		try {
			Gameboard gbaux = new Gameboard(new Coordinate(4,7));
			Coordinate c;
			for (int i=0; i<gbaux.getHeight(); i++) 
				for (int j=0; j<gbaux.getWidth(); j++) {
					c=new Coordinate(i,j);
					if (i!=3) gbaux.setCellContent(c, p[i]);
				}
			gbaux.makeUpperRowsFall(0); //No pasa nada
			gbaux.makeUpperRowsFall(1);
			gbaux.makeUpperRowsFall(2);
			gbaux.makeUpperRowsFall(3);
			for (int i=0; i<gbaux.getHeight(); i++) 
				for (int j=0; j<gbaux.getWidth(); j++) {
					c = new Coordinate(i,j);
					if (i==3) assertEquals(p[0], gbaux.getCellContent(c));
					else  assertNull(gbaux.getCellContent(c));
				}	
		} catch (WrongSizeException e) {
			fail("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	
	//Funciones auxiliares
	private Gameboard fullGameboard () {
		Gameboard gb=null;
		try {
			gb = new Gameboard(new Coordinate(4,7));
		
		for (int j=0; j<gb.getWidth()-1; j++) {
			gb.setCellContent(new Coordinate(0,j), p[j]);
			gb.setCellContent(new Coordinate(2,j+1), p[j+1]);
			gb.setCellContent(new Coordinate(3,j+1), p[j+1]);				
		}
		//gb.setCellContent(new Coordinate(2,6), p[6]);
		} catch (WrongSizeException e) {
			fail("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
		return gb;
	}
}
