package model.io;

import model.Game;
import model.exceptions.WrongSizeException;

/**
 * Clase IVisualizer: Representa el visualizador del juego.
 *
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 */
public interface IVisualizer {
	// La mégde
	/**
	 * Constructor por defecto de IVisualizer que crea un nuevo juego.
	 *
	 * @param game
	 *            es un objeto de clase game
	 * @throws WrongSizeException
	 *             lanza WrongSizeException
	 */
	public void setGame(Game game) throws WrongSizeException;

	/**
	 * Muestra el estado actual del tablero
	 */
	public void show();

}
