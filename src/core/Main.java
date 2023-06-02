package core;

public class Main {
	
	static Board board;
	static Game game;
	static GameWindow window;

	public static void main(String[] args) {
		
		InputParser parser = new InputParser();
		
		board = parser.parseInput("./resources/StartGrid.txt");

		game = new Game(board);
		
		boolean[][] newGrid = game.nextGeneration();
		
		window = new GameWindow(game);
	}

}
