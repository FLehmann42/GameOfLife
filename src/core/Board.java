package core;

public class Board {
	
	private int height, width;
	private boolean[][] grid;
	
	private static int MAX_HEIGHT = 100;
	private static int MAX_WIDTH = 100;
	
	public Board(int height, int width, boolean[][] start) {
		
		if(height <= 0 || width <= 0) {
			this.height = 1;
			this.width = 1;
			grid = new boolean[1][1];
			return;
		}
		
		this.height = Math.min(MAX_HEIGHT, height);
		this.width = Math.min(MAX_WIDTH, width);
		
		grid = new boolean[height][width];
		
		fillGrid(start);
	}
	
	
	// Fills grid with the desired starting position. If grid and starting position don't match in size, the smaller one is used.
	private void fillGrid(boolean[][] start) {
		
		if(start == null) {
			return;
		}
		
		for (int i=0; i < (start.length < grid.length ? start.length : grid.length); i++) {
			if(start[i] != null && grid[i] != null) {
				for(int j=0; j< (start[i].length < grid[i].length ? start[i].length : grid[i].length); j++) {
					grid[i][j] = start [i][j];
				}
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public boolean[][] getGrid() {
		return grid;
	}
	
	public void setGrid(boolean[][] grid) {
		this.grid = grid;
	}
}
