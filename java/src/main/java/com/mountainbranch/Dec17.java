package com.mountainbranch;

import java.util.ArrayList;
import java.util.List;

public class Dec17 {
	private final int stepSize;
	private final int numberOfIterations;
	private final List<Integer> buffer = new ArrayList<Integer>();
	
	public Dec17(int stepSize, int numberOfIterations) {
		this.stepSize = stepSize;
		this.numberOfIterations = numberOfIterations;
	}
	
	public int getValueAfterLastInsertion() {
		if (buffer.size() == 0) {
			populateBuffer();
		}
		Integer lastInsertedValue = buffer.size() - 1;
		int index = getNewPositionInBuffer(buffer.indexOf(lastInsertedValue), 1, buffer.size());
		return buffer.get(index);
	}

	/**
	 * Returns the value after 0, when the buffer has been populated
	 * (for performance reasons, the buffer is not actually populated).
	 */
	public int getValueAfter0() {
		final int indexOf0 = 0;
		int cursorPosition = 0;
		int valueAfter0 = 0;
		int bufferSize = 1;
		for (int i = 1; i < numberOfIterations; i++) {
			cursorPosition = getNewPositionInBuffer(cursorPosition, stepSize, bufferSize) + 1;
			if (cursorPosition == indexOf0 + 1) {
				valueAfter0 = i;
			}
			bufferSize++;
		}
		return valueAfter0;
	}

	private void populateBuffer() {
		buffer.add((Integer) 0);
		int cursorPosition = 0;
		for (int i = 1; i <= numberOfIterations; i++) {
			cursorPosition = getNewPositionInBuffer(cursorPosition, stepSize, buffer.size()) + 1;
			buffer.add(cursorPosition, (Integer) i);
		}
	}
	
	private int getNewPositionInBuffer(int oldPosition, int delta, int bufferSize) {
		return (oldPosition+delta) % bufferSize;
	}
}
