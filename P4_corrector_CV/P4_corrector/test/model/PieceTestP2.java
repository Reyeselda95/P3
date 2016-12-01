package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PieceTestP2 {
    Piece p1;
    static ArrayList<Coordinate> coorD0, coorD90, coorD180, coorD270;
    static String sD0, sD90, sD180, sD270;
    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		coorD0 = new ArrayList<Coordinate>();
		coorD90 = new ArrayList<Coordinate>();
		coorD180 = new ArrayList<Coordinate>();
		coorD270 = new ArrayList<Coordinate>();
		for (int i=0; i < 4; i++) {
			coorD0.add(new Coordinate(1,i));
			coorD270.add(new Coordinate(i,2));
			coorD180.add(new Coordinate(2,i));
			coorD90.add(new Coordinate(i,1));
		}
		 sD0 = "····\n▒▒▒▒\n····\n····\n";
		 sD270 = "··▒·\n··▒·\n··▒·\n··▒·\n";
		 sD180 = "····\n····\n▒▒▒▒\n····\n";
		 sD90 = "·▒··\n·▒··\n·▒··\n·▒··\n";
	}


	@Before
	public void setUp() throws Exception {
		p1 = PieceFactory.createPiece("I");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	//Test del constructor por defecto de Pieza.
	@Test
	public void testPiece() {
		assertNotNull("P no es null",p1);
		assertEquals("Orientacion==D0",Rotation.D0, p1.getOrientation());
		assertFalse("fixed == false",p1.isFixed());
		assertEquals("Symbol == ▒",'▒',p1.getBlockSymbol());
	}

	//Test constructor copia de Pieza
	@Test
	public void testPiecePiece1() {
		Piece p = p1.copy();
		assertNotNull("P no es null",p);
		assertNotSame("p != p1",p1,p);
		assertEquals("Orientacion==D0",Rotation.D0, p.getOrientation());
		assertFalse("fixed == false",p.isFixed());
		assertEquals("Symbol == ▒",'▒',p.getBlockSymbol());
		
	}

	//Test constructor copia de una Pieza previamente modificada.
	@Test
	public void testPiecePiece2() {
		p1.setOrientation(Rotation.D270);
		p1.setFixed(true);
		Piece p = p1.copy();
		assertNotNull("P no es null",p);
		assertNotSame("p != p1",p1,p);
		assertEquals("Orientacion==D270",Rotation.D270, p.getOrientation());
		assertTrue("fixed == false",p.isFixed());
		assertEquals("Symbol == ▒",'▒',p.getBlockSymbol());
		
	}
	
	//Tests setFixed e isFixed
	@Test
	public void testSetIsFixed() {
		p1.setFixed(true);
		assertTrue("Fixed == true",p1.isFixed());
		p1.setFixed(false);
		assertFalse("Fixed == false",p1.isFixed());
	}

	//Test setOrientation y getOrientation
	@Test
	public void testSetGetOrientation() {
		p1.setOrientation(Rotation.D180);
		assertEquals("Orientation == D180",Rotation.D180,p1.getOrientation());
		p1.setOrientation(Rotation.D270);
		assertEquals("Orientation == D270",Rotation.D270,p1.getOrientation());
		p1.setOrientation(Rotation.D90);
		assertEquals("Orientation == D90",Rotation.D90,p1.getOrientation());
		p1.setOrientation(Rotation.D0);
		assertEquals("Orientation == D0",Rotation.D0,p1.getOrientation());
	}

	//Test rotateClockwise
	@Test
	public void testRotateClockwise() {
		p1.rotateClockwise();
		assertEquals("Orientation == D270", Rotation.D270, p1.getOrientation());
		p1.rotateClockwise();
		assertEquals("Orientation == D180", Rotation.D180, p1.getOrientation());
		p1.rotateClockwise();
		assertEquals("Orientation == D90", Rotation.D90, p1.getOrientation());
		p1.rotateClockwise();
		assertEquals("Orientation == D0", Rotation.D0, p1.getOrientation());
	}
	
	//Test rotateCounterclockwise
	@Test
	public void testRotateCounterclockwise() {
		p1.rotateCounterclockwise();
		assertEquals("Orientation == D90", Rotation.D90, p1.getOrientation());
		p1.rotateCounterclockwise();
		assertEquals("Orientation == D180", Rotation.D180, p1.getOrientation());
		p1.rotateCounterclockwise();
		assertEquals("Orientation == D270", Rotation.D270, p1.getOrientation());
		p1.rotateCounterclockwise();
		assertEquals("Orientation == D0", Rotation.D0, p1.getOrientation());
	}

	//Test getAbsoluteCells para D0
	@Test
	public void testGetAbsoluteCellsD0() {
		Coordinate c1 = new Coordinate(13,27);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas D0+c1", cells.contains(coorD0.get(i).add(c1)));
		}
		
	}

	//Test getAbsoluteCells para D90
	@Test
	public void testGetAbsoluteCellsD90() {
		Coordinate c1 = new Coordinate(0,0);
		p1.setOrientation(Rotation.D90);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas D90+c1", cells.contains(coorD90.get(i).add(c1)));
		}
		
	}
	
	//Test getAbsoluteCells para D180
	@Test
	public void testGetAbsoluteCellsD180() {
		Coordinate c1 = new Coordinate(300,700);
		p1.setOrientation(Rotation.D180);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas D180+c1", cells.contains(coorD180.get(i).add(c1)));
		}
		
	}
	
	//Test getAbsoluteCells para D270
	@Test
	public void testGetAbsoluteCellsD270() {
		Coordinate c1 = new Coordinate(11,11);
		p1.setOrientation(Rotation.D270);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas 270+c1", cells.contains(coorD270.get(i).add(c1)));
		}
		
	}
	
	//Test toString para Todas rotaciones
	@Test
	public void testToStringRotations() {
		
		assertEquals("D0 toString",sD0,p1.toString());
		p1.rotateClockwise();
		assertEquals("D270 toString",sD270,p1.toString());		
		p1.rotateClockwise();
		assertEquals("D180 toString",sD180,p1.toString());
		p1.rotateClockwise();
		assertEquals("D90 toString",sD90,p1.toString());
		
	}
}
