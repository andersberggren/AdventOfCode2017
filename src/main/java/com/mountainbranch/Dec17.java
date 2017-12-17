package com.mountainbranch;

import java.util.ArrayList;
import java.util.List;

public class Dec17 {
	private final int stepSize;
	private final int numberOfIterations;
	
	private List<Integer> buffer;
	private int currentPosition = 0;
	
	public Dec17(int stepSize, int numberOfIterations) {
		this.stepSize = stepSize;
		this.numberOfIterations = numberOfIterations;
		buffer = new ArrayList<Integer>();
		buffer.add((Integer) 0);
		populateBuffer();
	}
	
	public int getValueAfterLastInsertion() {
		int position = (currentPosition+1) % buffer.size();
		return buffer.get(position);
	}
	
	private void populateBuffer() {
		for (int i = 1; i <= numberOfIterations; i++) {
			// Move to new position
			currentPosition = (currentPosition+stepSize) % buffer.size();
			
			// Insert value after current position.
			// The position of the new value is now the current position.
			buffer.add(currentPosition+1, (Integer) i);
			currentPosition++;
		}
	}
}
