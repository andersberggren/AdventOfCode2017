package com.mountainbranch;

public class Dec14 {
	private final static int NUMBER_OF_ROWS = 128;
	private final static int NUMBER_OF_COLS = 128;
	
	public int getNumberOfUsedSquares(String input) {
		int numberOfUsedSquares = 0;
		Integer[][] grid = stringToGrid(input);
		for (int x = 0; x < NUMBER_OF_COLS; x++) {
			for (int y = 0; y < NUMBER_OF_COLS; y++) {
				if (grid[x][y] != null) {
					numberOfUsedSquares++;
				}
			}
		}
		return numberOfUsedSquares;
	}
	
	public int getNumberOfRegions(String input) {
		int numberOfRegions = 0;
		Integer[][] grid = stringToGrid(input);
		for (int x = 0; x < NUMBER_OF_COLS; x++) {
			for (int y = 0; y < NUMBER_OF_COLS; y++) {
				if (grid[x][y] == (Integer) 0) {
					setRegion(grid, x, y, ++numberOfRegions);
				}
			}
		}
		return numberOfRegions;
	}
	
	/**
	 * Converts {@code input} to an Integer[][] (grid).
	 * A value of 0 means the square is used.
	 * A value of null means the square is free.
	 */
	private Integer[][] stringToGrid(String input) {
		Integer[][] grid = new Integer[NUMBER_OF_COLS][NUMBER_OF_ROWS];
		for (int y = 0; y < NUMBER_OF_ROWS; y++) {
			String hash = new Dec10().hash2(input + "-" + y);
			String binaryString = hashToBinaryString(hash);
			for (int x = 0; x < NUMBER_OF_COLS; x++) {
				grid[x][y] = (binaryString.charAt(x) == '1') ? 0 : null;
			}
		}
		return grid;
	}
	
	private String hashToBinaryString(String hash) {
		String binaryString = "";
		for (int i = 0; i < hash.length(); i++) {
			String hexStr = hash.substring(i, i+1);
			String hexCharAsBinaryString = Integer.toBinaryString(Integer.parseInt(hexStr, 16));
			while (hexCharAsBinaryString.length() < 4) {
				hexCharAsBinaryString = "0" + hexCharAsBinaryString;
			}
			binaryString += hexCharAsBinaryString;
		}
		return binaryString;
	}
	
	/**
	 * Sets square (x,y) in grid to regionIndex, if the square is used and
	 * doesn't already have a regionIndex (i.e. the squares value must be 0).
	 * Also makes recursive calls for neighboring squares (up/down/left/right).
	 */
	private void setRegion(Integer[][] grid, int x, int y, int regionIndex) {
		if (x >= 0 && x < NUMBER_OF_COLS && y >= 0 && y < NUMBER_OF_ROWS
				&& grid[x][y] == (Integer) 0) {
			grid[x][y] = regionIndex;
			setRegion(grid, x+1, y,   regionIndex);
			setRegion(grid, x-1, y,   regionIndex);
			setRegion(grid, x,   y+1, regionIndex);
			setRegion(grid, x,   y-1, regionIndex);
		}
	}
}
