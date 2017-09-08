package model;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Clase Piece: Representa cada una de las piezas que existen dentro del juego
 * 
 * @author ALEJANDRO REYES ALBILLAR
 *         correo ara65@alu.ua.es
 *
 */
public abstract class Piece {
	/**
	 * fixed nos dice si la pieza está fija o no
	 */
	private boolean fixed;
	/**
	 * blockSymbol almacena el caracter con el que será representada la pieza, es de tipo protected por lo que la podrán ver únicamente los de su mismo paquete o clase/subclase
	 */
	protected char blockSymbol;
	/**
	 * BOUNDING_SQARE_SIZE nos dice el tamaño que tendrá el cuadrado delimitador del tetrominio. Por defecto será 4.
	 */
	private static int BOUNDING_SQUARE_SIZE = 4;
	/**
	 * orientation es un clase Rotation que nos dice en qué orientación está la pieza, por defecto a 'D0'
	 */
	private Rotation orientation;
	// Comienza la parte pública de la clase

	/**
	 * Crea una pieza inicializando sus valores fixed a false y orientation a D0
	 * Se llamará a esta función desde sus hijas con la orden super();
	 */
	public Piece() {
		orientation = Rotation.D0;
		fixed = false;
	}
	
	/**
	 * Constructor de copia
	 * 
	 * se llama desde una pieza y se define en la subclase de la misma, donde es copiada a la que queremos usar
	 * 
	 * @return p Es una Piece
	 */
	public abstract Piece copy();

	/**
	 * copyAttributes(Piece p): Es una función que copia los atributos de orientación, fixed y blocksymbol de la pieza pasada por parámetro en la pieza que llama a la función
	 * 
	 * @param p
	 *            es la pieza que queremos copiar en la actual
	 */
	protected void copyAttributes(Piece p) {
		p = Objects.requireNonNull(p, "El parámetro 'p' no puede ser null");
		this.orientation = p.orientation;
		this.fixed = p.fixed;
		this.blockSymbol = p.blockSymbol;
	}

	/**
	 * Devuelve si la pieza está fija o no
	 * 
	 * @return Devuelve true si la pieza está fija y false si no
	 */
	public boolean isFixed() {
		return fixed;
	}

	/**
	 * Getter
	 * 
	 * @return Devuelve la orientación de la pieza
	 */
	public Rotation getOrientation() {
		return this.orientation;
	}

	/**
	 * Getter
	 * 
	 * @return Devuelve el valor de blockSymbol
	 */
	public char getBlockSymbol() {
		return blockSymbol;
	}

	/**
	 * Setter
	 * 
	 * @param r
	 *            es la rotación que queremos darle a la pieza
	 */
	public void setOrientation(Rotation r) {
		r = Objects.requireNonNull(r, "El parámetro 'r' no puede ser null");
		orientation = r;

	}

	/**
	 * Gira la pieza en el sentido de las agujas del reloj
	 * Hay que acordarse de que las piezas son
	 * 0º -->(derecha),
	 * 90º -->(arriba),
	 * 180º -->(izquierda),
	 * 270º -->(abajo)
	 */
	public void rotateClockwise() {
		if (getOrientation() == Rotation.D0) {
			setOrientation(Rotation.D270);
		}
		else if (getOrientation() == Rotation.D90) {
			setOrientation(Rotation.D0);
		}
		else if (getOrientation() == Rotation.D270) {
			setOrientation(Rotation.D180);
		}
		else if (getOrientation() == Rotation.D180) {
			setOrientation(Rotation.D90);
		}
	}

	/**
	 * Gira la pieza en el sentido contrario a las agujas del reloj
	 * Hay que acordarse de que las piezas son
	 * 0º -->(derecha),
	 * 90º -->(arriba),
	 * 180º -->(izquierda),
	 * 270º -->(abajo)
	 */
	public void rotateCounterclockwise() {
		if (orientation == Rotation.D0) {
			setOrientation(Rotation.D90);
		}
		else if (this.getOrientation() == Rotation.D90) {
			setOrientation(Rotation.D180);
		}
		else if (orientation == Rotation.D270) {
			setOrientation(Rotation.D0);
		}
		else if (orientation == Rotation.D180) {
			setOrientation(Rotation.D270);
		}
	}

	/**
	 * getAbsoluteCells(Coordinate c)
	 * 
	 * @param c
	 *            se le pasa una coordenada que nos dirá donde se coloca el cuadrado que contiene el tetrominio
	 * @return nos devuelve el valor de las celdas que serán ocupadas por el tetrominio
	 */
	public Set<Coordinate> getAbsoluteCells(Coordinate c) {
		c = Objects.requireNonNull(c, "El parámetro 'c' no puede ser null");
		Set<Coordinate> squares = new HashSet<Coordinate>();
		int linea = 0;
		int cont = 0;
		for (int i=0;i<BOUNDING_SQUARE_SIZE*BOUNDING_SQUARE_SIZE;i++){
			if (getShape()[orientation.ordinal()][i] == 1) {
				squares.add(new Coordinate(c.getRow() + linea, c.getColumn() + i % BOUNDING_SQUARE_SIZE));
			}
			cont++;
			if (cont == BOUNDING_SQUARE_SIZE) {
				linea++;
				cont = 0;
			}
		}
		return squares;
	}

	/**
	 * Setter
	 * 
	 * @param b
	 *            es un parámetro tipo bool que dará valor al campo fixed
	 */
	public void setFixed(boolean b) {
		fixed = b;
		fixed = Objects.requireNonNull(b, "El parámetro 'b' no puede ser null");
	}

	/**
	 * Función toString():
	 * usando la funcion Rotation.ordinal() recibo el orden en el que se han introducido las variables dentro de la clase Rotation;
	 * 
	 * @return Devuelve un string que contiene el cuadrado delimitador de la pieza en la posición actual.
	 *         Contiene una línea por cada fila del cuadrado y cuando dicha fila termina se añade el caracter '\n' para que termine la línea
	 * 
	 */
	@Override
	public String toString() {
		String piece = "";
		int count = 0;
		for (int j = 0; j < BOUNDING_SQUARE_SIZE * BOUNDING_SQUARE_SIZE; j++) {
			if (count == BOUNDING_SQUARE_SIZE) {//Cuando es la ultima posicion de la fila
				if (j == (BOUNDING_SQUARE_SIZE * BOUNDING_SQUARE_SIZE) - 1) {// Cuando es la última posicion de la pieza
					piece += '\n';
					count = 0;
					if (getShape()[orientation.ordinal()][j] == 0 && j == 0) {
						piece = "·";
						count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 1 && j == 0) {
						piece += getBlockSymbol();
						count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 0 && j > 0) {
						piece += '·';
						count++;
					}
					else {
						piece += this.getBlockSymbol();
						count++;
					}
					piece += '\n';
				}
				else {
					piece = piece + '\n';
					count = 0;
					if (getShape()[orientation.ordinal()][j] == 0 && j == 0) {
						piece = "·";
						count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 1 && j == 0) {
						piece += getBlockSymbol();
						count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 0 && j > 0) {
						piece = piece + '·';
						count++;
					}
					else {
						piece = piece + this.getBlockSymbol();
						count++;
					}
				}

			}
			else {
				if (j == (BOUNDING_SQUARE_SIZE * BOUNDING_SQUARE_SIZE) - 1) {

					if (getShape()[orientation.ordinal()][j] == 0 && j == 0) {
						piece = "·";
						count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 1 && j == 0) {
						piece += getBlockSymbol();
						count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 0) {
						piece += '·';
						count++;
					}
					else {
						piece += this.getBlockSymbol();
						count++;
					}
					piece += '\n';
				}
				else {
					if (getShape()[orientation.ordinal()][j] == 0 && j == 0) {
					piece = "·";
					count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 1 && j == 0) {
						piece += this.getBlockSymbol();
						count++;
					}
					else if (getShape()[orientation.ordinal()][j] == 0) {
						piece += '·';
						count++;
					}
					else {
						piece += this.getBlockSymbol();
						count++;
					}
				}
			}
		}
		return piece;
	}

	/**
	 * Devuelve la shape de una pieza
	 * 
	 * @return shape
	 */
	abstract protected int[][] getShape();

}
