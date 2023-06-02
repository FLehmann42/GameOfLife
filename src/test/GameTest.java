package test;

import core.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class GameTest {
	
	private static boolean[][] FULL_FIVE_BY_FIVE_GRID = {
			{true, true, true, true, true},
			{true, true, true, true, true},
			{true, true, true, true, true},
			{true, true, true, true, true},
			{true, true, true, true, true}
	};
	
	private static boolean[][] FULL_GEN1_FIVE_BY_FIVE_GRID = {
			{true, false, false, false, true},
			{false, false, false, false, false},
			{false, false, false, false, false},
			{false, false, false, false, false},
			{true, false, false, false, true}
	};
	
	private static boolean[][] GLIDER_FIVE_BY_FIVE_GRID = {
			{false, false, false, false, false},
			{false, false, false, false, false},
			{true, true, true, false, false},
			{false, false, true, false, false},
			{false, true, false, false, false}
	};
	
	private static boolean[][] GLIDER_GEN8_FIVE_BY_FIVE_GRID = {
			{false, false, true, true, true},
			{false, false, false, false, true},
			{false, false, false, true, false},
			{false, false, false, false, false},
			{false, false, false, false, false}
	};

	@Test
	void testNullBoard() {
		Board board = null;
		Game game = new Game(board);
		
		Board emptyBoard = new Board(0,0, new boolean[0][0]);
		
		assertTrue(equalBoards(game.getBoard(), emptyBoard));
	}
	
	@Test
	void testEmptyBoard() {
		Board board = new Board(0,0, new boolean[0][0]);
		Board board2 = new Board(0,0, new boolean[0][0]);
		Game game = new Game(board);
		
		assertEquals(game.getLivingNeighbourCount(0, 0), 0);
		game.nextGeneration();
		assertTrue(equalBoards(game.getBoard(), board2));
	}
	
	@Test
	void testFullBoard() {
		Board board = new Board(5,5, copyGrid(FULL_FIVE_BY_FIVE_GRID));
		Game game = new Game(board);
		
		assertEquals(game.getLivingNeighbourCount(0, 0), 3);
		assertEquals(game.getLivingNeighbourCount(1, 0), 5);
		assertEquals(game.getLivingNeighbourCount(1, 1), 8);
		
		assertEquals(game.getLivingNeighbourCount(5, -1), 1);
		assertEquals(game.getLivingNeighbourCount(5, 0), 2);
		assertEquals(game.getLivingNeighbourCount(5, 1), 3);
		assertEquals(game.getLivingNeighbourCount(6, 0), 0);
		
		game.nextGeneration();
		
		assertTrue(equalBoards(game.getBoard(), new Board(5,5, FULL_GEN1_FIVE_BY_FIVE_GRID)));
	}
	
	@Test
	void testGlider() {
		Board board = new Board(5,5, copyGrid(GLIDER_FIVE_BY_FIVE_GRID));
		Game game = new Game(board);
		
		for(int i=0; i<8; i++) {
			game.nextGeneration();
		}
		
		assertTrue(equalBoards(game.getBoard(), new Board(5,5, GLIDER_GEN8_FIVE_BY_FIVE_GRID)));
	}
	
	private boolean equalBoards(Board board1, Board board2) {
		
		if(board1.getHeight() != board2.getHeight() || board1.getWidth() != board2.getWidth()) {
			return false;
		}
		
		for(int i=0; i<board1.getHeight(); i++) {
			for(int j=0; j<board1.getWidth(); j++) {
				if(board1.getGrid()[i][j] != board2.getGrid()[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean[][] copyGrid(boolean[][] grid){
		return Arrays.stream(grid).map(boolean[]::clone).toArray(boolean[][]::new);
	}

}
