package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testBoard {
	Coordinate c;
	Gameboard b;
	Piece p;
	
	
	@Before
	public void setUp() throws Exception {
		
		c = new Coordinate(5, 7);
		b = new Gameboard(c);
		p = new Piece();
		b.putPiece(new Coordinate(0, 0), p);
		
	}
	
	@Test
	public final void getters()
	{
		assertEquals("height", 5, b.getHeight());
		assertEquals("width", 7, b.getWidth());
	}
	
	@Test
	public void testPutPiece_and_toString() {
		assertEquals("toString", "·······\n▒▒▒▒···\n·······\n·······\n·······\n", b.toString());
		b.putPiece(new Coordinate(2, 2), p);
		b.putPiece(new Coordinate(3, 3), p);
		assertEquals("toString", "·······\n▒▒▒▒···\n·······\n··▒▒▒▒·\n···▒▒▒▒\n", b.toString());
	}
	
	@Test
	public final void testPlaceValid()
	{
		//Pruebo en una posicion que es correcta
		assertEquals("Posicion Correcta", true,  b.isPlaceValid(new Coordinate(0,  1), p));

		//Pruebo una posicion donde un cuadro de la piece se saldria del tablero
		assertEquals("Posicion Erronea",  false, b.isPlaceValid(new Coordinate(4,  1), p));
	}
	
	@Test
	public final void testPlaceFree()
	{
		Piece p2 = new Piece();
		
		//p2 esta en la misma posicion y angulo que p, la funcion debe retornar false
		assertEquals("Posicion ocupada", false,  b.isPlaceFree(new Coordinate(0,  0), p2));
		
		//Roto la pieza 90 grados y seguira colisionando con p
		p2.rotateClockwise();
		assertEquals("Posicion ocupada", false,  b.isPlaceFree(new Coordinate(0,  0), p2));
		
		//LA roto otros 90 grados y ahora no deberia colisionar
		p2.rotateClockwise();
		assertEquals("Posicion libre", true,  b.isPlaceFree(new Coordinate(0,  0), p2));
	}
	
	@Test
	public final void testSet_get_cell()
	{
		assertEquals("Nada", null, b.getCellContent(new Coordinate(0, 0)));
		assertEquals("Nada", p, b.getCellContent(new Coordinate(1, 1)));
		
		b.setCellContent(new Coordinate(0,  0), p);
		assertEquals("Nada", p, b.getCellContent(new Coordinate(0, 0)));
	}
	
	
	@Test
	public final void testRemovePiece()
	{
		//quito la pieza p del tablero
		b.removePiece(p);
		
		//Una vez borrada la pieza, en la posicion 1,1 no deberia haber nada
		assertEquals("Nada", null, b.getCellContent(new Coordinate(1, 1)));
	}
	
}
