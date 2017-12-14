package com.mountainbranch;

import java.awt.Point;

public class Dec11 {
	private Point position = new Point(0, 0);
	private int maxDistanceFromOrigin = 0;
	
	public void doMoves(String moves) {
		// Position on the hex grid is represented by an (x,y)-position.
		// Moving north/south means a movement 2 units in the y-direction.
		// Moving northeast/southeast/southwest/northwest means a movement
		// 1 unit in the x-direction and 1 unit in the y-direction.
		for (String move : moves.split(",")) {
			if (move.equals("n")) {
				position.translate(0, 2);
			} else if (move.equals("ne")) {
				position.translate(1, 1);
			} else if (move.equals("se")) {
				position.translate(1, -1);
			} else if (move.equals("s")) {
				position.translate(0, -2);
			} else if (move.equals("sw")) {
				position.translate(-1, -1);
			} else if (move.equals("nw")) {
				position.translate(-1, 1);
			}
			maxDistanceFromOrigin = Math.max(maxDistanceFromOrigin, getDistanceFromOrigin());
		}
	}
	
	public int getDistanceFromOrigin() {
		int distanceX = Math.abs(position.x);
		int distanceY = Math.abs(position.y);
		return distanceX + Math.max(distanceY-distanceX, 0)/2;
	}
	
	public int getMaxDistanceFromOrigin() {
		return maxDistanceFromOrigin;
	}
}
