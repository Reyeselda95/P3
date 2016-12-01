package model.io;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import model.exceptions.io.TetrisIOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GamePlayTestP4 {
	static String splayMain1, splayFinal;
	static IVisualizer ivc; 
	final String stestplay5="T↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T"+
			"←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T↻←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T↺→↓↓↓↓↓↓↓↓↓↓↓↓"+
			"↓↓↓↓↓L↺←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓J↻←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T↺→→→→↓↓↓↓↓↓↓↓"+
			"↓↓↓↓↓↓↓↓↓J↻→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I↺→→→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓";
	final String stestplay6="S←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓S↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓←↓"+
			"L↻↻←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→↓↓↓↓↓↓↓↓↓↓"+
			"↓↓↓↓↓↓↓↓↓I↺→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T↻↻↻↓↓↓↓↓"+
			"↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"Z↺↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I↻↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"↓↓T↻←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓J↺↓↓↓↓↓↓↓↓↓↓↓↓↓↓←↓←↓Z↺↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"↓↓O→→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓S↻↻↻←←←↓↓↓↓↓↓↓↓↓"+
			"↓↓↓↓↓↓↓T↺←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓J↻←↓↓↓↓↓↓↓↓↓↓↓↓↓↓"+
			"↓L↓↓↓↓↓↓↓↓↓↓↓↓↓I↓↓↓↓↓↓↓↓↓↓↓O↓↓↓↓↓↓↓↓↓↓Z↺↓↓↓↓↓↓↓↓L↻↓↓↓↓↓I↺↓J";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		splayMain1="I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓";
		
		ivc=VisualizerFactory.createVisualizer("console");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGamePlay() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓O←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
	
			GamePlay gp = new GamePlay(ip,ivc);
			assertNotNull(gp);
		
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}

	@Test(expected=TetrisIOException.class)
	public void testGamePlayVacio() throws TetrisIOException {
		GamePlay sgp = new GamePlay(PlayerFactory.createPlayer(" "),ivc);
		sgp.play();
	}

	@Test
	public void testPlayRandom() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("54322");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlayRandom.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlayRandom.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlayRandom.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	//String del main
	@Test
	public void testPlay1() {
		try {
			IPlayer ip = PlayerFactory.createPlayer(splayMain1);
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay1.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay1.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay1.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
		
	//Fichero con string del main (resultado igual a testPlay1)
	@Test
	public void testPlay2() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("test/files/P4/testPlay2.in");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay2.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay2.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay1.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
		//PlayerRamdom
	@Test
	public void testPlay3() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("123987");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay3.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay3.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay3.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	@Test
	public void testPlay4() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("test/files/P4/testPlay4.in");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay4.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay4.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay4.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	@Test
	public void testPlay5() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("test/files/P4/testPlay5.in");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay5.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay5.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay5.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	//Misma partida que testPlay5 pero leyéndola de un String
	@Test
	public void testSPlay5() {
		try {
			IPlayer ip = PlayerFactory.createPlayer(stestplay5);
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testSPlay5.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testSPlay5.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay5.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	@Test
	public void testPlay6() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("test/files/P4/testPlay6.in");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay6.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay6.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay6.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	//Misma partida que testPlay6 pero leyéndola de un String
	@Test
	public void testSPlay6() {
		try {
			IPlayer ip = PlayerFactory.createPlayer(stestplay6);
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testSPlay6.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testSPlay6.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay6.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	
	
	//Comprobación que al salirse del tablero ignora la excepcion
	@Test
	public void testPlay7() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("I←←←←←←←");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay7.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay7.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay7.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
			
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	//Comprobación que al mover una pieza fija ignora la excepcion
	@Test
	public void testPlay8() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("I↺→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay8.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay8.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay8.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
				
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	//Comprobación que al colisionar con una pieza fija ignora la excepcion
	@Test
	public void testPlay9() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("I↺→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓L↺↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓→↺↻");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay9.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay9.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay9.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
					
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	//Comprobación que al terminar la partida, ignora el resto de movimientos
	@Test
	public void testPlay10() {
		try {
			IPlayer ip = PlayerFactory.createPlayer("I↺→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓L↺→↓↓↓↓↓↓↓↓↓↓↓↓↓↓O→↓↓↓↓↓↓↓↓↓↓↓↓J↺→↓↓↓↓↓↓↓↓↓I↺→↓↓↓↓↓I↺→↓I↓↓↓↺→←←S");
			GamePlay gp = new GamePlay(ip,ivc);
			standardIO2File("test/files/P4/testPlay10.alu");
			gp.play();
			System.setOut(System.out);
			StringBuilder sbalu = readSolutionFromFile("test/files/P4/testPlay10.alu");
			StringBuilder sbsol = readSolutionFromFile("test/files/P4/testPlay10.sol");
			assertEquals(sbalu.toString(),sbsol.toString());
						
		} catch (TetrisIOException e) {
			fail("Error: excepcion "+e.getClass().getSimpleName()+" "+e.getMessage());
		}
	}
	
	//Se produce excepción TetrisIOException cuando movemos en un tablero vacío
	@Test(expected=TetrisIOException.class)
	public void testPlay11() throws TetrisIOException {
			IPlayer ip = PlayerFactory.createPlayer("↺→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓L↺→↓↓↓↓↓↓↓↓↓↓↓↓↓");
			GamePlay gp = new GamePlay(ip,ivc);
			gp.play();		
	}
	
	//Se produce excepción TetrisIOException cuando ponemos pieza con una pieza no fija en el tablero
	@Test(expected=TetrisIOException.class)
	public void testPlay12() throws TetrisIOException {
		IPlayer ip = PlayerFactory.createPlayer("I↺→↓↓↓↓↓L↺→↓↓↓↓↓↓↓↓↓↓↓↓↓");
		GamePlay gp = new GamePlay(ip,ivc);
		gp.play();
	}
	
	@Test
	public void generaCadena() {
		IPlayer ip=null;
		try {
			ip = PlayerFactory.createPlayer("test/files/P4/testPlay5.in");
		} catch (TetrisIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char c='●';
		String cad ="";
		try {
			c = ip.nextMove();
		} catch (TetrisIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0; c!='●'; i++) {
			cad=cad+c;
			try {
				c=ip.nextMove();
			} catch (TetrisIOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(cad);
	}
	
	/*************************
	 * FUNCIONES AUXILIARES
	 *************************/
	//Redirección de la salida estandard a un fichero	
	public static void standardIO2File(String fileName){

        if(fileName.equals("")){//Si viene vacío usamos este por defecto
            fileName="C:\\javalog.txt";
        }

        try {
            //Creamos un printstream sobre el archivo.
            PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(fileName))),true);
            //Redirigimos salida estandar
            System.setOut(ps);
        } catch (FileNotFoundException ex) {
            System.err.println("Se ha producido una excepción FileNotFoundException");
        }
    }

	//Lee la solución de un fichero y la devuelve en un StringBuilder	
	private StringBuilder readSolutionFromFile(String file) {
		Scanner sc=null;
		try {
				sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		while (sc.hasNext()) 
			sb.append(sc.nextLine()+"\n");			
		sc.close();
		return (sb);
	}
	
	
}
