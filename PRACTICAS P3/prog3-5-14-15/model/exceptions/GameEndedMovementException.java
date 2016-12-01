package model.exceptions;

/**
 * Clase GameEndedMovementException: Devuelve un mensaje de error porque se ha intentado mover o añadir una pieza cuando el juego ya había finalizado
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class GameEndedMovementException extends MovementException {
	/**
	 * Constructor por defecto de GameEndedMovementException
	 */
	public GameEndedMovementException() {
		super();
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "El juego ha terminado y has intentado mover o añadir una pieza.";
	}
}
