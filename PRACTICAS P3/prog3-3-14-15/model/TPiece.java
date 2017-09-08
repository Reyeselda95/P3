package model;

/**
 * Clase TPiece: Es una clase que hereda las propiedades de la clase Piece, declarando su propia shape y sus getters
 * 
 * @author ALEJANDRO REYES ALBILLAR
 *         correo ara65@alu.ua.es
 *
 */
public class TPiece extends Piece {
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
	public TPiece() {
		super();
		blockSymbol = '▤';
	}

	/**
	 * Constructor de copia
	 * 
	 * @return p nos devuelve una nueva pieza de tipo TPiece
	 */
	@Override
	public TPiece copy() {
		TPiece p = new TPiece();
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
