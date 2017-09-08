package model.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

import model.exceptions.io.TetrisIOException;

/**
 * Clase PlayerFactory: Es un método factory que crea objetos de las diferentes clases que implementan IPlayer.
 *
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 */
public class PlayerFactory {
	/**
	 * isLong(String s): Comprueba si el string pasado por parametro contiene un numero válido o no.
	 *
	 * @param s
	 *            es un string que puede contener o no un entero long
	 * @return true si lo que contiene el string es long y false si no
	 */
	private static boolean isLong(String s) {
		s = Objects.requireNonNull(s, "El parámetro s no puede ser null.");
		int i = 0;
		if (s.charAt(0) == '-' || s.charAt(0) == '+') {
			if (s.length() > 1) {
				i++;
			}
			else {
				return false;
			}
		}
		for (; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Constructor por defecto de la clase.
	 */
	public PlayerFactory() {
	}

	/**
	 * createPlayer(String s): crea un nuevo jugador según el argumento pasado por el string del parámetro.
	 *
	 * @param s
	 *            es un string que se utilizará para saber que tipo de Player crear
	 * @return devuelve un IPlayer diferente según el string pasado por parametro
	 * @throws TetrisIOException
	 *             lanza TetrisIOException
	 */
	public static IPlayer createPlayer(String s) throws TetrisIOException{
		s=Objects.requireNonNull(s, "El parametro s no puede ser null.");
		if(s.contains("s")|| s.contains("/") || s.contains("\\")) {
			BufferedReader br1;
			try {
				br1 = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException fnfe) {
				throw new TetrisIOException("No se ha podido crear el objeto de clase BufferedReader");
			}
			return new PlayerFile(br1);
		}

		/*
		 * else if (s == "") {
		 * return new PlayerKeyboard();// Para jugar Yo
		 * }
		 */

		else if (isLong(s)) {
			try {
				Long.parseLong(s);
			} catch (Exception e) {
				throw new TetrisIOException("No se ha posido crear un numero entero largo desde el parametro s.");
			}
			return new PlayerRandom(Long.parseLong(s));
		}
		else {
			return new PlayerString(s);
		}
	}

}
