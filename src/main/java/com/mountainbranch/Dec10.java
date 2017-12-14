package com.mountainbranch;

import java.util.LinkedList;
import java.util.List;

public class Dec10 {
	private static final int LIST_SIZE = 256;
	private final int[] list = new int[LIST_SIZE];
	private int currentIndex = 0;
	private int skipSize = 0;
	
	public Dec10() {
		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}
	}
	
	public int hash1(String input) {
		for (String lengthStr : input.split(",")) {
			reverseListSection(Integer.parseInt(lengthStr));
		}
		return list[0] * list[1];
	}
	
	public String hash2(String input) {
		// Convert each character in the input string to its ASCII value.
		List<Integer> lengths = new LinkedList<Integer>();
		for (char c : input.toCharArray()) {
			lengths.add((int) c);
		}
		// Always append these values at the end
		for (int i : new int[]{17, 31, 73, 47, 23}) {
			lengths.add(i);
		}
		
		// Do hash similar to hash1, but do it 64 times,
		// keeping the index and skip size between rounds.
		for (int i = 0; i < 64; i++) {
			for (Integer length : lengths) {
				reverseListSection(length);
			}
		}
		
		// Convert sparse hash (256 numbers) into dense hash (16 numbers),
		// by taking the XOR of each block of 16 numbers in the sparse hash.
		// The hash string is the hex value of every number in the dense hash
		// (each number padded to 2 characters).
		final int NUMBER_OF_BLOCKS = 16;
		final int NUMBERS_PER_BLOCK = 16;
		String hashStr = "";
		for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
			int hash = 0;
			for (int k = i*NUMBERS_PER_BLOCK; k < (i+1)*NUMBERS_PER_BLOCK; k++) {
				hash ^= list[k];
			}
			hashStr += String.format("%02x", hash);
		}
		return hashStr;
	}
	
	private void reverseListSection(int length) {
		int frontIndex = currentIndex;
		int backIndex = currentIndex+length-1;
		while (frontIndex < backIndex) {
			int valueAtFrontIndex = list[frontIndex%LIST_SIZE];
			list[frontIndex%LIST_SIZE] = list[backIndex%LIST_SIZE];
			list[backIndex%LIST_SIZE] = valueAtFrontIndex;
			frontIndex++;
			backIndex--;
		}
		// Update currentIndex, and increment skipSize
		currentIndex = (currentIndex + length + skipSize++) % LIST_SIZE;
	}
}
