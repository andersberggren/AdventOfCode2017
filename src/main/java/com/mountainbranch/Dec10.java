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
		List<Integer> lengths = new LinkedList<Integer>();
		for (char c : input.toCharArray()) {
			lengths.add((int) c);
		}
		for (int i : new int[]{17, 31, 73, 47, 23}) {
			lengths.add(i);
		}
		
		for (int i = 0; i < 64; i++) {
			for (Integer length : lengths) {
				reverseListSection(length);
			}
		}
		
		// Convert sparse hash (256 numbers) into dense hash (16 numbers),
		// by taking the XOR of each block of 16 numbers in the sparse hash.
		int[] denseHash = new int[16];
		for (int i = 0; i < LIST_SIZE; i++) {
			denseHash[i/16] ^= list[i];
		}

		// Output dense hash as a hex string (each number padded to 2 characters)
		String hash = "";
		for (int i : denseHash) {
			hash += String.format("%02x", i);
		}
		return hash;
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
		currentIndex = (currentIndex + length + skipSize++) % LIST_SIZE;
	}
}
