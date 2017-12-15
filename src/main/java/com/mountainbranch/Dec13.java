package com.mountainbranch;

import java.util.HashMap;
import java.util.Map;

public class Dec13 {
	private Map<Integer, Scanner> scanners = new HashMap<Integer, Scanner>();
	private int lastScannerLayer = 0;

	public Dec13(String[] input) {
		for (String line : input) {
			String[] words = line.split(": ");
			Integer layer = Integer.parseInt(words[0]);
			int depthOfLayer = Integer.parseInt(words[1]);
			scanners.put(layer, new Scanner(layer, depthOfLayer));
			lastScannerLayer = Math.max(lastScannerLayer, layer);
		}
	}
	
	public int getTotalSeverity() {
		int totalSeverity = 0;
		for (Integer i = 0; i <= lastScannerLayer; i++) {
			// See if caught by scanner
			Scanner scannerAtCurrentLayer = scanners.get(i);
			if (scannerAtCurrentLayer != null && scannerAtCurrentLayer.tryToCatchPacket()) {
				totalSeverity += scannerAtCurrentLayer.severity;
			}
			
			// Move scanners
			for (Scanner scanner : scanners.values()) {
				scanner.move();
			}
		}
		return totalSeverity;
	}
	
	public int getDelayToPassWithoutBeingCaught() {
		int delay = 0;
		while (true) {
			// For each scanner, check if the scanner would catch packet with this delay
			boolean caught = false;
			for (Scanner scanner : scanners.values()) {
				int timeEnteringLayer = delay + scanner.layer;
				if (timeEnteringLayer % ((scanner.depthOfLayer-1)*2) == 0) {
					caught = true;
					break;
				}
			}
			if (caught) {
				delay++;
			} else {
				return delay;
			}
		}
	}
	
	private class Scanner {
		public final int layer;
		public final int depthOfLayer;
		public final int severity;
		public int position = 0;
		public int deltaStep = 1;
		
		public Scanner(int layer, int depthOfLayer) {
			this.layer = layer;
			this.depthOfLayer = depthOfLayer;
			severity = layer * depthOfLayer;
		}
		
		public void move() {
			if (position + deltaStep < 0 || position + deltaStep >= depthOfLayer) {
				deltaStep *= -1;
			}
			position += deltaStep;
		}
		
		public void reset() {
			position = 0;
			deltaStep = 1;
		}
		
		public boolean tryToCatchPacket() {
			return position == 0;
		}
	}
}
