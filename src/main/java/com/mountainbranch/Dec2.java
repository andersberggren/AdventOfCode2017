package com.mountainbranch;

import java.util.Arrays;

public class Dec2 {
	public int calcChecksum1(String[] input) {
		// The checksum of a line is the difference between the largest and smallest number
		LineChecksumCalculator lcc = new LineChecksumCalculator() {
			@Override
			public int calculateLineChecksum(Integer[] values) {
				Arrays.sort(values);
				return values[values.length-1] - values[0];
			}
		};
		return calcChecksum(input, lcc);
	}

	public int calcChecksum2(String[] input) {
		// The checksum of a line is the quotient of the only two evenly divisible numbers
		LineChecksumCalculator lcc = new LineChecksumCalculator() {
			@Override
			public int calculateLineChecksum(Integer[] values) {
				Arrays.sort(values);
				for (int a = 0; a < values.length; a++) {
					for (int b = a+1; b < values.length; b++) {
						if (values[b] % values[a] == 0) {
							return values[b] / values[a];
						}
					}
				}
				throw new IllegalArgumentException("No two numbers are evenly divisible");
			}
		};
		return calcChecksum(input, lcc);
	}

	private int calcChecksum(String[] input, LineChecksumCalculator lcc) {
		int checksum = 0;
		for (String line : input) {
			String[] words = line.split("[\t ]+");
			Integer[] values = new Integer[words.length];
			for (int i = 0; i < values.length; i++)
				values[i] = Integer.parseInt(words[i]);
			checksum += lcc.calculateLineChecksum(values);
		}
		return checksum;
	}
	
	private static interface LineChecksumCalculator {
		public int calculateLineChecksum(Integer[] values);
	}
}
