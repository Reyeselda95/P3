package model.exceptions;

/**
 * Clase NoCurrentPieceException: devuelve un mensaje de error indicando que, como el juego no ha comenzado, no hay piezas en el tablero
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class NoCurrentPieceException extends TetrisException {
	/**
	 * Constructor por defecto de NoCurrentPieceException
	 */
	public NoCurrentPieceException() {
		super();
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "La partida no ha empezado por lo que no hay piezas en el tablero.";
	}

}
