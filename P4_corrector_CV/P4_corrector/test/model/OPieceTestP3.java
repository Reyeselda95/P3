package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OPieceTestP3 {
    Piece p1;
    static ArrayList<Coordinate> coorD0, coorD90, coorD180, coorD270;
    static String sD0, sD90, sD180, sD270;
    static char symbol;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		coorD0 = new ArrayList<Coordinate>();
		coorD90 = new ArrayList<Coordinate>();
		coorD180 = new ArrayList<Coordinate>();
		coorD270 = new ArrayList<Coordinate>();
		symbol = '▣';
		for (int i=0; i < 2; i++) {
			for (int j=1; j<3; j++)	{
				coorD0.add(new Coordinate(i,j));
				coorD270.add(new Coordinate(i,j));
				coorD180.add(new Coordinate(i,j));
				coorD90.add(new Coordinate(i,j));
			}
		}
		
		 sD0 = "·▣▣·\n·▣▣·\n····\n····\n";
		 sD270 = "·▣▣·\n·▣▣·\n····\n····\n";
		 sD180 = "·▣▣·\n·▣▣·\n····\n····\n";
		 sD90 = "·▣▣·\n·▣▣·\n····\n····\n";
	}


	@Before
	public void setUp() throws Exception {
		p1 = PieceFactory.createPiece("O");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	//Test del constructor por defecto de Pieza.
	@Test
	public void testJPiece() {
		assertNotNull("P no es null",p1);
		assertEquals("Orientacion==D0",Rotation.D0, p1.getOrientation());
		assertFalse("fixed == false",p1.isFixed());
		assertEquals("Symbol == "+symbol,symbol,p1.getBlockSymbol());
	}

	//Test  copia de JPieza
	@Test
	public void testCopyJPiece1() {
		Piece p = p1.copy();
		assertNotNull("P no es null",p);
		assertNotSame("p != p1",p1,p);
		assertEquals("Orientacion==D0",Rotation.D0, p.getOrientation());
		assertFalse("fixed == false",p.isFixed());
		assertEquals("Symbol == "+symbol,symbol,p.getBlockSymbol());
		
	}

	//Test copia de una Pieza previamente modificada.
	@Test
	public void testCopyJPiece2() {
		p1.setOrientation(Rotation.D270);
		p1.setFixed(true);
		Piece p = p1.copy();
		assertNotNull("P no es null",p);
		assertNotSame("p != p1",p1,p);
		assertEquals("Orientacion==D270",Rotation.D270, p.getOrientation());
		assertTrue("fixed == false",p.isFixed());
		assertEquals("Symbol == "+symbol,symbol,p.getBlockSymbol());
		
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
