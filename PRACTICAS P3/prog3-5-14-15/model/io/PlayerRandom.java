package model.io;

import java.util.Objects;
import java.util.Random;

import model.exceptions.io.TetrisIOException;

/**
 * Clase PlayerRandom: Representa a un jugador aleatorio.
 *
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 */
public class PlayerRandom implements IPlayer{
	/**
	 * random es un atributo de instancia que se inicializa con una semilla
	 */
	private Random random;
	/**
	 * nextPutPiece indica si en el siguiente movimiento ha de colocarse o no una nueva pieza en el tablero
	 */
	private boolean nextPutPiece = true;
	/**
	 * downCounter cuenta el número de veces que se mueve una pieza hacia abajo
	 */
	private int downCounter = 0;
	/**
	 * PlayerRandom(long num): Inicializa el atributo de instancia random con la semilla num
	 * 
	 * @param num
	 *            es un numero long que sirve para la semilla
	 */
	PlayerRandom(long num) {
		num = Objects.requireNonNull(num, "El parámetro num no puede ser null.");
		random = new Random(num);
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
		int nextAleatPiece;
		int nextAleatMove;
		if (downCounter == GamePlay.TETRIS_BOARD_STANDARD_HEIGHT) {
			nextPutPiece = true;
			downCounter = 0;
		}
		if (nextPutPiece) {
			nextAleatPiece = random.nextInt(8);
			switch (nextAleatPiece) {
				case 0:
					nextPutPiece = false;
					return IPiece;
				case 1:
					nextPutPiece = false;
					return JPiece;
				case 2:
					nextPutPiece = false;
					return LPiece;
				case 3:
					nextPutPiece = false;
					return OPiece;
				case 4:
					nextPutPiece = false;
					return SPiece;
				case 5:
					nextPutPiece = false;
					return TPiece;
				case 6:
					nextPutPiece = false;
					return ZPiece;
				default:
					nextPutPiece = false;
					return LAST_MOVE;
			}
		}
		else {
			nextAleatMove = random.nextInt(10);
			switch (nextAleatMove) {
				case 0:
					return MoveLeft;
				case 1:
					return MoveRight;
				case 2:
					return RotateClockwise;
				case 3:
					return RotateCounterclockwise;
				default:
					downCounter++;
					return MoveDown;
			}
		}
	}

}
