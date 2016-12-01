package model;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

	// Comienza la parte pública de la clase;
	
	/**
	 * 
	 * Gameboard(c): Crea un gameboard a partir de unas unas coordenadas
	 * row representará la anchura (width)
	 * column representará la altura(height)
	 * 
	 * @param c
	 *            es una coordenada de tipo Coordinate
	 */
	public Gameboard(Coordinate c) {
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
		boolean free = true;
		Set<Coordinate> squares = p.getAbsoluteCells(c);
		for (Coordinate cor : squares) {
			if (gameboard.containsKey(cor) && gameboard.get(cor) != p) {// Aqui se usa el containsKey para comprobar si está o no la pieza en la posicion
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
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Coordinate c = new Coordinate(i, j);
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
		String string = null;
		int count = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Coordinate c = new Coordinate(i, j);
				if (count < width) {// Mientras no sea la ultima posicion de la fila
					if (i == height - 1 && j == width - 1) {// Si es la última posicion de fila y columna
						if (i == 0 && j == 0 && getCellContent(c) != null) {
							string = "▒";
							count++;
						}
						else if (i == 0 && j == 0 && getCellContent(c) == null) {
							string = "·";
							count++;
						}
						else if (getCellContent(c) == null) {
							string += '·';
							count++;
						}
						else {
							string += getCellContent(c).getBlockSymbol();
							count++;
						}
						string += '\n';
					}
					else {// Si no es la última posicion de fila y columna
						if (i == 0 && j == 0 && getCellContent(c) != null) {
							string = "▒";
							count++;
						}
						else if (i == 0 && j == 0 && getCellContent(c) == null) {
							string = "·";
							count++;
						}
						else if (getCellContent(c) == null) {
							string += '·';
							count++;
						}
						else {
							string += getCellContent(c).getBlockSymbol();
							count++;
						}
					}
				}
				else {// Cuando es laq última posicion de la fila
					count = 0;
					string += '\n';
					if (i == height - 1 && j == width - 1) {
						string += '\n';
					}
					else if (i == 0 && j == 0 && getCellContent(c) != null) {
						string = "▒";
						count++;
					}
					else if (i == 0 && j == 0 && getCellContent(c) == null) {
						string = "·";
						count++;
					}
					else if (getCellContent(c) == null) {
						string += '·';
						count++;
					}
					else {
						string += getCellContent(c).getBlockSymbol();
						count++;
					}
				}
			}
		}
		return string;
	}

}
