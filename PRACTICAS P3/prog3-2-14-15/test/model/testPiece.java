package model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
@author Jose vicente Lozano Copa
@date 25/09/2014
 **/
public class testPiece	 {
	Piece p;
	Coordinate c;
	
	@Before
	public void setUp() throws Exception {
		p = new Piece();
		c = new Coordinate(1, 1);
	}

	@Test
	public final void testConstructor() {
		assertEquals("Block", '▒', p.getBlockSymbol());
		assertEquals("Fixed", false, p.isFixed());
	}

	@Test
	public final void testRotateAndString() {
		
		//Esta prueba gira la pieza a derecha e izquierda y verifica que su toString coincide con lo esperado
		
		assertEquals("toString", "····\n▒▒▒▒\n····\n····\n", p.toString());
		
		p.rotateClockwise();
		assertEquals("toString", "··▒·\n··▒·\n··▒·\n··▒·\n", p.toString());
		
		p.rotateClockwise();
		assertEquals("toString", "····\n····\n▒▒▒▒\n····\n", p.toString());
		
		p.rotateClockwise();
		assertEquals("toString", "·▒··\n·▒··\n·▒··\n·▒··\n", p.toString());

		p.rotateCounterclockwise();
		assertEquals("toString", "····\n····\n▒▒▒▒\n····\n", p.toString());
		
		p.rotateCounterclockwise();
		assertEquals("toString", "··▒·\n··▒·\n··▒·\n··▒·\n", p.toString());
		
		p.rotateCounterclockwise();
		assertEquals("toString", "····\n▒▒▒▒\n····\n····\n", p.toString());
	}
	

	@Test
	public final void testAbsoluteCells()
	{
		c = new Coordinate(2, 2);
		Set<Coordinate> retorno= new HashSet<Coordinate>();
		
		retorno = p.getAbsoluteCells(c);
		
		//Si todo esta bien, el HashSet debería contener las coordenadas (3,2)(3,3)(3,4)(3,5)
		assertEquals("Absolute Coordenada 1", true, retorno.contains(new Coordinate(3,2)));
		assertEquals("Absolute Coordenada 2", true, retorno.contains(new Coordinate(3,3)));
		assertEquals("Absolute Coordenada 3", true, retorno.contains(new Coordinate(3,4)));
		assertEquals("Absolute Coordenada 4", true, retorno.contains(new Coordinate(3,5)));
		
		//Estas no estan, deben retornar False
		assertEquals("Absolute Erronea 1", false, retorno.contains(new Coordinate(2,2)));
		assertEquals("Absolute Erronea 2", false, retorno.contains(new Coordinate(2,4)));
		assertEquals("Absolute Erronea 3", false, retorno.contains(new Coordinate(4,3)));
		assertEquals("Absolute Erronea 4", false, retorno.contains(new Coordinate(4,5)));
	}

	@Test
	public final void gettersSetters()
	{
		assertEquals("No Fixed", false, p.isFixed());
		p.setFixed(true);
		assertEquals("Si Fixed", true, p.isFixed());
	}
	
}
