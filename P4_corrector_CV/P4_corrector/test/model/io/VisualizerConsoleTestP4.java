package model.io;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import model.Coordinate;
import model.Game;
import model.exceptions.WrongSizeException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VisualizerConsoleTestP4 {
	Game game,gamerr1, gamerr2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new Game(new Coordinate(GamePlay.TETRIS_BOARD_STANDARD_HEIGHT,GamePlay.TETRIS_BOARD_STANDARD_WIDTH));
		gamerr1 = new Game(new Coordinate(GamePlay.TETRIS_BOARD_STANDARD_HEIGHT,GamePlay.TETRIS_BOARD_STANDARD_WIDTH-1));
		gamerr2 = new Game(new Coordinate(GamePlay.TETRIS_BOARD_STANDARD_HEIGHT-1,GamePlay.TETRIS_BOARD_STANDARD_WIDTH));
	}

	@Test(expected=NullPointerException.class)
	public void testVisualizerConsoleException1() throws NullPointerException {
		VisualizerConsole vc= new VisualizerConsole();
		vc.show();
	}

	@Test
	public void testSetGame() {
		VisualizerConsole vc= new VisualizerConsole();
		try {
			vc.setGame(game);
		} catch (WrongSizeException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
	}

	@Test(expected=WrongSizeException.class)
	public void testSetGameWrongSizeException1() throws WrongSizeException  {
		VisualizerConsole vc= new VisualizerConsole();
		vc.setGame(gamerr1);
	}
	
	@Test(expected=WrongSizeException.class)
	public void testSetGameWrongSizeException2() throws WrongSizeException  {
		VisualizerConsole vc= new VisualizerConsole();
		vc.setGame(gamerr2);
	}
	
	@Test
	public void testShow() {
		VisualizerConsole vc= new VisualizerConsole();
		try {
			vc.setGame(game);
		} catch (WrongSizeException e) {
			fail("Error: se lanzo la excepcion "+e.getClass().getSimpleName());
		}
		standardIO2File("test/files/P4/testShow.alu");
		vc.show();
		System.setOut(System.out); //Reestablecemos la salida standard
		StringBuilder sb=readSolutionFromFile("test/files/P4/testShow.alu");
		assertEquals(sb.toString(),game.toString());
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
           // System.setErr(ps);
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



