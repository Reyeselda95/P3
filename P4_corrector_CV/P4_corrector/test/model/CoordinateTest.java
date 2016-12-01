package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
@author jgonzalo
@date 10/09/2014
 **/
public class CoordinateTest {
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
		Coordinate c2 = new Coordinate(c);
		assertEquals("c2.Row", c2.getRow(), c.getRow());
		assertEquals("c2.Column", c2.getColumn(), c.getColumn());
		
	}
	
	@Test
	public final void testEquals() {
		Coordinate c4 = new Coordinate(3,5);
		Coordinate c5 = new Coordinate(3,6);
		Coordinate c6 = new Coordinate(4,5);
		String s = new String();
		assertFalse(c.equals(null));
		assertFalse(c.equals(s));
		assertFalse(c.equals(c5));
		assertFalse(c.equals(c6));
		assertTrue(c.equals(c));
		assertTrue(c.equals(c4));
		
	
	}
	
		
	@Test
	public final void testToString() {
		assertEquals("Coordinate.toString()","[3,5]",c.toString());
		
	}
	
	@Test
	public final void testAdd() {
		Coordinate c7 = new Coordinate(c.add(c));
		assertEquals("c.add(c).x",6,c7.getRow());
		assertEquals("c.add(c).y",10,c7.getColumn());
		assertEquals("c.add(c7)","[9,15]",c.add(c7).toString());
		
	}
	
}
