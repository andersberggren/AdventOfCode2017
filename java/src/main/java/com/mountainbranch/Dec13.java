package com.mountainbranch;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Dec13 {
	private Map<Integer, Integer> scanners = new HashMap<Integer, Integer>();

	public Dec13(String[] input) {
		for (String line : input) {
			String[] words = line.split(": ");
			scanners.put(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
		}
	}
	
	/** Returns the total severity of a packet starting at time 0. */
	public int getTotalSeverity() {
		return getTotalSeverity(0).totalSeverity;
	}

	/** Returns the minimum delay necessary for a packet to pass without being caught. */
	public int getDelayToPassWithoutBeingCaught() {
		int delay = 0;
		while (true) {
			if (!getTotalSeverity(delay).caught) {
				return delay;
			}
			delay++;
		}
	}
	
	private Result getTotalSeverity(int startDelay) {
		int totalSeverity = 0;
		boolean caught = false;
		
		// For each scanner, check if the scanner catches the packet. If so, add severity.
		for (Entry<Integer, Integer> entry : scanners.entrySet()) {
			Integer layer = entry.getKey();
			Integer depth = entry.getValue();
			int timeEnteringLayer = startDelay+layer;
			if (timeEnteringLayer % ((depth-1)*2) == 0) {
				totalSeverity += layer*depth;
				caught = true;
			}
		}
		
		return new Result(totalSeverity, caught);
	}
	
	private class Result {
		public final int totalSeverity;
		public final boolean caught;
		
		public Result(int totalSeverity, boolean caught) {
			this.totalSeverity = totalSeverity;
			this.caught = caught;
		}
	}
}
