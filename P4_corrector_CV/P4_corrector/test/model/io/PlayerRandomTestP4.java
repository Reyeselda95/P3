package model.io;

import static org.junit.Assert.*;

import model.exceptions.io.TetrisIOException;

import org.junit.Test;

public class PlayerRandomTestP4 {

	static final String moves1="S↓↓↓↓↻↓↓→↓↓↓↓↓↓↓←↓↓↓↓↓↓↓●↺↻↓↓↺↺↓↓↓↓→↺↓↺→";
	static final String moves2="T↓↓↓↓↓→↓↓↓↓↓→↓↓→→↓↓↺←↓↓↺←↓↓↓→↓●↓←↓↓↺↓↓↓↓";

	
	@Test
	public void testNextMove1() {
		PlayerRandom plr= new PlayerRandom(54322);
		try {
		for (int i=0; i<moves1.length(); i++) {
			char move;
			move = plr.nextMove();
			assertEquals(move,moves1.charAt(i));
			System.out.println(move);
		}
		} catch (TetrisIOException e) {
		fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testNextMove2() {
		PlayerRandom plr= new PlayerRandom(98765);
		try {
		for (int i=0; i<moves2.length(); i++) {
			char move;
			move = plr.nextMove();
			assertEquals(move,moves2.charAt(i));
			System.out.println(move);
		}
		} catch (TetrisIOException e) {
		fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}

}
