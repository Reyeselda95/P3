package model.exceptions;

import model.Coordinate;

/**
 * Clase WrongSizeException: devuelve un mensaje de error debido a que se ha intentado crear un tablero tan pequeño que no se puede introducir una pieza
 * 
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class WrongSizeException extends TetrisException {
	/**
	 * c almacena una coordenada
	 */
	private Coordinate c;
	/**
	 * Constructor por defecto de WrongSizeException que pasa a la variable c el valor de la coordenada pasada por parámetro
	 * 
	 * @param cor
	 *            es una coordenada
	 */
	public WrongSizeException(Coordinate cor) {
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
		return "El tablero de coordenadas " + c.toString() + " no puede crearse porque es muy pequeño para que pueda entrar en él una pieza";
	}

}
