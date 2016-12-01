package model;

import static org.junit.Assert.*;
import model.Coordinate;
import model.Gameboard;
import model.exceptions.*;

import org.junit.Before;
import org.junit.Test;

public class GameboardExceptionsTestP3 {
    
    Gameboard gb, gb1;
	

	@Before
	public void setUp() throws Exception {
		gb1 = new Gameboard(new Coordinate(6,7));
	}

	@Test
	public void testGameBoardConstructorExceptions1() {
		try {
			gb = new Gameboard(new Coordinate(3,4));
			fail("Error: no se lanzó la excepción WrongSizeException");
		} catch (WrongSizeException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testGameBoardConstructorExceptions2() {
		try {
			gb = new Gameboard(new Coordinate(4,3));
			fail("Error: no se lanzó la excepción WrongSizeException");
		} catch (WrongSizeException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}

	@Test
	public void testGameBoardConstructorExceptions3() {
		try {
			gb = new Gameboard(new Coordinate(0,0));
			fail("Error: no se lanzó la excepción WrongSizeException");
		} catch (WrongSizeException e) {
			assertNotNull(e.getMessage());
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
		
	@Test
	public void testGameBoardConstructorExceptions4() {
		try {
			gb = new Gameboard(new Coordinate(4,4));
			//No debe lanzarse ninguna excepción
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	
	@Test
	public void testGameBoardclearRowExceptions1() {
		try {		
			gb1.clearRow(6);
		} catch (IllegalArgumentException e) {
			
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testGameBoardclearRowExceptions2() {
		try {		
			gb1.clearRow(-1);
		} catch (IllegalArgumentException e) {
			
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
		
	@Test
	public void testGameBoardclearRowExceptions3() {
		//No se debe producir excepción
		try {		
			gb1.clearRow(0);
			gb1.clearRow(5);
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}

		
	@Test
	public void testGameBoardmakeUpperRowsFallExceptions1() {
		try {		
			gb1.makeUpperRowsFall(6);
		} catch (IllegalArgumentException e) {
			
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testGameBoardmakeUpperRowsFallExceptions2() {
		try {		
			gb1.makeUpperRowsFall(-1);
		} catch (IllegalArgumentException e) {
			
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
		
	@Test
	public void testGameBoardmakeUpperRowsFallExceptions3() {
		//No se debe producir excepción
		try {		
			gb1.makeUpperRowsFall(0);
			gb1.makeUpperRowsFall(5);
		} catch (Exception e) {
			fail ("Error: se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
}
