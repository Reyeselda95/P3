package model.exceptions.score;

import model.exceptions.TetrisException;

/**
 * Clase RankingException: Devuelve un mensaje de error debido a un error en el Ranking al estar este vacío
 *
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
@SuppressWarnings("serial")
public class RankingException extends TetrisException {

	/**
	 * Constructor por defecto de RankingException
	 */
	public RankingException() {
		super();
	}

	/**
	 * getMessage: Devuelve un mensaje de error
	 * 
	 * @return devuelve el string que describe el error
	 */
	@Override
	public String getMessage() {
		return "Ha habido un error con el Ranking debido a que está vacío.";
	}
}
