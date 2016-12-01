/**
 * Main package for the first assignment.
 */
package mains;

import java.util.ArrayList;

import model.Coordinate;

/**
@author drizo
@version 20140908
 **/
public class Main1C1415 {

	/**
	 * Entry method.
	 * @param args Arguments from command line.
	 */
	public static void main(String[] args) {
		Coordinate c0; // no instance created for c0 
		Coordinate c1 = new Coordinate(0,0);
		Coordinate c2 = new Coordinate(10,10);
		Coordinate c3 = new Coordinate(4,3);
		Coordinate c4 = new Coordinate(5,15);
		Coordinate c5 = new Coordinate(c4);
		Coordinate c6 = new Coordinate(2,5);
		System.out.println(c1.toString());
		System.out.println();
		
		System.out.println(c2.toString());
		System.out.println();

		System.out.println(c3.toString());
		System.out.println();
		
		System.out.println(c4.toString());
		System.out.println();

		System.out.println(c5.toString());
		System.out.println();
		
		Coordinate s = c3.add(c6);
		System.out.println(c3.toString() + "+" + c6.toString() + "= " + s.toString());
		System.out.println();
		
		Coordinate [] v = new Coordinate[5];
		for (int i=0; i<5; i++) {
			v[i] = new Coordinate(i,4-i);
		}
		
		for (int i=0; i<5; i++) {
			System.out.println(v[i].getRow() + "," + v[i].getColumn());
		}

		ArrayList<Coordinate> v2 = new ArrayList<Coordinate>();
		for (int i=0; i<8; i++) {
			v2.add(new Coordinate(i, i));
			System.out.println(v2.get(i).toString());
		}
		
		Coordinate c7 = c1.add(c3);
		if (c7.equals(c3)) {
			System.out.println("c7 and c3 are equal");
		}

		System.out.println("Coordinate count: " + Coordinate.getCoordinateCount());
		
	}

}
