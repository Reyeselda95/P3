package org.tetrisgui;

/* This is a reduced version of the Tetris code by Brendan Jones available
 * at https://github.com/PSNB92/Tetris. It has been modified to keep the functionality
 * of drawing Tetris boards, but the rest of features have mostly been removed. 
 */

/* The original code is commented here: 
 * http://psnbtech.blogspot.com.es/2012/10/tutorial-java-tetris-game.html
 */

import java.awt.Color;

/**
 * The {@code PieceType} enum describes the properties of the various pieces that can be used in the game.
 * @author Brendan Jones
 *
 */
public enum TileType {

	/**
	 * Piece TypeI.
	 */
	TypeI(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX), 4, 4, 1),
	
	/**
	 * Piece TypeJ.
	 */
	TypeJ(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX), 3, 3, 2),
	
	/**
	 * Piece TypeL.
	 */
	TypeL(new Color(BoardPanel.COLOR_MAX, 127, BoardPanel.COLOR_MIN), 3, 3, 2),
	
	/**
	 * Piece TypeO.
	 */
	TypeO(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 2, 2, 2),
	
	/**
	 * Piece TypeS.
	 */
	TypeS(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 3, 3, 2),
	
	/**
	 * Piece TypeT.
	 */
	TypeT(new Color(128, BoardPanel.COLOR_MIN, 128), 3, 3, 2),
	
	/**
	 * Piece TypeZ.
	 */
	TypeZ(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN), 3, 3, 2);
		
	/**
	 * The base color of tiles of this type.
	 */
	private Color baseColor;
	
	/**
	 * The light shading color of tiles of this type.
	 */
	private Color lightColor;
	
	/**
	 * The dark shading color of tiles of this type.
	 */
	private Color darkColor;
	
	/**
	 * The dimensions of the array for this piece.
	 */
	private int dimension;
	
	/**
	 * The number of rows in this piece. (Only valid when rotation is 0 or 2,
	 * but it's fine since we're only using it for displaying the next piece
	 * preview, which uses rotation 0).
	 */
	private int rows;
	
	/**
	 * The number of columns in this piece. (Only valid when rotation is 0 or 2,
	 * but it's fine since we're only using it for displaying the next piece
	 * preview, which uses rotation 0).
	 */
	private int cols;

	/**
	 * Creates a new TileType.
	 * @param color The base color of the tile.
	 * @param dimension The dimensions of the tiles array.
	 * @param cols The number of columns.
	 * @param rows The number of rows.
	 * @param tiles The tiles.
	 */
	private TileType(Color color, int dimension, int cols, int rows) {
		this.baseColor = color;
		this.lightColor = color.brighter();
		this.darkColor = color.darker();
		this.dimension = dimension;
		this.cols = cols;
		this.rows = rows;
	}
	
	/**
	 * Gets the base color of this type.
	 * @return The base color.
	 */
	public Color getBaseColor() {
		return baseColor;
	}
	
	/**
	 * Gets the light shading color of this type.
	 * @return The light color.
	 */
	public Color getLightColor() {
		return lightColor;
	}
	
	/**
	 * Gets the dark shading color of this type.
	 * @return The dark color.
	 */
	public Color getDarkColor() {
		return darkColor;
	}
	
	/**
	 * Gets the dimension of this type.
	 * @return The dimension.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Gets the number of rows in this piece. (Only valid when rotation is 0 or 2,
	 * but it's fine since this is only used for the preview which uses rotation 0).
	 * @return The number of rows.
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Gets the number of columns in this piece. (Only valid when rotation is 0 or 2,
	 * but it's fine since this is only used for the preview which uses rotation 0).
	 * @return The number of columns.
	 */
	public int getCols() {
		return cols;
	}
	
}
