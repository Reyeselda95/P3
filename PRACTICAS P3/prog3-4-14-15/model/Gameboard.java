package model;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import model.exceptions.WrongSizeException;

/**
 * Clase Gameboard: Representa el entorno en el que se desarrolla el juego
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 * 
 */
public class Gameboard {
	/**
	 * Width almacena el ancho del tablero de juego
	 */
	private int width;
	/**
	 * Height almacena la altura del tablero de juego
	 */
	private int height;
	/**
	 * Gameboard es un mapa de coordenadas y piezas
	 */
	private Map<Coordinate, Piece> gameboard = new HashMap<Coordinate, Piece>();
	/**
	 * MINIMUM_BOARD_HEIGHT indica la altura minima del tablero
	 */
	private static final int MINIMUM_BOARD_HEIGHT = 4;
	/**
	 * MINIMUM_BOARD_WIDTH indica la anchura mínima del tablero
	 */
	private static final int MINIMUM_BOARD_WIDTH = 4;

	// Comienza la parte pública de la clase;
	
	/**
	 * 
	 * Gameboard(c): Crea un gameboard a partir de unas unas coordenadas
	 * row representará la anchura (width)
	 * column representará la altura(height)
	 * 
	 * @param c
	 *            es una coordenada de tipo Coordinate
	 * @throws WrongSizeException
	 *             lanza error de tamaño incorrecto
	 */
	public Gameboard(Coordinate c) throws WrongSizeException {
		if (c.getRow() < MINIMUM_BOARD_HEIGHT || c.getColumn() < MINIMUM_BOARD_WIDTH) {
			throw new WrongSizeException(c);
		}
		c = Objects.requireNonNull(c, "El parametro 'c' no puede ser null");
		height = c.getRow();
		width = c.getColumn();
	}

	/**
	 * putPiece pone una pieza en el tablero
	 * 
	 * @param c
	 *            es la coordenada de la pieza
	 * @param p
	 *            es la pieza
	 */
	public void putPiece(Coordinate c, Piece p) {
		c = Objects.requireNonNull(c, "El parámetro 'c' no puede ser null");
		p = Objects.requireNonNull(p, "El parámetro 'p' no puede ser null");
		Set<Coordinate>squares= p.getAbsoluteCells(c);
		for(Coordinate cor:squares) {
			gameboard.put(cor,p);
		}
	}
	
	/**
	 * Getter
	 * 
	 * @return devuelve el valor de el ancho del tablero de juego
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter
	 * 
	 * @return devuelve el valor de la altuta del tablero de juego
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * isPlaceValid(Coordinate c, Piece p)
	 * @param c es la coordenada desde la que se pondrá la parte superior izquierda del cuadrado delimitador de la pieza
	 * @param p es la pieza que se pasa
	 * @return nos devuelve cierto si las coordenadas obtenidas de la funcion Piece.getAbsoluteCells(c) no están fuera del tablero, en cuyo caso devolverá false
	 */
	public boolean isPlaceValid(Coordinate c, Piece p){
		c = Objects.requireNonNull(c, "El parámetro 'c' no puede ser null");
		p = Objects.requireNonNull(p, "El parámetro 'p' no puede ser null");
		boolean valid = true;
		Set<Coordinate>squares= p.getAbsoluteCells(c);
		for (Coordinate cor : squares) {
			if (cor.getRow() > this.getHeight() - 1 || cor.getColumn() > this.getWidth() - 1
					|| cor.getColumn() < 0 || cor.getRow() < 0) {// si es mayor o menor nos devolverá false
				valid = false;
			}
		}
		return valid;
	}

	/**
	 * isPlaceFree(Coordinate c, Piece p)
	 * 
	 * @param c
	 *            la coordenada donde queremos colocar la pieza
	 * @param p
	 *            la pieza que queremos colocar
	 * @return devolverá true si el conjunto de posiciones en las que queremos colocar la pieza
	 *         p.getAbsoluteCells(c) no está ocupado en alguna de sus coordenadas
	 *         Recorreremos gameboard y cuando encontremos algo, miraremos si es el lugar en el que queremos colocar la pieza
	 *         Si es el lugar devolverá false y si no continuará la ejecucion hasta terminar de recorrer gameboard
	 */
	public boolean isPlaceFree(Coordinate c, Piece p) {
		c = Objects.requireNonNull(c, "El parámetro 'c' no puede ser null");
		p = Objects.requireNonNull(p, "El parámetro 'p' no puede ser null");
		boolean free = true;
		Set<Coordinate> squares = p.getAbsoluteCells(c);
		for (Coordinate cor : squares) {
			if (gameboard.containsKey(cor) && gameboard.get(cor).isFixed()) {// Aqui se usa el containsKey para comprobar si está o no la pieza en la posicion y si está la pieza fija o no
				free = false;
			}
		}
		return free;
	}

	/**
	 * removePiece(Piece p): busca en el tablero gameboard una pieza recorriendolo
	 * cuando encuentra una posicion en la que existe algo me devuelve la pieza asociada al valor
	 * la compara con la pasada por parametro y, si es la misma, la borra pasando a la funcion
	 * gameboard.values().remove(Piece p)el valor, en este caso la pieza que buscamos.
	 * Ésto borrará el par <Coordinate, Piece> asociado a la coordenada
	 * y tendremos que repetirlo mientras existan pares asociados a la pieza por lo que utilizaremos un do-while
	 * 
	 * @param p
	 *            la pieza que queremos borrar
	 */
	public void removePiece(Piece p) {
		p = Objects.requireNonNull(p, "El parámetro 'p' no puede ser null");
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Coordinate c = new Coordinate(j, i);
				Piece actual = gameboard.get(c);
				if (actual == p) {
					do {
						gameboard.values().remove(p);
					} while (gameboard.containsValue(p));

				}
			}
		}
	}

	/**
	 * Piece getCellContent(Coordinate c):
	 * 
	 * @param c
	 *            Es una coordenada en la que buscaremos si existe o no una pieza
	 * @return Devuelve una referencia a la pieza que contiene la coordenada pasada o null si no se encuentra ocupada
	 */
	public Piece getCellContent(Coordinate c) {
		c = Objects.requireNonNull(c, "El parámetro 'c' no puede ser null");
		Piece p;
		p = gameboard.get(c);
		return p;
	}

	/**
	 * setCellContent(Coordinate, Piece): Nos introduce la referencia a una pieza en una coordenada.
	 * A diferencia de putPiece() no introduce una pieza entera, sino únicamente una referencia a ella.
	 * 
	 * @param c
	 *            es la coordenada donde queremos colocar la referencia a la pieza
	 * @param p
	 *            es la referencia a la pieza que queremos colocar en el tablero
	 */
	public void setCellContent(Coordinate c, Piece p) {
		c = Objects.requireNonNull(c, "El parámetro 'c' no puede ser null");
		p = Objects.requireNonNull(p, "El parámetro 'p' no puede ser null");
		gameboard.put(c, p);
	}

	/**
	 * gameboard.toString(): Devuelve gameboard convertido en un string en el que cada fila, si contiene una pieza,
	 * esa pieza será representada por el caracter devuelto por Piece.getBlockSymbol(), el valor "▒" y si no
	 * devolverá "·", en cada fila teniendo en cuenta que cada fila tendrá un '\n' al final para terminar con la línea
	 * 
	 * @return Devuelve el valor de gameboard en string
	 */
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {// He tenido que ponerlo así porque la última no me la ejecuta
				Coordinate c = new Coordinate(i, j);
				if (j == width - 1)
				{
					if (gameboard.containsKey(c)) {
						string += getCellContent(c).getBlockSymbol();
					}
					else {
						string += "·";
					}
					string += "\n";
				}
				else {
					if (gameboard.containsKey(c)) {
						string += getCellContent(c).getBlockSymbol();
					}
					else {
						string += "·";
					}
				}
			}
		}
		return string;
	}
	
	/**
	 * boolean isRowFull: nos dice si la fila está llena o no
	 * 
	 * @param i
	 *            es la fila que se ha de comprobar si está llena o no
	 * @return devuelve verdadero si la fila está llena y falso si no
	 * @throws IllegalArgumentException
	 *             argumento ilegal
	 */
	private boolean isRowFull(int i) throws IllegalArgumentException {
		i = Objects.requireNonNull(i, "El parametro 'i' no puede ser null");
		if (i > height || i < 0) {
			throw new IllegalArgumentException();
		}
		boolean b = true;
		for (int j = 0; j < width; j++) {
			Coordinate c = new Coordinate(i, j);
			if (!gameboard.containsKey(c) || !gameboard.get(c).isFixed()) {
				b = false;
			}
		}
		return b;
	}
	
	/**
	 * int firstRowFullFromBottom: nos devuelve la fila que está llena contando desde abajo y -1 si no hay filas llenas
	 * 
	 * @return devuelve el número de fila que está llena contando desde abajo
	 */
	public int firstRowFullFromBottom(){
		int fila = -1;
		int actual = getHeight();
		while (fila == -1 && actual >= 0) {
			if (isRowFull(actual)) {
				fila = actual;
			}
			actual--;
		}

		return fila;
	}
	
	/**
	 * void clearRow: limpia una fila pasada por parámetro
	 * 
	 * @param i
	 *            es la fila que se ha de limpiar
	 * @throws IllegalArgumentException
	 *             argumento ilegal
	 */
	public void clearRow(int i) throws IllegalArgumentException {
		i = Objects.requireNonNull(i, "El parametro 'i' no puede ser null");
		if (i > height || i < 0) {
			throw new IllegalArgumentException();
		}
		for (int j = 0; j <= width; j++) {
			Coordinate c = new Coordinate(i, j);
			if (gameboard.containsKey(c)) {
				gameboard.remove(c);
			}
		}
	}

	/**
	 * void makeUpperRowsFall: hace que las filas caigan desde una fila en concreto
	 * 
	 * @param i
	 *            es la fila desde la que caen las filas hacia abajo
	 * @throws IllegalArgumentException
	 *             argumento ilegal
	 */
	public void makeUpperRowsFall(int i) throws IllegalArgumentException {
		i = Objects.requireNonNull(i, "El parametro 'i' no puede ser null");
		if (i > height || i < 0) {
			throw new IllegalArgumentException();
		}
		for (int fila = i - 1; fila >= 0; fila--) {
			for (int j = 0; j < width; j++) {
				Coordinate c = new Coordinate(fila, j);
				Piece p = gameboard.get(c);
				if (gameboard.containsKey(c)) {
					gameboard.remove(c);
					c = c.add(new Coordinate(1, 0));
					gameboard.put(c, p);
				}
			}
		}

	}

}
