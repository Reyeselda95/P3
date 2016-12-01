package model.io;

import java.util.Objects;

import model.Coordinate;
import model.Game;
import model.exceptions.WrongSizeException;

/**
 * Clase VisualizerConsole: Representa el visualizador del juego actual por consola
 *
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public class VisualizerConsole implements IVisualizer {
	// La mégde
	/**
	 * game es un juego de clase Game
	 */
	private Game game;

	/**
	 * Constructor por defecto de VisualizerConsole. Pone el atrubuto game a null.
	 */
	VisualizerConsole() {
		game = null;
	}

	/**
	 * setGame(Game game): Inicializa el atributo game con el juego pasado por parametro si no es null y cumple las especificaciones standard del tablero.
	 * 
	 * @param game
	 *            es un juego que ya debe estar inicializado
	 * @throws WrongSizeException
	 *             lanza WrongSizeExeption
	 */
	@Override
	public void setGame(Game game) throws WrongSizeException {
		game = Objects.requireNonNull(game, "El parametro game no puede ser null.");
		if (game.getGameboard().getWidth() != GamePlay.TETRIS_BOARD_STANDARD_WIDTH || game.getGameboard().getHeight() != GamePlay.TETRIS_BOARD_STANDARD_HEIGHT) {
			throw new WrongSizeException(new Coordinate(game.getGameboard().getHeight(), game.getGameboard().getWidth()));
		}
		this.game = game;
	}

	/**
	 * show(): Muestra por pantalla el estado actual de un juego. Tiene la misma funcion que Game.toString().
	 */
	@Override
	public void show() {
		game = Objects.requireNonNull(game, "El objeto game no puede ser null.");
		System.out.println(game.toString());
	}

}
