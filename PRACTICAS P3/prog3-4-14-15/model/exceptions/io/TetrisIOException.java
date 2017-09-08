package model.exceptions.io;

import java.util.Objects;

import model.exceptions.TetrisException;

/**
 * 
 * Clase TetrisIOException: Representa Una excoción que lanzan las interfaces
 *
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class TetrisIOException extends TetrisException {
	/**
	 * message almacena el mensaje que se pasa a la excepcion
	 */
	private String message;

	/**
	 * Constructor por defecto de TetrisIOException que lanza el mensaje que le pasan por parámetro almacenandolo en message
	 * 
	 * @param s
	 *            es un String
	 */
	public TetrisIOException(String s) {
		s = Objects.requireNonNull(s, "El parametro s no puede ser null.");
		message = s;
	}

	/**
	 * getMessage():Devuelve el String almacenado en message
	 * 
	 * @return devuelve el valor de message
	 */
	@Override
	public String getMessage() {
		return message;
	}

}
