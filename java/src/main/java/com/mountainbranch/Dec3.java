package com.mountainbranch;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Dec3 {
	public static int calcDistance(int targetAddress) {
		// Find the smallest (n*2+1) x (n*2+1) grid that contains the target address.
		// The target address will be on the perimeter of that grid.
		int sideLength = 1;
		while (sideLength * sideLength < targetAddress)
			sideLength += 2;

		// Address 1, center, is (0,0).
		// Start at bottom-right, search clockwise around the perimeter,
		// one row/column at a time, until the address is found.
		int distanceFromCenterToEdge = (sideLength-1)/2;
		int addressAtCorner = sideLength*sideLength;
		while (true) {
			addressAtCorner -= (sideLength-1);
			if (addressAtCorner <= targetAddress) {
				return Math.abs((targetAddress-addressAtCorner) - distanceFromCenterToEdge)
						+ distanceFromCenterToEdge;
			}
		}
	}
	
	public static int calcFirstValueLargerThan(int targetValue) {
		// Initial direction: Right
		Point direction = new Point(1, 0);
		Point currentAddress = new Point(0, 0);
		Map<Point, Integer> grid = new HashMap<Point, Integer>();
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;
		
		while (true) {
			// Is value of current address larger than targetValue?
			Integer valueAtAddress = calcValueOfAddress(currentAddress, grid);
			if (valueAtAddress > targetValue)
				return valueAtAddress;
			
			// If not, add value to grid, calculate new position/direction
			grid.put(new Point(currentAddress), valueAtAddress);
			currentAddress.translate(direction.x, direction.y);
			if (currentAddress.x < minX || currentAddress.x > maxX
					|| currentAddress.y < minY || currentAddress.y > maxY) {
				// New address is outside bounds. Update bounds and change direction (counter-clockwise).
				minX = Math.min(minX, currentAddress.x);
				maxX = Math.max(maxX, currentAddress.x);
				minY = Math.min(minY, currentAddress.y);
				maxY = Math.max(maxY, currentAddress.y);
				direction.setLocation(direction.y, -direction.x);
			}
		}
	}
	
	private static int calcValueOfAddress(Point address, Map<Point, Integer> grid) {
		if (address.x == 0 && address.y == 0)
			return 1;
		
		int totalValue = 0;
		for (int x = address.x-1; x <= address.x+1; x++) {
			for (int y = address.y-1; y <= address.y+1; y++) {
				Integer valueOfCurrentAddress = grid.get(new Point(x,y));
				if (valueOfCurrentAddress != null) {
					totalValue += valueOfCurrentAddress;
				}
			}
		}
		return totalValue;
	}
}
