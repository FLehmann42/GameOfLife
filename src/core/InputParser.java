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
				
				boolean heightSet = false;
				boolean widthSet = false;
				
				for(int i = 0; i<dimensionLine.length(); i++) {
					
					int asciiValue = (int) dimensionLine.charAt(i);
					if(asciiValue >= 48 && asciiValue <=57) {
						if(heightSet && !widthSet) {
							width = Character.getNumericValue(asciiValue);
							widthSet = true;
						} else {
							height = Character.getNumericValue(asciiValue);
							heightSet = true;
						}
					}
				}
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
}
