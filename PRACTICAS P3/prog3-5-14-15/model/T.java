package model;

/**
 * Clase TPiece: Es una clase que hereda las propiedades de la clase Piece, declarando su propia shape y sus getters
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public class T extends Piece {
	/**
	 * shape indica la forma que tendrá la pieza, actualmente almacena 'T'
	 */
	private static int[][] shape = new int[][] {
			{ 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } };

	/**
	 * Inicializa los datos de TPiece según se indica
	 * orientation a 'D0'
	 * fixed a false
	 * blockSymbol al caracter en unicode U+25A4 ▤
	 */
	public T() {
		super();
		blockSymbol = '▤';
	}

	/**
	 * Constructor de copia
	 * 
	 * @return p nos devuelve una nueva pieza de tipo TPiece
	 */
	@Override
	public T copy() {
		T p = new T();
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
