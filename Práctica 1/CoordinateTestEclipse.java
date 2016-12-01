package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
@author jgonzalo
@date 10/09/2014
 **/
public class CoordinateTestEclipse {
	Coordinate c;
	
	@Before
	public void setUp() throws Exception {
		c = new Coordinate(3, 5);
				
	}

	@Test
	public final void testGetters() {
		assertEquals("Row", 3, c.getRow());
		assertEquals("Column", 5, c.getColumn());
		
	}

	@Test
	public final void testInicializacion() {
		Coordinate c3 = new Coordinate(100,25);
		Coordinate c2 = new Coordinate(c);
		assertEquals("c2.Row", c2.getRow(), c.getRow());
		assertEquals("c2.Column", c2.getColumn(), c.getColumn());
		
	}
	
	
		
	@Test
	public final void testToString() {
		assertEquals("Coordinate.toString()","[3,5]",c.toString());
		
	}
	
	
}
