package model.io;

import static org.junit.Assert.*;

import model.exceptions.io.TetrisIOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerFactoryTestP4 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testCreatePlayerFile1() {
		
		try {
			IPlayer ip=PlayerFactory.createPlayer("test/files/P4/testCreatePlayerFile1.in");
			assertEquals("PlayerFile",ip.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testCreatePlayerFile2() {
		
		try {
			IPlayer ip=PlayerFactory.createPlayer("12345/54321");
			assertEquals("PlayerFile",ip.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testCreatePlayerFile3() {
		
		try {
			IPlayer ip=PlayerFactory.createPlayer("IJL/OSTZ");
			assertEquals("PlayerFile",ip.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}

	@Test
	public void testCreatePlayerRandom1() {
		
		try {
			IPlayer ip=PlayerFactory.createPlayer("67584902");
			assertEquals("PlayerRandom",ip.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testCreatePlayerRandom2() {
		
		try {
			IPlayer ip=PlayerFactory.createPlayer("-567890");
			assertEquals("PlayerRandom",ip.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testCreatePlayerString() {
		
		try {
			IPlayer ip=PlayerFactory.createPlayer("IJLOSTZ");
			assertEquals("PlayerString",ip.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test
	public void testCreatePlayerString2() {
		
		try {
			IPlayer ip=PlayerFactory.createPlayer(" ");
			assertEquals("PlayerString",ip.getClass().getSimpleName());
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}
	
	@Test(expected=TetrisIOException.class)
	public void testCreatePlayerTetrisIOException() throws TetrisIOException {
		
			IPlayer ip=PlayerFactory.createPlayer("/noexistefichero.in");
			assertEquals("PlayerString",ip.getClass().getSimpleName());
	}
		
}
