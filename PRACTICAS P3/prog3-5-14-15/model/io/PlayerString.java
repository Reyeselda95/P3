package model.io;

import java.util.Objects;

import model.exceptions.io.TetrisIOException;

/**
 * Clase PlayerString(): Representa una linea en la que se almacenan caracteres de movimientos
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public class PlayerString implements IPlayer {
	/**
	 * moves es un string que almacena un conjunto de movimientos en forma de caracteres
	 */
	private String moves;
	/**
	 * currentMove almacena el movimiento en el que nos encontramos
	 */
	private int currentMove = 0;

	/**
	 * PlayerString(String s): Copia el string pasado por parámetro en la variable moves. Es un modulo de paquete.
	 * 
	 * @param s
	 *            es un sting
	 */
	PlayerString(String s) {
		s = Objects.requireNonNull(s, "El objeto s no puede ser null.");
		moves = s;
	}

	/**
	 * nextMove():Nos devuelve un carácter que represente un movimiento
	 * 
	 * @return Nos devuelve un carácter que represente un movimiento
	 * @throws TetrisIOException
	 *             lanza TetrisIOException
	 */
	@Override
	public char nextMove() throws TetrisIOException {
		if (currentMove >= moves.length()) {
			return LAST_MOVE;
		}
		char car = moves.charAt(currentMove);
		currentMove++;
		switch (car)
		{
			case IPiece:
				return IPiece;
			case JPiece:
				return JPiece;
			case LPiece:
				return LPiece;
			case SPiece:
				return SPiece;
			case ZPiece:
				return ZPiece;
			case TPiece:
				return TPiece;
			case OPiece:
				return OPiece;
			case RotateClockwise:
				return RotateClockwise;
			case RotateCounterclockwise:
				return RotateCounterclockwise;
			case MoveRight:
				return MoveRight;
			case MoveLeft:
				return MoveLeft;
			case MoveDown:
				return MoveDown;
			default:
				throw new TetrisIOException("El caracter encontrado no es un movimiento valido.");
		}
	}

}
