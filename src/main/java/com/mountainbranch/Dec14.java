package com.mountainbranch;

import java.util.HashMap;
import java.util.Map;

public class Dec14 {
	private final static int NUMBER_OF_ROWS = 128;
	
	private Map<Character, Integer> hexDigitToNumberOfZeroes = new HashMap<Character, Integer>();
	
	public Dec14() {
		hexDigitToNumberOfZeroes.put((Character) '0', (Integer) 0);
		hexDigitToNumberOfZeroes.put((Character) '1', (Integer) 1);
		hexDigitToNumberOfZeroes.put((Character) '2', (Integer) 1);
		hexDigitToNumberOfZeroes.put((Character) '3', (Integer) 2);
		hexDigitToNumberOfZeroes.put((Character) '4', (Integer) 1);
		hexDigitToNumberOfZeroes.put((Character) '5', (Integer) 2);
		hexDigitToNumberOfZeroes.put((Character) '6', (Integer) 2);
		hexDigitToNumberOfZeroes.put((Character) '7', (Integer) 3);
		hexDigitToNumberOfZeroes.put((Character) '8', (Integer) 1);
		hexDigitToNumberOfZeroes.put((Character) '9', (Integer) 2);
		hexDigitToNumberOfZeroes.put((Character) 'a', (Integer) 2);
		hexDigitToNumberOfZeroes.put((Character) 'b', (Integer) 3);
		hexDigitToNumberOfZeroes.put((Character) 'c', (Integer) 2);
		hexDigitToNumberOfZeroes.put((Character) 'd', (Integer) 3);
		hexDigitToNumberOfZeroes.put((Character) 'e', (Integer) 3);
		hexDigitToNumberOfZeroes.put((Character) 'f', (Integer) 4);
	}
	
	public int getNumberOfUsedSquares(String input) {
		int numberOfUsedSquares = 0;
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			Dec10 dec10 = new Dec10();
			String lineInput = input + "-" + i;
			String hash = dec10.hash2(lineInput);
			for (Character c : hash.toCharArray()) {
				numberOfUsedSquares += hexDigitToNumberOfZeroes.get(c);
			}
		}
		return numberOfUsedSquares;
	}
}
