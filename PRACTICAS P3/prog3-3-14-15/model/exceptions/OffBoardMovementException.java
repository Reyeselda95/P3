package model.exceptions;

import model.Coordinate;

/**
 * Clase OffBoardMovementException: devuelve un mensaje de error cuando intentas mover una pieza a una posición en la que se sale del tablero
 * 
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class OffBoardMovementException extends MovementException {
	/**
	 * c almacena una cordenada
	 */
	private Coordinate c;

	/**
	 * Constructor por defecto de OffBoardMovementException que pasa a la variable c el valor de la coordenada pasada por parámetro
	 * 
	 * @param cor
	 *            es una coordenada
	 */
	public OffBoardMovementException(Coordinate cor) {
		super();
		c = cor;
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "La pieza no se puede mover a " + c.toString() + " porque parte o la totalidad de la pieza se sale fuera del tablero";
	}

}
