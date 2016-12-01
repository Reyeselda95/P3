package mains;

import model.Coordinate;
import model.Game;
import model.exceptions.CollisionMovementException;
import model.exceptions.CurrentPieceNotFixedException;
import model.exceptions.FixedPieceMovementException;
import model.exceptions.GameEndedMovementException;
import model.exceptions.NoCurrentPieceException;
import model.exceptions.OffBoardMovementException;
import model.exceptions.WrongSizeException;

public class Main3C1415 {

	public static void main(String[] args) throws WrongSizeException {
		Game game = null;
		try {
			game = new Game(new Coordinate(4, 5));
		} catch (WrongSizeException e1) {
			System.out.println("Ops! Wrong board size!");
		}
		try {
			game.nextPiece("I");
			System.out.println(game.toString());
			game.moveCurrentPieceDown();
			System.out.println(game.toString());
			game.moveCurrentPieceDown();
			System.out.println(game.toString());
			game.moveCurrentPieceDown();
			System.out.println(game.toString());
			game.nextPiece("T");
			System.out.println(game.toString());
			game.moveCurrentPieceRight();
			System.out.println(game.toString());
			game.moveCurrentPieceRight();
			System.out.println(game.toString());
			game.rotateCurrentPieceCounterclockwise();
			System.out.println(game.toString());
			game.moveCurrentPieceRight();
			System.out.println(game.toString());
			game.moveCurrentPieceDown();
			System.out.println(game.toString());
			game.moveCurrentPieceDown();
			System.out.println(game.toString());
		} catch (GameEndedMovementException e) {
			System.out.println("Ops! The game has already ended!");
		} catch (FixedPieceMovementException e) {
			System.out.println("Ops! Current piece is fixed!");
		} catch (OffBoardMovementException e) {
			System.out.println("The piece cannot be moved off the board.");
		} catch (CollisionMovementException e) {
			System.out
					.println("The piece cannot be moved to an occupied cell.");
		} catch (NoCurrentPieceException e) {
			System.out.println("Ops! The game has not started yet!");
		} catch (CurrentPieceNotFixedException e) {
			System.out.println("Ops! The current piece is not fixed yet!");
		}
	}

}

