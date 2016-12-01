package model;

/**
 * Clase JPiece: Es una clase que hereda las propiedades de la clase Piece, declarando su propia shape y sus getters
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public class JPiece extends Piece {
	/**
	 * shape indica la forma que tendrá la pieza, actualmente almacena 'J'
	 */
	private static int[][] shape = new int[][] {
			{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 } };

	/**
	 * Inicializa los datos de JPiece según se indica
	 * orientation a 'D0'
	 * fixed a false
	 * blockSymbol al caracter en unicode U+25EA ◪
	 */
	public JPiece() {
		super();
		blockSymbol = '◪';
	}

	/**
	 * Constructor de copia
	 * 
	 * @return p nos devuelve una nueva pieza de tipo JPiece
	 */
	@Override
	public JPiece copy() {
		JPiece p = new JPiece();
		p.copyAttributes(this);
		return p;
	}

	/**
	 * getShape() es una función abstracta declarada en Piece y que es heredada en la subclase en la que será inicializada
	 * 
	 * @return devuelve la shape de la pieza
	 */
	@Override
	protected int[][] getShape() {
		return shape;
	}

}
