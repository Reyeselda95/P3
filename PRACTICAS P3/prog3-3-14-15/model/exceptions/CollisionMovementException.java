package model.exceptions;

import model.Coordinate;

/**
 * Clase: CollisionMovementException: Devuelve un mensaje de error cuando una pieza colisiona con otra al intentar moverse hacia algún lugar
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class CollisionMovementException extends MovementException {
	/**
	 * c almacena una coordenada
	 */
	private Coordinate c;

	/**
	 * Constructor por defecto de CollisionMovementException que da a la variable c el valor de la coordenada pasada por parámetro
	 * 
	 * @param cor
	 *            cor se pasa una coordenada por parámetro
	 */
	public CollisionMovementException(Coordinate cor) {
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
		return "La pieza actual no se puede mover a la coordenada " + c.toString() + " ya que colisiona con una pieza.";
	}

}
