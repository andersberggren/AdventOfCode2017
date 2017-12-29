package com.mountainbranch;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Dec22 {
	private final Set<Point> infectedNodes = new HashSet<Point>();
	private final Virus virus;
	
	public Dec22(String[] input) {
		int maxX = 0;
		for (int y = 0; y < input.length; y++) {
			for (int x = 0; x < input[y].length(); x++) {
				maxX = Math.max(maxX, x);
				if (input[y].charAt(x) == '#') {
					infectedNodes.add(new Point(x, y));
				}
			}
		}
		virus = new Virus(maxX/2, input.length/2);
	}
	
	public int unleashVirus(int nrOfBursts) {
		int nrInfections = 0;
		int i = 0;
		while (i < nrOfBursts) {
			if (virus.doBurst()) {
				nrInfections++;
			}
			i++;
		}
		return nrInfections;
	}
	
	private class Virus {
		public final Point location;
		public final Point direction = new Point(0, -1);
		
		public Virus(int x, int y) {
			location = new Point(x, y);
		}
		
		public boolean doBurst() {
			boolean nodeInfected = false;
			
			if (infectedNodes.contains(location)) {
				// Turn right
				int oldX = direction.x;
				direction.x = -direction.y;
				direction.y = oldX;
				
				infectedNodes.remove(location);
			} else {
				// Turn left
				int oldX = direction.x;
				direction.x = direction.y;
				direction.y = -oldX;
				
				infectedNodes.add(new Point(location));
				nodeInfected = true;
			}
			
			location.translate(direction.x, direction.y);
			return nodeInfected;
		}
	}
}
