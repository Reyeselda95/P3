package model;

/**
 * Clase Coordinate: Representa las coordenadas de una pieza dentro de un juego
 * 
 * @author ALEJANDRO REYES ALBILLAR 45931406-S
 *         correo ara65@alu.ua.es
 * 
 */
public class Coordinate {
	/**
	 * row almacena el valor de la fila
	 */
	private int row;
	/**
	 * column almacena el valor de la columna
	 */
	private int column;
	/**
	 * COORDINATE_COUNT cuenta las coordenadas según van siendo creadas.
	 * Cuando se destruyen objetos no disminuye su valor.
	 */
	private static int COORDINATE_COUNT;
	
	//Comienza la parte pública de la clase
	/**
	 * Constructor
	 * 
	 * @param row
	 *            Da a row un valor que recibe por argumentos
	 * @param column
	 *            Da a column un valor que recibe por argumentos
	 */
	public Coordinate(int row, int column) {
		this.row=row;
		this.column=column;
		COORDINATE_COUNT++;
	}

	/**
	 * Constructor de copia
	 * 
	 * @param c
	 *            se pasa un objeto de la clase coordinate y se copia al objeto actual
	 */
	public Coordinate(Coordinate c) {
		this.row = c.row;
		this.column = c.column;
		COORDINATE_COUNT++;
	}
	
	/**
	 * Getter
	 * 
	 * @return row: nos devuelve el valor de la fila en la que se encuentra el puntero
	 */
	public final int getRow() {
		return row;
	}

	/**
	 * Getter
	 * 
	 * @return column: nos devuelve el valor de la columna en la que se encuentra el puntero
	 */
	public final int getColumn() {
		return column;
	}

	/**
	 * add devuelve el valor de dos coordenadas sumadas entre si
	 * 
	 * @param c
	 *            es la coordenada que se va a sumar
	 * @return nos devuelve la suma de las 2 coordenadas
	 */

	public Coordinate add(Coordinate c) {
		return new Coordinate(row + c.row, column + c.column);
	}

	/**
	 * @return devuelve 31*(31+column)+row
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	/**
	 * equals compara el objeto pasado por parámetro y el de la llamada para comprobar si son el mismo, en cuyo caso devolverá true.
	 * Si los dos ojetos no son los mismos la función devolverá false. Cuando se trata del mismo objeto devuelve por defecto true.
	 * 
	 * @param obj
	 *            : es el objeto que se le pasa por parámetro a la función
	 * @return devuelve true o false según se realice o no las diferentes condiciones de la función.
	 * 
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj) // comprueba que el objeto pasado por parámetro es de la misma clase que el desde el cual se llama a la funcion
			return true;
		if (obj == null) // comprueba que el objeto pasado por parámetro no es nulo
			return false;
		if (!(obj instanceof Coordinate)) // comprueba que el objeto pasado por parametro pertenece a la clase Coordinate
			return false;
		Coordinate other = (Coordinate) obj;
		if (column != other.column) // comprueba que la columna es igual
			return false;
		if (row != other.row) // comprueba que la fila es igual
			return false;
		return true;
	}

	/**
	 * toString nos devuelve los valores de row y column entre corchetes en forma de string
	 * 
	 * @return devuelve la coordenada entre corchetes
	 * 
	 */
	@Override
	public String toString() {
		return "[" + row + "," + column + "]";
	}

	/**
	 * Getter
	 * 
	 * @return COORDINATE_COUNT: nos devuelve el valor de las coordenadas que han sido introducidas
	 */
	public static int getCoordinateCount() {
		return COORDINATE_COUNT;
	}

}
