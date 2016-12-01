package model.score;

import model.io.GamePlay;

/**
 * Clase RowsClearedScore: Representa 
 *
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 * correo ara65@alu.ua.es
 *
 */
public class RowsClearedScore extends Score {

	/**
	 * Constructor por defecto de la clase RowsClearedScore
	 * 
	 * @param name
	 *            es el nombre del jugador
	 * @param gameplay
	 *            es el juego actual
	 */
	public RowsClearedScore(String name, GamePlay gameplay) {
		super(name, gameplay);
	}

	/**
	 * getScoring(): Devuelve la cantidad de filas borradas en la partida
	 * 
	 * @return devuelve la cantidad de filas borradas en una partida
	 */
	@Override
	public int getScoring() {
		return gameplay.getRowsCleared();
	}

}
