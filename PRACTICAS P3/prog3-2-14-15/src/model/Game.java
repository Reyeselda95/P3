package model;

/**
 * Clase Game: Representa una instancia del juego
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public class Game {
	/**
	 * gameEnded representa si el juego ha terminado o no.
	 */
	private boolean gameEnded;
	/**
	 * board es
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
	 */
	public Game(Coordinate c) {
		gameEnded = false;
		board = new Gameboard(c);
	}

	/**
	 * nextPiece(): Es una función que colocará una pieza que creemos en la mitad del tablero de juego, más específicamente en la segunda fila del tablero.
	 * 
	 * @return nos devuelve true si la pieza se pudo colocar correctamente.
	 */
	public boolean nextPiece() {
		Piece p = new Piece();
		currentPiece = p;
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
	 */
	public void moveCurrentPieceLeft() {
		currentPosition = currentPosition.add(new Coordinate(0, -1));
		if (isCurrentPieceFixed() == false && isGameEnded() == false && board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece) == true) {
			board.removePiece(currentPiece);
			board.putPiece(currentPosition, currentPiece);
		}
	}

	/**
	 * moveCurrentPieceRight(): mueve la pieza actual a la derecha si no está fija, el lugar está vacío, el lugar es válido y la pieza no está fija.
	 */
	public void moveCurrentPieceRight() {
		currentPosition = currentPosition.add(new Coordinate(0, 1));
		if (isCurrentPieceFixed() == false && isGameEnded() == false && board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece) == true) {
			board.removePiece(currentPiece);
			board.putPiece(currentPosition, currentPiece);
		}
	}

	/**
	 * moveCurrentPieceDown(): mueve la pieza actual a abajo si no está fija, el lugar está vacío, el lugar es válido y la pieza no está fija.
	 */
	public void moveCurrentPieceDown() {
		currentPosition = currentPosition.add(new Coordinate(1, 0));
		if (!isCurrentPieceFixed() && !isGameEnded()) {
			if (!board.isPlaceValid(currentPosition, currentPiece) || !board.isPlaceFree(currentPosition, currentPiece)) {
				currentPiece.setFixed(true);
			}
			else if (board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece)) {
				board.removePiece(currentPiece);
				board.putPiece(currentPosition, currentPiece);
			}
		}
	}

	/**
	 * rotateCurrentPieceCounterClockwise rota la pieza actual en el sentido contrario al de las agujas del reloj
	 */
	public void rotateCurrentPieceCounterclockwise() {
		currentPiece.rotateCounterclockwise();
		if (!isCurrentPieceFixed() && !isGameEnded() && board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece)) {
			board.removePiece(currentPiece);
			board.putPiece(currentPosition, currentPiece);
		}
		else {
			currentPiece.rotateClockwise();
		}

	}

	/**
	 * rotateCurrentPieceClockwise rota la pieza actual en el sentido de las agujas del reloj
	 */
	public void rotateCurrentPieceClockwise() {
		currentPiece.rotateClockwise();
		if (!isCurrentPieceFixed() && !isGameEnded() && board.isPlaceValid(currentPosition, currentPiece) && board.isPlaceFree(currentPosition, currentPiece)) {
			board.removePiece(currentPiece);
			board.putPiece(currentPosition, currentPiece);
		}
		else {
			currentPiece.rotateCounterclockwise();
		}
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
	 */
	public boolean isCurrentPieceFixed() {
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
