package com.mountainbranch;

import java.awt.Point;

public class Dec19 {
	private char[][] grid;
	private final int width;
	private final int height;
	
	public Dec19(String[] input) {
		width = input[0].length();
		height = input.length;
		grid = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y] = input[y].charAt(x);
			}
		}
	}
	
	public String findLetters() {
		String letters = "";
		Point cursor = null;
		Point direction = new Point(0, 1);
		
		// Find starting point
		for (int x = 0; x < width; x++) {
			if (grid[x][0] == '|') {
				cursor = new Point(x, 0);
				break;
			}
		}
		
		while (step(cursor, direction)) {
			char c = grid[cursor.x][cursor.y];
			if (c >= 'A' && c <= 'Z') {
				letters += c;
			}
		}
		return letters;
	}

	private boolean step(Point cursor, Point direction) {
		Point[] directions = new Point[]{
				// "Forward" (relative to the direction we're currently facing)
				direction,
				// Right
				new Point(-direction.y, direction.x),
				// Left
				new Point(direction.y, -direction.x)
		};
		for (Point newDirection : directions) {
			Point newCursorLocation = new Point(cursor.x + newDirection.x, cursor.y + newDirection.y);
			if (isValidLocation(newCursorLocation)) {
				char c = grid[newCursorLocation.x][newCursorLocation.y];
				if (c == '|' || c == '-' || c == '+' || (c >= 'A' && c <= 'Z')) {
					cursor.setLocation(newCursorLocation);
					direction.setLocation(newDirection);
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isValidLocation(Point location) {
		return location.x >= 0 && location.x < width && location.y >= 0 && location.y < height;
	}
}
