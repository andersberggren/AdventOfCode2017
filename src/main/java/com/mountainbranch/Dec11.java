package com.mountainbranch;

import java.awt.Point;

public class Dec11 {
	public int calcDistance(String moves) {
		Point position = new Point(0, 0);
		for (String move : moves.split(",")) {
			if (move.equals("n")) {
				position.y += 2;
			} else if (move.equals("ne")) {
				position.x += 1;
				position.y += 1;
			} else if (move.equals("se")) {
				position.x += 1;
				position.y -= 1;
			} else if (move.equals("s")) {
				position.y -= 2;
			} else if (move.equals("sw")) {
				position.x -= 1;
				position.y -= 1;
			} else if (move.equals("nw")) {
				position.x -= 1;
				position.y += 1;
			}
		}
		
		position.x = Math.abs(position.x);
		position.y = Math.abs(position.y);
		return position.x + Math.max(position.y-position.x, 0)/2;
	}
}
