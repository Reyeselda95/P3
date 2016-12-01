package mains;

import model.exceptions.io.TetrisIOException;
import model.io.GamePlay;
import model.io.IVisualizer;
import model.io.IPlayer;
import model.io.VisualizerFactory;
import model.io.PlayerFactory;

public class Main4C1415 {

	public static void main(String[] args) throws TetrisIOException {
		String output= "console";
		
		playTheGame("2001", output);
		playTheGame("I↓↓↓↺→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", output);
		playTheGame("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", output);
		playTheGame("players/player1.txt", output);
	}

	static void playTheGame(String input, String output)
			throws TetrisIOException {
		System.out.println("New game!");
		IPlayer player = PlayerFactory.createPlayer(input);
		IVisualizer visualizer = VisualizerFactory.createVisualizer(output);
		GamePlay gamePlay = new GamePlay(player, visualizer);
		gamePlay.play();
	}
}
