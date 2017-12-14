package com.mountainbranch;

import java.util.LinkedList;
import java.util.List;

public class Dec10 {
	private static final int LIST_SIZE = 256;
	
	private final int[] list = new int[LIST_SIZE];
	
	public Dec10() {
		for (int i = 0; i < list.length; i++) {
			list[i] = i;
		}
	}
	
	public int hash(String input) {
		int skipSize = 0;
		int index = 0;
		
		for (String lengthStr : input.split(",")) {
			int length = Integer.parseInt(lengthStr);
			
			// Do the reversing
			int frontIndex = index;
			int backIndex = index+length-1;
			while (frontIndex < backIndex) {
				// Change values
				int valueAtFrontIndex = list[frontIndex%LIST_SIZE];
				list[frontIndex%LIST_SIZE] = list[backIndex%LIST_SIZE];
				list[backIndex%LIST_SIZE] = valueAtFrontIndex;
				
				frontIndex++;
				backIndex--;
			}
			
			index = (index+length+skipSize) % LIST_SIZE;
			skipSize++;
		}
		
		return list[0] * list[1];
	}
	
	public String hash2(String input) {
		int skipSize = 0;
		int index = 0;

		List<Character> chars = new LinkedList<Character>();
		for (char c : input.toCharArray()) {
			chars.add(c);
		}
		chars.add((char) 17);
		chars.add((char) 31);
		chars.add((char) 73);
		chars.add((char) 47);
		chars.add((char) 23);
		
		for (int i = 0; i < 64; i++) {
			for (Character c : chars) {
				int length = c;

				// Do the reversing
				int frontIndex = index;
				int backIndex = index+length-1;
				while (frontIndex < backIndex) {
					// Change values
					int valueAtFrontIndex = list[frontIndex%LIST_SIZE];
					list[frontIndex%LIST_SIZE] = list[backIndex%LIST_SIZE];
					list[backIndex%LIST_SIZE] = valueAtFrontIndex;

					frontIndex++;
					backIndex--;
				}

				index = (index+length+skipSize) % LIST_SIZE;
				skipSize++;
			}
		}
		
		// Convert sparse hash (256 numbers) into dense hash (16 numbers),
		// by taking the XOR of each block of 16 numbers in the sparse hash.
		int[] denseHash = new int[16];
		for (int i = 0; i < 16; i++) {
			int xor = 0;
			for (int k = i*16; k < (i+1)*16; k++) {
				xor ^= list[k];
			}
			denseHash[i] = xor;
		}

		// Output dense hash as a hex string (each number padded to 2 characters)
		String hash = "";
		for (int i : denseHash) {
			String intAsHex = Integer.toHexString(i);
			if (intAsHex.length() < 2) {
				intAsHex = "0" + intAsHex;
			}
			hash += intAsHex;
		}
		return hash;
	}
}
