package model.io;

import model.exceptions.io.TetrisIOException;

/**
 * Interfaz IPlayer: Representa a un jugador
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public interface IPlayer {
	/**
	 * LAST_MOVE es una constante con valor '●' que se devuelve si ya se han devuelto todos los movimientos disponibles
	 */
	public static final char LAST_MOVE = '●';
	/**
	 * representa la acción de introducir una nueva pieza en el juego con la forma indicada
	 */
	public static final char IPiece = 'I';
	/**
	 * representa la acción de introducir una nueva pieza en el juego con la forma indicada
	 */
	public static final char JPiece = 'J';
	/**
	 * representa la acción de introducir una nueva pieza en el juego con la forma indicada
	 */
	public static final char LPiece = 'L';
	/**
	 * representa la acción de introducir una nueva pieza en el juego con la forma indicada
	 */
	public static final char OPiece = 'O';
	/**
	 * representa la acción de introducir una nueva pieza en el juego con la forma indicada
	 */
	public static final char SPiece = 'S';
	/**
	 * representa la acción de introducir una nueva pieza en el juego con la forma indicada
	 */
	public static final char TPiece = 'T';
	/**
	 * representa la acción de introducir una nueva pieza en el juego con la forma indicada
	 */
	public static final char ZPiece = 'Z';
	/**
	 * (carácter Unicode U+21BB): rotación de la pieza actual en el sentido de las agujas del reloj
	 */
	public static final char RotateClockwise = '↻';
	/**
	 * (carácter Unicode U+21BA): rotación de la pieza actual en el sentido contrario a las agujas del reloj.
	 */
	public static final char RotateCounterclockwise = '↺';
	/**
	 * (carácter Unicode U+2192): movimiento a la derecha de la pieza actual.
	 */
	public static final char MoveRight = '→';
	/**
	 * (carácter Unicode U+2190): movimiento a la izquierda de la pieza actual.
	 */
	public static final char MoveLeft = '←';
	/**
	 * (carácter Unicode U+2193): movimiento hacia abajo de la pieza actual.
	 */
	public static final char MoveDown = '↓';

	/**
	 * nextMove(): se implementa en IPlayer, PlayerFile y PlayerRandom
	 * 
	 * @return Nos devuelve un carácter que represente un movimiento
	 * @throws TetrisIOException
	 *             lanza TetrisIOException
	 */
	public char nextMove() throws TetrisIOException;
}
