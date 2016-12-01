package model.score;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import model.exceptions.score.RankingException;

// TODO: Auto-generated Javadoc
/**
 * Clase Ranking: Representa el ranking del Tetris.
 *
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 * @param <ScoreType>
 *            es un conjunto de tipo score
 */
public class Ranking<ScoreType extends Score> {

	/** gamePlays almacena un conjunto de jugadores y puntuaciones de tipo score. */
	private SortedSet<ScoreType> gamePlays;

	/**
	 * Constructor por defecto de la clase Ranking
	 * inicializa gamePlays como TreeSet<>.
	 */
	public Ranking() {
		gamePlays = new TreeSet<>();
	}

	/**
	 * addScore(): añade un score al TreeSet si no existe ya en él y reordena el conjunto.
	 *
	 * @param s
	 *            es un ScoreType
	 */
	public void addScore(ScoreType s) {
		s = Objects.requireNonNull(s, "El parámetro 's' no puede ser null");
		gamePlays.add(s);
	}

	/**
	 * getWinner():getter.
	 *
	 * @return devuelve el primer resultado (el más bajo) de gamePlays
	 * @throws RankingException
	 *             lanza RankingException
	 */
	public ScoreType getWinner() throws RankingException {
		if (gamePlays.isEmpty()) {
			throw new RankingException();
		}
		else {
			return gamePlays.first();
		}
	}

	/**
	 * getSortedRanking(): es un getter de gamePlays.
	 *
	 * @return devuelve los gamePlays
	 */
	public SortedSet<ScoreType> getSortedRanking() {
		return gamePlays;
	}

}
