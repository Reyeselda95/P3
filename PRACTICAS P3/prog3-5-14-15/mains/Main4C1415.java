package mains;

import model.exceptions.io.TetrisIOException;
import model.io.GamePlay;
import model.io.IPlayer;
import model.io.IVisualizer;
import model.io.PlayerFactory;
import model.io.VisualizerFactory;

public class Main4C1415 {

	public static void main(String[] args) throws TetrisIOException {
		String output = "console";
		
		// playTheGame("2001", output);// Ya funciona
		
		// playTheGame("I↓↓↓↺→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓T↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", output);
		playTheGame("I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "I↺→↻→→→↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓I←←←←↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
				+ "O↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", output);
		 
		// playTheGame("players/player1.txt", output);// Funciona mal, caracteres ocultos
		// playTheGame("", output);// Para jugar Yo
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
