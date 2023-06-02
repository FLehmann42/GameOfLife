package core;

public class Game {
	
	Board board;
	
	public Game(Board board) {
		
		if(board != null) {
			this.board = board;
		} else {
			this.board = new Board(0,0, new boolean[0][0]);
		}
		
			 
            
	}
	
	
	// Calculates the next generation, and returns the grid of that next generation as well as setting the board's grid to it.
	public boolean[][] nextGeneration() {
		
		boolean[][] newGrid = new boolean[board.getHeight()][board.getWidth()];
		
		for(int i = 0; i < board.getHeight(); i++) {
			for(int j = 0; j < board.getWidth(); j++) {
				newGrid[i][j] = calculateCellLifeStatus(i,j);
			}
		}
		
		board.setGrid(newGrid);
		
		return newGrid;
	}
	
	// Calculates the next generation of the cell with the given coordinates.
	public boolean calculateCellLifeStatus(int height, int width) {
		if(board.getGrid()[height][width]) { 	// Living cell
			return getLivingNeighbourCount( height , width ) >= 2 && getLivingNeighbourCount( height , width ) <= 3 ? true : false;
		} else {						// Dead cell
			return getLivingNeighbourCount( height , width ) == 3 ? true : false;
		}
	}
	
	// Calculates the number of living cells in the neighborhood of the cell with the given coordinates.
	public int getLivingNeighbourCount(int height, int width) {
		
		int count = 0;
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0) {		// Center cell, doesn't count
					continue;		
				}
				
				if(height + i >= board.getHeight() || height + i < 0 || width + j >= board.getWidth() || width + j < 0) { // Out of bounds
					continue;
				}
				
				count = count + (board.getGrid()[height + i][width + j] ? 1 : 0);
				
			}
		}
		
		
		return count;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
}
