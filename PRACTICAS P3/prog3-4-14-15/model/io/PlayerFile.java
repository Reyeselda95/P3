package model.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.exceptions.io.TetrisIOException;

/**
 * Clase PlayerFile: Represeta los movimientos de un jugador dados desde fichero.
 *
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 */
public class PlayerFile implements IPlayer {
	/** br almacena. */
	private BufferedReader br;
	
	/**
	 * PlayerFile(BufferedReader): Copia en br el valor que se le pasa por parametro.
	 *
	 * @param br1
	 *            es un atributo de clase BufferedReader
	 */
	PlayerFile(BufferedReader br1) {
		br1 = Objects.requireNonNull(br1, "El parametro br1 no puede ser null.");
		br = br1;
	}

	/*
	 * /**
	 * Limpiar(String s): Limpia la cadena de posibles caracteres ocultos.
	 * 
	 * @param s
	 * es el string a limpiar
	 * 
	 * @return devuelve un string
	 *//*
		 * private String Limpiar(String s) {
		 * int i = 0;
		 * String salida = new String("");
		 * for (i = 0; i < s.length(); i++) {
		 * if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
		 * salida += s.charAt(i);
		 * }
		 * else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
		 * salida += s.charAt(i);
		 * }
		 * }
		 * return salida;
		 * }
		 */

	/**
	 * nextMove():Nos devuelve un carácter que represente un movimiento.
	 *
	 * @return Nos devuelve un carácter que represente un movimiento
	 * @throws TetrisIOException
	 *             lanza TetrisIOException
	 */
	@Override
	public char nextMove() throws TetrisIOException {
		String[] palabras;
		String linea = "";
		try {
			linea = br.readLine();
		} catch (IOException e) {
			throw new TetrisIOException("No se ha podido leer correctamente la linea.");
		}
		if (linea == null) {
			return LAST_MOVE;
		}
		palabras = linea.trim().split("\\s+");
		if (palabras.length > 2) {// Si hay más de 2 palabras por linea
			throw new TetrisIOException("La linea tenía más palabras de las que debería.");
		}
		switch (palabras[0]) {
			case "put":
				switch (palabras[1]) {
					case "I":
						return IPiece;
					case "J":
						return JPiece;
					case "L":
						return LPiece;
					case "O":
						return OPiece;
					case "S":
						return SPiece;
					case "T":
						return TPiece;
					case "Z":
						return ZPiece;
					default:
						throw new TetrisIOException("Se ha leido una linea incorrecta del fichero.");
				}
			case "move":
				switch (palabras[1]) {
					case "left":
						return MoveLeft;
					case "right":
						return MoveRight;
					case "down":
						return MoveDown;
					default:
						throw new TetrisIOException("Se ha leido una linea incorrecta del fichero.");
				}
			case "rotate":
				switch (palabras[1]) {
					case "clockwise":
						return RotateClockwise;
					case "counterclockwise":
						return RotateCounterclockwise;
					default:
						throw new TetrisIOException("Se ha leido una linea incorrecta del fichero.");
				}
			default:
				throw new TetrisIOException("Se ha leido una linea incorrecta del fichero.");
		}

	}

}
