package model.exceptions;

/**
 * Clase TetrisException: Aparece cuando se da un error en el tetris
 * 
 * @author ALEJANDRO REYES ALBILLAR
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class TetrisException extends Exception {
	/**
	 * Constructor por defecto de TetrisException
	 */
	public TetrisException() {
		super();
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "Hay un error en tetris.";
	}
}

