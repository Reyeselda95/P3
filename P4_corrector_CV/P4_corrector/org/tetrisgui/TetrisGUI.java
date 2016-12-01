package org.tetrisgui;

/* This is a reduced version of the Tetris code by Brendan Jones available
 * at https://github.com/PSNB92/Tetris. It has been modified to keep the functionality
 * of drawing Tetris boards, but the rest of features have mostly been removed. 
 */

/* The original code is commented here: 
 * http://psnbtech.blogspot.com.es/2012/10/tutorial-java-tetris-game.html
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * The {@code Tetris} class is responsible for handling much of the game logic and
 * reading user input.
 * @author Brendan Jones
 *
 */
@SuppressWarnings("serial")
public class TetrisGUI extends JFrame {
	
	/**
	 * The BoardPanel instance.
	 */
	public BoardPanel board;
	
	/**
	 * Creates a new Tetris instance. Sets up the window's properties,
	 * and adds a controller listener.
	 */
	public TetrisGUI() {
		/*
		 * Set the basic properties of the window.
		 */
		super("Tetris");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		/*
		 * Initialize the BoardPanel and SidePanel instances.
		 */
		this.board = new BoardPanel();
		add(board, BorderLayout.CENTER);

		/*
		 * Here we resize the frame to hold the BoardPanel and SidePanel instances,
		 * center the window on the screen, and show it to the user.
		 */
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		resetGame();
	}
		
	/**
	 * Forces the BoardPanel and SidePanel to repaint.
	 */
	public void renderGame() {
		board.repaint();
	}
	
	/**
	 * Resets the game variables to their default values at the start
	 * of a new game.
	 */
	public void resetGame() {
		board.clear();
	}

	/**
	 * Getter
	 * @return board attribute
	 */
	public BoardPanel getBoardPanel () {
		return board;
	}
		
	/**
	 * Entry-point of the game. Responsible for creating and starting a new
	 * game instance.
	 * @param args Unused.
	 */
	//public static void main(String[] args) {
	//	TetrisGUI tetris = new TetrisGUI();
	//	tetris.resetGame();
	//	tetris.getBoardPanel().addPieces();
	//	tetris.renderGame();
	//}

}
