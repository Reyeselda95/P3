package model.io;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.exceptions.io.TetrisIOException;

import org.junit.Before;
import org.junit.Test;

public class PlayerFileTestP4 {
	BufferedReader bin;
	PlayerFile pf;
	String m2, m1; 
	@Before
	public void setUp() throws Exception {
		m1=new String("I↻↻J↺L→→→→O←S↓↓↓↓←←↓↓↓↓T↓↓→↓↓Z↺↺");
		m2=new String("I↻J↺L→O←S↓T↓Z");


	}
	
	@Test
	public void testNextMove() {
		NewPlayerFile("test/files/P4/testNextMove.in");
	
		try {
			for (int i=0; i<m1.length(); i++) {
				assertEquals(m1.charAt(i),pf.nextMove());
			}
			assertEquals(IPlayer.LAST_MOVE,pf.nextMove());
		} catch (TetrisIOException e) {
			fail ("Error, se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testNextMoveConEspacios() {
		NewPlayerFile("test/files/P4/testNextMoveConEspacios.in");
	
		try {
			for (int i=0; i<m1.length(); i++) {
				assertEquals(m1.charAt(i),pf.nextMove());
			}
			assertEquals(IPlayer.LAST_MOVE,pf.nextMove());
		} catch (TetrisIOException e) {
			fail ("Error, se lanzó la excepción "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testNextMoveIOException1() {
		NewPlayerFile("test/files/P4/testNextMoveIOException1.in");
		char c=' ';
		try {
			for (int i=0; i<m2.length(); i++) {
				c =pf.nextMove();
				assertEquals(m2.charAt(i),c);
			}
			fail ("Error. No se lanzó la excepción TetrisIOException");
		} catch (TetrisIOException e) {
			assertEquals('↓',c);
		}
	
	}
	
	@Test
	public void testNextMoveIOException2() {
		NewPlayerFile("test/files/P4/testNextMoveIOException2.in");
		char c=' ';
		try {
			for (int i=0; i<m2.length(); i++) {
				c =pf.nextMove();
				assertEquals(m2.charAt(i),c);
			}
			fail ("Error. No se lanzó la excepción TetrisIOException");
		} catch (TetrisIOException e) {
			assertEquals('S',c);
		}
	}
	
	@Test
	public void testNextMoveIOException3() {
		NewPlayerFile("test/files/P4/testNextMoveIOException3.in");
		char c=' ';
		try {
			for (int i=0; i<m2.length(); i++) {
				c =pf.nextMove();
				assertEquals(m2.charAt(i),c);
			}
			fail ("Error. No se lanzó la excepción TetrisIOException");
		} catch (TetrisIOException e) {
			assertEquals('T',c);
		}
	}
	
	@Test
	public void testNextMoveIOException4() {
		NewPlayerFile("test/files/P4/testNextMoveIOException4.in");
		char c=' ';
		try {
			for (int i=0; i<m2.length(); i++) {
				c =pf.nextMove();
				assertEquals(m2.charAt(i),c);
			}
			fail ("Error. No se lanzó la excepción TetrisIOException");
		} catch (TetrisIOException e) {
			assertEquals('O',c);
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testNextMoveIOException5() {
		
		pf=new PlayerFile(null);
	
	}
	
	
//Funciones de apoyo
	private void NewPlayerFile(String name) {
	
		try {
			bin = new BufferedReader(new FileReader(name));
			pf=new PlayerFile(bin);
		} catch (FileNotFoundException e) {
			fail("Error apertura, fichero "+name);
		}
	}

}