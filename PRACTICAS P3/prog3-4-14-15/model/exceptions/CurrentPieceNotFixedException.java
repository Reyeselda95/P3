package model.exceptions;

/**
 * Clase CurrentPieceNotFixedException:Devuelve un mensaje que indica que la pieza actual no está fija
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class CurrentPieceNotFixedException extends TetrisException {
	/**
	 * Constructor por defecto de CurrentPieceNotFixedException
	 */
	public CurrentPieceNotFixedException() {
		super();
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "La pieza actual no está fija y has intentado colocar una nueva pieza en el tablero.";
	}
}
