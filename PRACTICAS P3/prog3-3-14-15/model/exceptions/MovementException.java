package model.exceptions;

/**
 * Clase MovementException: Aparece cuando intentas realizar un movimiento inválido
 * 
 * @author ALEJANDRO REYES ALBILLAR
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class MovementException extends TetrisException {
	/**
	 * Constructor por defecto de MovementExcepiton
	 */
	public MovementException() {
		super();
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "El movimiento que has intentado realizar no es válido";
	}

}
