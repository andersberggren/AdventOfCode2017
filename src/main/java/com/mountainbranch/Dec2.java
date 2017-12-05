package com.mountainbranch;

public class Dec2 {
	public int calcChecksum1(String[] input) {
		// The checksum of a line is the difference between the largest and smallest number
		LineChecksumCalculator lcc = new LineChecksumCalculator() {
			@Override
			public int calculateLineChecksum(int[] values) {
				int min = 0;
				int max = 0;
				for (int i = 0; i < values.length; i++) {
					int value = values[i];
					min = Math.min(value, i==0 ? value : min);
					max = Math.max(value, i==0 ? value : max);
				}
				return max - min;
			}
		};
		return calcChecksum(input, lcc);
	}

	public int calcChecksum2(String[] input) {
		// The checksum of a line is the quotient of the only two evenly divisible numbers
		LineChecksumCalculator lcc = new LineChecksumCalculator() {
			@Override
			public int calculateLineChecksum(int[] values) {
				for (int i = 0; i < values.length; i++) {
					for (int k = i+1; k < values.length; k++) {
						int a = values[i];
						int b = values[k];
						if (a % b == 0)
							return a / b;
						else if (b % a == 0)
							return b / a;
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
			int[] values = new int[words.length];
			for (int i = 0; i < values.length; i++)
				values[i] = Integer.parseInt(words[i]);
			checksum += lcc.calculateLineChecksum(values);
		}
		return checksum;
	}
	
	private static interface LineChecksumCalculator {
		public int calculateLineChecksum(int[] values);
	}
}
