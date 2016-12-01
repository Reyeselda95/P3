package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PieceFactoryTestP3 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateIPiece() {
		IPiece ip = (IPiece) PieceFactory.createPiece("I");
		assertNotNull(ip);
	}
	
	@Test
	public void testCreateJPiece() {
		JPiece jp = (JPiece) PieceFactory.createPiece("J");
		assertNotNull(jp);
	}
	
	@Test
	public void testCreateLPiece() {
		LPiece lp = (LPiece) PieceFactory.createPiece("L");
		assertNotNull(lp);
	}
	
	@Test
	public void testCreateOPiece() {
		OPiece op = (OPiece) PieceFactory.createPiece("O");
		assertNotNull(op);
	}
	
	@Test
	public void testCreateSPiece() {
		SPiece sp = (SPiece) PieceFactory.createPiece("S");
		assertNotNull(sp);
	}
	
	@Test
	public void testCreateTPiece() {
		TPiece tp = (TPiece) PieceFactory.createPiece("T");
		assertNotNull(tp);
	}
	
	@Test
	public void testCreateZPiece() {
		ZPiece zp = (ZPiece) PieceFactory.createPiece("Z");
		assertNotNull(zp);
	}
	
	@Test
	public void testCreatePieceNull() {
		assertNull(PieceFactory.createPiece("Q"));
	}
}
