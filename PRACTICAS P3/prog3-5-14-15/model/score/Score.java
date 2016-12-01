package model.score;

import java.util.Objects;

import model.io.GamePlay;

/**
 * Clase Score: Representa la puntuación de jugadores
 *
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public abstract class Score implements Comparable<Score> {
	/**
	 * name es el nombre del jugador
	 */
	private String name;
	/**
	 * score es la puntuación que ha obtenido el jugador en cuestión
	 */
	private int score;
	/**
	 * gameplay es el juego actual
	 */
	protected GamePlay gameplay;

	/**
	 * Constructor por defecto de la clase Score
	 * 
	 * @param s
	 *            es un string que se almacena en name
	 * @param game
	 *            es un gameplay que se almacena en gameplay
	 */
	public Score(String s, GamePlay game) {
		s = Objects.requireNonNull(s, "El parámetro 's' no puede ser null");
		game = Objects.requireNonNull(game, "El parámetro 'game' no puede ser null");
		name = s;
		gameplay = game;
		score = getScoring();
	}

	/**
	 * toString(): Devuelve un String indicando un jugador con su puntuación
	 * 
	 * @return devuelve un String con el nombre:puntuación
	 */
	@Override
	public String toString() {
		String s;
		s = name + ":" + score;
		return s;
	}

	/**
	 * getName(): el getter del atributo name
	 * 
	 * @return devuelve name
	 */
	public String getName() {
		return name;
	}

	/**
	 * compareTo(Score other): Compara las puntuaciones de dos jugadores. Si sus puntuaciones son iguales, entonces compara sus nombres
	 * 
	 * @param other
	 *            es un tipo Score a comparar
	 * @return Devuelve -1 si el objeto que llama(this) es mayor que la puntuación del parámetro, 1 si es menor y name.compareTo(other.name) en otro caso
	 */
	@Override
	public int compareTo(Score other) {
		other = Objects.requireNonNull(other, "El parámetro 'other' no puede ser null");
		if (this.score > other.score) {
			return -1;
		}
		else if (this.score < other.score) {
			return 1;
		}
		else {
			return this.name.compareTo(other.name);
		}
	}

	/**
	 * hashCode(): Funcion hashCode
	 * 
	 * @return devuelve un int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}

	/**
	 * Score.equals(Object): Compara dos objetos para comprobar si son el mismo
	 *
	 * @param obj
	 *            es un objeto
	 * @return devuelve true si son iguales y false si no lo son
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Score))
			return false;
		Score other = (Score) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}

	/**
	 * getScoring(): método abstracto que se implementa en RowsClearedScore y en TimeScore
	 * 
	 * @return devuelve la puntuación
	 */
	public abstract int getScoring();
}
