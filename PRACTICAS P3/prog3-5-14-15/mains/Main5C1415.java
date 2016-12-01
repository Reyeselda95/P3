package mains;

import model.exceptions.TetrisException;
import model.exceptions.io.TetrisIOException;
import model.io.GamePlay;
import model.io.IPlayer;
import model.io.IVisualizer;
import model.io.PlayerFactory;
import model.io.VisualizerFactory;
import model.score.Ranking;
import model.score.RowsClearedScore;
import model.score.TimeScore;

public class Main5C1415 {

	public static void main(String[] args) throws TetrisException {
		String output = "window";
		Ranking<TimeScore> rankingTime = new Ranking<>();
		Ranking<RowsClearedScore> rankingRows = new Ranking<>();

		playTheGame("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", output,
				"Sasha", rankingTime, rankingRows);
		playTheGame("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", output,
				"Rachel", rankingTime, rankingRows);
		System.out.println("Time winner: "
				+ rankingTime.getWinner().getName());
		System.out.println("Ranking: "
				+ rankingTime.getSortedRanking().toString());

		System.out.println("\nRows-cleared winner: "
				+ rankingRows.getWinner().getName());
		System.out.println("Ranking: "
				+ rankingRows.getSortedRanking().toString());
	}

	static void playTheGame(String input, String output, String nickname,
			Ranking<TimeScore> rt, Ranking<RowsClearedScore> rr)
			throws TetrisIOException {
		System.out.println("New game!");
		IPlayer player = PlayerFactory.createPlayer(input);
		IVisualizer visualizer = VisualizerFactory.createVisualizer(output);
		GamePlay gamePlay = new GamePlay(player, visualizer);
		gamePlay.play();
		TimeScore ts = new TimeScore(nickname, gamePlay);
		rt.addScore(ts);
		RowsClearedScore rs = new RowsClearedScore(nickname, gamePlay);
		rr.addScore(rs);
	}
}
