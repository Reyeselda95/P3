package model.io;

import static org.junit.Assert.*;

import model.exceptions.io.TetrisIOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerStringTestP4 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=NullPointerException.class)
	public void testPlayerString() {
		new PlayerString(null);
	}

	@Test
	public void testNextMove() {
		String m = new String("I↻↻J↺L→→→→O←S↓↓↓↓←←↓↓↓↓T↓↓→↓↓Z↺↺");
		PlayerString pl = new PlayerString(m);
		try {
		for (int i=0; i<m.length(); i++) {
				assertEquals(m.charAt(i), pl.nextMove());	
		}
		assertEquals (IPlayer.LAST_MOVE, pl.nextMove());
		} catch (TetrisIOException e) {
			fail ("Error, lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	//Prueba de TetrisIOException
	@Test(expected=TetrisIOException.class)
	public void testNextMoveException1() throws TetrisIOException {
		String m = new String("I↻↻J↺L→→→→O←M↓↓↓↓←←↓↓↓↓T↓↓→↓↓Z↺↺");
	
		PlayerString pl = new PlayerString(m);
		
		for (int i=0; i<m.length(); i++) {
				assertEquals(m.charAt(i), pl.nextMove());	
		}
	}
		

	@Test(expected=TetrisIOException.class)
	public void testNextMoveException2() throws TetrisIOException {
		String m = new String("I↻↻J↺L→→→→O←Q↓↓↓↓←←↓↓↓↓T↓↓->↓↓Z↺↺");
		PlayerString pl = new PlayerString(m);
		
		for (int i=0; i<m.length(); i++) {
			assertEquals(m.charAt(i), pl.nextMove());	
		}
		
	}
}
