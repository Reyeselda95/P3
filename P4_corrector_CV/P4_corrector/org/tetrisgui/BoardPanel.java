package org.tetrisgui;

/* This is a reduced version of the Tetris code by Brendan Jones available
 * at https://github.com/PSNB92/Tetris. It has been modified to keep the functionality
 * of drawing Tetris boards, but the rest of features have mostly been removed. 
 */

/* The original code is commented here: 
 * http://psnbtech.blogspot.com.es/2012/10/tutorial-java-tetris-game.html
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The {@code BoardPanel} class is responsible for displaying the game grid and
 * handling things related to the game board.
 * @author Brendan Jones
 *
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	/**
	 * Minimum color component values for tiles. This is required if we
	 * want to show both light and dark shading on our tiles.
	 */
	public static final int COLOR_MIN = 35;
	
	/**
	 * Maximum color component values for tiles. This is required if we
	 * want to show both light and dark shading on our tiles.
	 */
	public static final int COLOR_MAX = 255 - COLOR_MIN;
	
	/**
	 * The width of the border around the game board.
	 */
	private static final int BORDER_WIDTH = 5;
	
	/**
	 * The number of columns on the board.
	 */
	public static final int COL_COUNT = 10;
		
	/**
	 * The number of visible rows on the board.
	 */
	private static final int VISIBLE_ROW_COUNT = 20;
	
	/**
	 * The total number of rows that the board contains.
	 */
	public static final int ROW_COUNT = VISIBLE_ROW_COUNT;
	
	/**
	 * The number of pixels that a tile takes up.
	 */
	public static final int TILE_SIZE = 24;
	
	/**
	 * The width of the shading on the tiles.
	 */
	public static final int SHADE_WIDTH = 4;
	
	/**
	 * The total width of the panel.
	 */
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	
	/**
	 * The total height of the panel.
	 */
	public static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;

	/**
	 * The tiles that make up the board.
	 */
	private TileType[][] tiles;
		
	/**
	 * Crates a new GameBoard instance.
	 * @param tetris The Tetris instance to use.
	 */
	public BoardPanel() {
		this.tiles = new TileType[ROW_COUNT][COL_COUNT];
		
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}
	
	/**
	 * Resets the board and clears away any tiles.
	 */
	public void clear() {
		/*
		 * Loop through every tile index and set it's value
		 * to null to clear the board.
		 */
		for(int i = 0; i < ROW_COUNT; i++) {
			for(int j = 0; j < COL_COUNT; j++) {
				tiles[i][j] = null;
			}
		}
	}
	
	public void addPieces() {
		setTile(1, 0, TileType.TypeI);   // x,y (column,row)
		setTile(1, 1, TileType.TypeI);
		setTile(1, 2, TileType.TypeI);
		setTile(1, 3, TileType.TypeI);
		setTile(5, 5, TileType.TypeS);
	}
	
	/**
	 * Sets a tile located at the desired column and row.
	 * @param x The column.
	 * @param y The row.
	 * @param type The value to set to the tile to.
	 */
	public void setTile(int  x, int y, TileType type) {
		tiles[y][x] = type;
	}
		
	/**
	 * Gets a tile by it's column and row.
	 * @param x The column.
	 * @param y The row.
	 * @return The tile.
	 */
	private TileType getTile(int x, int y) {
		return tiles[y][x];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//This helps simplify the positioning of things.
		g.translate(BORDER_WIDTH, BORDER_WIDTH);
		
			/*
			 * Draw the tiles onto the board.
			 */
			for(int x = 0; x < COL_COUNT; x++) {
				for(int y = 0; y < ROW_COUNT; y++) {
					TileType tile = getTile(x, y);
					if(tile != null) {
						drawTile(tile, x * TILE_SIZE, (y) * TILE_SIZE, g);
					}
				}
			}
			
			/*
			 * Draw the background grid above the pieces (serves as a useful visual
			 * for players, and makes the pieces look nicer by breaking them up.
			 */
			g.setColor(Color.DARK_GRAY);
			for(int x = 0; x < COL_COUNT; x++) {
				for(int y = 0; y < VISIBLE_ROW_COUNT; y++) {
					g.drawLine(0, y * TILE_SIZE, COL_COUNT * TILE_SIZE, y * TILE_SIZE);
					g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, VISIBLE_ROW_COUNT * TILE_SIZE);
				}
			}
				
		/*
		 * Draw the outline.
		 */
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
	}
	
	/**
	 * Draws a tile onto the board.
	 * @param type The type of tile to draw.
	 * @param x The column.
	 * @param y The row.
	 * @param g The graphics object.
	 */
	private void drawTile(TileType type, int x, int y, Graphics g) {
		drawTile(type.getBaseColor(), type.getLightColor(), type.getDarkColor(), x, y, g);
	}
	
	/**
	 * Draws a tile onto the board.
	 * @param base The base color of tile.
	 * @param light The light color of the tile.
	 * @param dark The dark color of the tile.
	 * @param x The column.
	 * @param y The row.
	 * @param g The graphics object.
	 */
	private void drawTile(Color base, Color light, Color dark, int x, int y, Graphics g) {
		
		/*
		 * Fill the entire tile with the base color.
		 */
		g.setColor(base);
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		
		/*
		 * Fill the bottom and right edges of the tile with the dark shading color.
		 */
		g.setColor(dark);
		g.fillRect(x, y + TILE_SIZE - SHADE_WIDTH, TILE_SIZE, SHADE_WIDTH);
		g.fillRect(x + TILE_SIZE - SHADE_WIDTH, y, SHADE_WIDTH, TILE_SIZE);
		
		/*
		 * Fill the top and left edges with the light shading. We draw a single line
		 * for each row or column rather than a rectangle so that we can draw a nice
		 * looking diagonal where the light and dark shading meet.
		 */
		g.setColor(light);
		for(int i = 0; i < SHADE_WIDTH; i++) {
			g.drawLine(x, y + i, x + TILE_SIZE - i - 1, y + i);
			g.drawLine(x + i, y, x + i, y + TILE_SIZE - i - 1);
		}
	}

}
