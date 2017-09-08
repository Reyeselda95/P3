package model;

import java.util.Objects;

import model.exceptions.CollisionMovementException;
import model.exceptions.CurrentPieceNotFixedException;
import model.exceptions.FixedPieceMovementException;
import model.exceptions.GameEndedMovementException;
import model.exceptions.NoCurrentPieceException;
import model.exceptions.OffBoardMovementException;
import model.exceptions.WrongSizeException;

/**
 * Clase Game: Representa una instancia del juego
 * 
 * @author ALEJANDRO REYES ALBILLAR 
 *         correo ara65@alu.ua.es
 *
 */
public class Game {
	/**
	 * gameEnded representa si el juego ha terminado o no.
	 */
	private boolean gameEnded;
	/**
	 * board es el tablero de juego actual
	 */
	private Gameboard board;
	/**
	 * currentPiece es la pieza con la que estamos tratando actualmente
	 */
	private Piece currentPiece;
	/**
	 * currentPosition es la coordenada en la que se encuentra actualmente la esquina superior izquierda del cuadrado delimitador de currentPiece
	 */
	private Coordinate currentPosition;

	//Empieza la parte pública del código


	/**
	 * Game(Coordinate c): Crea un tablero de juego con las coordenadas pasadas por parámetro
	 * 
	 * @param c
	 *            son las coordenadas que conformarán el ancho y el alto del tablero
	 * @throws WrongSizeException
	 *             lanza error de tamaño incorrecto
	 */
	public Game(Coordinate c) throws WrongSizeException {
		c = Objects.requireNonNull(c, "El parámetro 'c' no puede ser null");
		gameEnded = false;
		board = new Gameboard(c);
	}

	/**
	 * getGameboard(): getter
	 * 
	 * @return devuelve el tablero de juego actual
	 */
	protected Gameboard getGameboard() {
		return board;
	}

	/**
	 * nextPiece(String type): Es una función que colocará una pieza que creemos en la mitad del tablero de juego, más específicamente en la segunda fila del tablero.
	 * 
	 * @param type
	 *            indica el tipo de pieza que queremos crear
	 * @return nos devuelve true si la pieza se pudo colocar correctamente.
	 * @throws GameEndedMovementException
	 *             juego finalizado
	 * @throws CurrentPieceNotFixedException
	 *             pieza no fija
	 */
	public boolean nextPiece(String type) throws GameEndedMovementException, CurrentPieceNotFixedException {
		type = Objects.requireNonNull(type, "El parámetro 'type' no puede ser null");
		if (isGameEnded()) {
			throw new GameEndedMovementException();
		}
		if (currentPiece != null) {
			if (!currentPiece.isFixed()) {
				throw new CurrentPieceNotFixedException();
			}
		}
		currentPiece = PieceFactory.createPiece(type);
		// Aqui terminan las excepciones
		currentPosition = new Coordinate(0, board.getWidth() / 2 - 2);
		boolean next = false;
		if (board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece)) {
			board.putPiece(currentPosition, currentPiece);
			next = true;
		}
		else {
			gameEnded = true;
		}
		return next;
	}

	/**
	 * moveCurrentPieceLeft(): mueve la pieza actual a la iquierda si no está fija, el lugar está vacío, el lugar es válido y la pieza no está fija.
	 * 
	 * @throws FixedPieceMovementException
	 *             pieza fija
	 * @throws GameEndedMovementException
	 *             juego finalizado
	 * @throws NoCurrentPieceException
	 *             no pieza
	 * @throws OffBoardMovementException
	 *             fuera del tablero
	 * @throws CollisionMovementException
	 *             colision
	 */
	public void moveCurrentPieceLeft() throws NoCurrentPieceException, FixedPieceMovementException, GameEndedMovementException, OffBoardMovementException, CollisionMovementException {

		if(currentPiece==null) {
			throw new NoCurrentPieceException();
		}
		if (isGameEnded()) {
			throw new GameEndedMovementException();
		}
		if (isCurrentPieceFixed()) {
			throw new FixedPieceMovementException();
		}		
		Coordinate nueva = currentPosition.add(new Coordinate(0, -1));
		if (!board.isPlaceValid(nueva, currentPiece)) {
			throw new OffBoardMovementException(nueva);
		}
		if (!board.isPlaceFree(nueva, currentPiece)) {
			throw new CollisionMovementException(nueva);
		}
		// Aqui terminan las excepciones
		currentPosition = nueva;

		if (!isCurrentPieceFixed() && !isGameEnded() && board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece)) {
			board.removePiece(currentPiece);
			board.putPiece(currentPosition, currentPiece);
		}
		else {
			currentPosition = currentPosition.add(new Coordinate(0, 1));
		}
	}

	/**
	 * moveCurrentPieceRight(): mueve la pieza actual a la derecha si no está fija, el lugar está vacío, el lugar es válido y la pieza no está fija.
	 * 
	 * @throws NoCurrentPieceException
	 *             no pieza
	 * @throws GameEndedMovementException
	 *             juego finalizado
	 * @throws FixedPieceMovementException
	 *             pieza fija
	 * @throws OffBoardMovementException
	 *             fuera del tablero
	 * @throws CollisionMovementException
	 *             colision
	 */
	public void moveCurrentPieceRight() throws NoCurrentPieceException, GameEndedMovementException, FixedPieceMovementException, OffBoardMovementException, CollisionMovementException {
		
		if (currentPiece == null) {
			throw new NoCurrentPieceException();
		}
		if (isGameEnded()) {
			throw new GameEndedMovementException();
		}
		if (isCurrentPieceFixed()) {
			throw new FixedPieceMovementException();
		}
		Coordinate nueva = currentPosition.add(new Coordinate(0, 1));
		if (!board.isPlaceValid(nueva, currentPiece)) {
			throw new OffBoardMovementException(nueva);
		}
		if (!board.isPlaceFree(nueva, currentPiece)) {
			throw new CollisionMovementException(nueva);
		}
		// Aqui terminan las excepciones
		
		currentPosition = nueva;
		if (!isCurrentPieceFixed() && !isGameEnded() && board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece)) {
			board.removePiece(currentPiece);
			board.putPiece(currentPosition, currentPiece);
		}
		else {
			currentPosition = currentPosition.add(new Coordinate(0, -1));
		}
	}

	/**
	 * moveCurrentPieceDown(): mueve la pieza actual a abajo si no está fija, el lugar está vacío, el lugar es válido y la pieza no está fija.
	 * 
	 * @throws NoCurrentPieceException
	 *             no pieza
	 * @throws GameEndedMovementException
	 *             juego finalizado
	 * @throws FixedPieceMovementException
	 *             pieza fija
	 */
	public void moveCurrentPieceDown() throws NoCurrentPieceException, GameEndedMovementException, FixedPieceMovementException {
		if (currentPiece == null) {
			throw new NoCurrentPieceException();
		}
		if (isGameEnded()) {
			throw new GameEndedMovementException();
		}
		if (isCurrentPieceFixed()) {
			throw new FixedPieceMovementException();
		}
		// Aqui terminan las excepciones
		Coordinate nueva = currentPosition.add(new Coordinate(1, 0));
		currentPosition = nueva;

		if (!board.isPlaceValid(currentPosition, currentPiece) || !board.isPlaceFree(currentPosition, currentPiece)) {
			currentPiece.setFixed(true);
			int borrar;
			borrar = board.firstRowFullFromBottom();
			while (borrar > -1) {
				board.clearRow(borrar);
				board.makeUpperRowsFall(borrar);
				borrar = board.firstRowFullFromBottom();
			}
		}
		else {
			board.removePiece(currentPiece);
			board.putPiece(currentPosition, currentPiece);
		}
	}

	/**
	 * rotateCurrentPieceCounterClockwise rota la pieza actual en el sentido contrario al de las agujas del reloj
	 * 
	 * @throws NoCurrentPieceException
	 *             no pieza
	 * @throws GameEndedMovementException
	 *             juego finalizado
	 * @throws FixedPieceMovementException
	 *             pieza fija
	 * @throws OffBoardMovementException
	 *             fuera del tablero
	 * @throws CollisionMovementException
	 *             colision
	 */
	public void rotateCurrentPieceCounterclockwise() throws NoCurrentPieceException, GameEndedMovementException, FixedPieceMovementException, OffBoardMovementException, CollisionMovementException {

		if (currentPiece == null) {
			throw new NoCurrentPieceException();
		}
		if (isGameEnded()) {
			throw new GameEndedMovementException();
		}
		if (isCurrentPieceFixed()) {
			throw new FixedPieceMovementException();
		}
		currentPiece.rotateCounterclockwise();
		if (!board.isPlaceValid(currentPosition, currentPiece)) {
			currentPiece.rotateClockwise();
			throw new OffBoardMovementException(currentPosition);
		}
		if (!board.isPlaceFree(currentPosition, currentPiece)) {
			currentPiece.rotateClockwise();
			throw new CollisionMovementException(currentPosition);
		}
		// Aqui terminan las excepciones
		board.removePiece(currentPiece);
		board.putPiece(currentPosition, currentPiece);

	}

	/**
	 * rotateCurrentPieceClockwise rota la pieza actual en el sentido de las agujas del reloj
	 * 
	 * @throws NoCurrentPieceException
	 *             no pieza
	 * @throws GameEndedMovementException
	 *             juego finalizado
	 * @throws FixedPieceMovementException
	 *             pieza fija
	 * @throws OffBoardMovementException
	 *             fuera del tablero
	 * @throws CollisionMovementException
	 *             colision
	 */
	public void rotateCurrentPieceClockwise() throws NoCurrentPieceException, GameEndedMovementException, FixedPieceMovementException, OffBoardMovementException, CollisionMovementException {

		if (currentPiece == null) {
			throw new NoCurrentPieceException();
		}
		if (isGameEnded()) {
			throw new GameEndedMovementException();
		}
		if (isCurrentPieceFixed()) {
			throw new FixedPieceMovementException();
		}
		currentPiece.rotateClockwise();
		if (!board.isPlaceValid(currentPosition, currentPiece)) {
			currentPiece.rotateCounterclockwise();
			throw new OffBoardMovementException(currentPosition);
		}
		if (!board.isPlaceFree(currentPosition, currentPiece)) {
			currentPiece.rotateCounterclockwise();
			throw new CollisionMovementException(currentPosition);
		}
		// Aqui terminan las excepciones
		board.removePiece(currentPiece);
		board.putPiece(currentPosition, currentPiece);
	}

	/**
	 * Game.toString(): Devuelve el valor del tablero en forma de String de la misma forma que lo hace Gameboard.toString().
	 * 
	 * @return devuelve el tablero en forma de string
	 */
	@Override
	public String toString() {
		return board.toString();
	}

	/**
	 * isCurrentPieceFixed dice si la pieza actual está fija o no
	 * 
	 * @return devuelve true si la pieza está fija y false si no
	 * @throws NoCurrentPieceException
	 *             no pieza
	 */
	public boolean isCurrentPieceFixed() throws NoCurrentPieceException {
		if (currentPiece == null) {
			throw new NoCurrentPieceException();
		}
		// Aqui terminan las excepciones
		return currentPiece.isFixed();
	}

	/**
	 * isGameEnded dice si el juego ha acabado
	 * 
	 * @return devuelve verdadero si el campo gameEnded es true y falso si no
	 */
	public boolean isGameEnded() {
		return gameEnded;
	}

}
