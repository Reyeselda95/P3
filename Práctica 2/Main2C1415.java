package mains;


import model.Coordinate;
import model.Game;

public class Main2C1415 {
	
	public static void main(String[] args) {	
		Game game= new Game(new Coordinate(7, 10));
		System.out.println(game.toString());
		game.nextPiece();
		System.out.println(game.toString());
		game.rotateCurrentPieceClockwise();
		System.out.println(game.toString());
		game.rotateCurrentPieceCounterclockwise();
		System.out.println(game.toString());
		game.rotateCurrentPieceCounterclockwise();
		System.out.println(game.toString());
		game.moveCurrentPieceLeft();
		System.out.println(game.toString());
		game.moveCurrentPieceRight();
		System.out.println(game.toString());
		game.moveCurrentPieceRight();
		System.out.println(game.toString());
		game.moveCurrentPieceDown();
		System.out.println(game.toString());
		game.moveCurrentPieceDown();
		System.out.println(game.toString());
		game.moveCurrentPieceDown();
		System.out.println(game.toString());
		game.moveCurrentPieceDown();
		System.out.println(game.toString());
		System.out.println(game.isCurrentPieceFixed());
	}

}
