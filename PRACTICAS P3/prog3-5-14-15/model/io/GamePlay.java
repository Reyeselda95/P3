package model.io;

import java.util.Date;
import java.util.Objects;

import model.Coordinate;
import model.Game;
import model.exceptions.CollisionMovementException;
import model.exceptions.CurrentPieceNotFixedException;
import model.exceptions.FixedPieceMovementException;
import model.exceptions.GameEndedMovementException;
import model.exceptions.NoCurrentPieceException;
import model.exceptions.OffBoardMovementException;
import model.exceptions.WrongSizeException;
import model.exceptions.io.TetrisIOException;

/**
 * Clase GamePlay: Representa un nuevo juego
 *
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 */
public class GamePlay {
	/**
	 * TETRIS_BOARD_STANDARD_WIDTH indica la anchura standard del tablero de juego.
	 */
	static final int TETRIS_BOARD_STANDARD_WIDTH = 10;

	/**
	 * TETRIS_BOARD_STANDARD_HEIGHT indica la altura standard del tablero de juego.
	 */
	static final int TETRIS_BOARD_STANDARD_HEIGHT = 20;

	/**
	 * rowsCleared es el número de filas limpiadas en el juego
	 */
	int rowsCleared = 0;

	/**
	 * duration es la duración del juego
	 */
	int duration = 0;

	/**
	 * game es un nuevo.
	 */
	private Game game;

	/**
	 * player es un nuevo jugador.
	 */
	IPlayer player;

	/**
	 * visualizer es un nuevo visualizador.
	 */
	IVisualizer visualizer;
	

	/**
	 * GamePlay(IPlayer, IVisualizer):Representa un nuevo juego.
	 *
	 * @param player
	 *            es el jugador
	 * @param visualizer
	 *            es el visualizador
	 */
	public GamePlay(IPlayer player, IVisualizer visualizer) {
		player = Objects.requireNonNull(player, "El parametro player no puede ser null.");
		visualizer = Objects.requireNonNull(visualizer, "El parametro visualizer no puede ser null.");
		try {
			this.player = player;
			this.visualizer = visualizer;
			game = new Game(new Coordinate(TETRIS_BOARD_STANDARD_HEIGHT, TETRIS_BOARD_STANDARD_WIDTH));
			visualizer.setGame(game);
		} catch (WrongSizeException e) {
			throw new java.lang.RuntimeException();
		}
	}

	/**
	 * getRowsCleared():getter
	 * 
	 * @return las líneas limpiadas
	 */
	public int getRowsCleared() {
		return rowsCleared;
	}

	/**
	 * getDuration():getter
	 * 
	 * @return la duración del juego
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * play():Juega al tetris
	 * 
	 * @throws TetrisIOException
	 *             Lanza TetrisIOException
	 */
	public void play() throws TetrisIOException {
		long t0 = new Date().getTime();
		visualizer.show();
		char move;
		move = player.nextMove();
		while (move != IPlayer.LAST_MOVE) {
			try {
				switch (move) {
					case IPlayer.IPiece:
						game.nextPiece("I");
						break;
					case IPlayer.JPiece:
						game.nextPiece("J");
						break;
					case IPlayer.LPiece:
						game.nextPiece("L");
						break;
					case IPlayer.OPiece:
						game.nextPiece("O");
						break;
					case IPlayer.SPiece:
						game.nextPiece("S");
						break;
					case IPlayer.TPiece:
						game.nextPiece("T");
						break;
					case IPlayer.ZPiece:
						game.nextPiece("Z");
						break;
					case IPlayer.MoveLeft:
						game.moveCurrentPieceLeft();
						break;
					case IPlayer.MoveRight:
						game.moveCurrentPieceRight();
						break;
					case IPlayer.MoveDown:
						rowsCleared += game.moveCurrentPieceDown();
						break;
					case IPlayer.RotateClockwise:
						game.rotateCurrentPieceClockwise();
						break;
					case IPlayer.RotateCounterclockwise:
						game.rotateCurrentPieceCounterclockwise();
						break;
				}

			} catch (OffBoardMovementException obme) {
			}// Pasa de largo
			catch (CollisionMovementException cme) {
			}// Pasa de largo
			catch (FixedPieceMovementException fpme) {
			}// Pasa de largo
			catch (GameEndedMovementException geme) {
				break;
			}
			catch (NoCurrentPieceException ncpe) {// Lanza TetrisIOException
				throw new TetrisIOException("No hay pieza en el tablero y has intentado mover una.");
			} catch (CurrentPieceNotFixedException cpnfe) {// Lanza TetrisIOException
				throw new TetrisIOException("La pieza que hay en el tablero no está fija y has intentado poner una nueva.");
			}
			visualizer.show();
			move = player.nextMove();
			long t1 = new Date().getTime();
			duration = (int) (t1 - t0);
		}
	}

}
