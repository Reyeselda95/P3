package model;

import java.util.Objects;

/**
 * PieceFactory es una clase que crea una pieza de un tipo en concreto según se pase por parámetro una cosa u otra
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 *
 */
public class PieceFactory {
	/**
	 * Constructor por defecto de la clase PieceFactory
	 */
	public PieceFactory() {
	}

	/**
	 * createPiece(String s): Devuelve un tipo de pieza según el parámetro que se le pase
	 * 
	 * @param s
	 *            es el tipo de pieza que queremos que devuelva
	 * @return devuelve la pieza que deseamos
	 */
	public static Piece createPiece(String s) {
		s = Objects.requireNonNull(s, "El parámetro 's' no puede ser null");
		Piece p = null;
		switch (s) {
			case "I":
				p = new IPiece();
				break;
			case "J":
				p = new JPiece();
				break;
			case "L":
				p = new LPiece();
				break;
			case "O":
				p = new OPiece();
				break;
			case "S":
				p = new SPiece();
				break;
			case "T":
				p = new TPiece();
				break;
			case "Z":
				p = new ZPiece();
				break;
			default:
				break;
		}
		return p;
	}
}
