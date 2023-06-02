package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputParser {
	
	static char DEAD_CELL = '.';
	static char ALIVE_CELL = '*';
	
	/*	Reads the text file at the given path
	 * 
	 * 	File has to have the following formatting:
	 * 	
	 * 	
	 * 	2. Line: <height> <width>
	 * 	3. Line and following: <the grid, with . representing dead cells and * representing alive cells>
	 */
	public Board parseInput(String path) {
		
		File file = new File(path);
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			return null;
		}
		
		try {
			
			int height = 0;
			int width = 0;
			
			
			
			reader.readLine(); //Skip the first line
			
			String dimensionLine = reader.readLine(); 
			
			if(dimensionLine != null) {
				
				String[] dimensions = dimensionLine.split(" ");
				
				if(!isInteger(dimensions[0]) || !isInteger(dimensions[1])) {
					reader.close();
					return null;
				}
				
				height = Integer.parseInt(dimensions[0]);
				width = Integer.parseInt(dimensions[1]);
				
				System.out.println(height + " " + width);
			}
			
			boolean[][] grid = new boolean[height][width];
			
			String line;
			
			for(int i = 0; i < height; i++) {
				
				line = reader.readLine();
				
				if(line == null) {
					break;
				} else {
					for(int j = 0; j < line.length(); j++) {
						if(line.charAt(j) == DEAD_CELL) {
							grid[i][j] = false;
						} else if (line.charAt(j) == ALIVE_CELL) {
							grid[i][j] = true;
						}
					}
				}
			}
			
			reader.close();
			
			return new Board(height, width, grid);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
				
	}
	
	private boolean isInteger(String string) {
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) < 48 || string.charAt(i) > 57) {
				return false;
			}
		}
		
		return true;
	}
}
