package model.score;

import model.io.GamePlay;

/**
 * Clase TimeScore: Representa la puntuación por tiempo de juego
 *
 * @author ALEJANDRO REYES ALBILLAR 
 * correo ara65@alu.ua.es
 *
 */
public class TimeScore extends Score{

	/**
	 * Constructor por defecto de la clase TimeScore.
	 *
	 * @param name
	 *            es el nombre del jugador
	 * @param gameplay
	 *            es el juego actual
	 */
	public TimeScore(String name, GamePlay gameplay) {
		super(name, gameplay);
	}

	/**
	 * getScoring(): Devuelve la duración de la partida.
	 *
	 * @return devuelve la duración de una partida tal que sea el valor negado de la duración de la partida
	 */
	@Override
	public int getScoring() {
		return -gameplay.getDuration();
	}

}
