package model.exceptions;

/**
 * Clase FixedPieceMovementException: Devuelve un error cuando intentamos mover una pieza fija
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class FixedPieceMovementException extends MovementException {

	/**
	 * Constructor por defecto para FixedPieceMovementException
	 */
	public FixedPieceMovementException() {
		super();
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "Has intentado mover una pieza que está fija.";
	}
}
