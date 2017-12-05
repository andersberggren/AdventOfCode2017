package com.mountainbranch;

public class Dec1 {
	public int calculateSum1(String input) {
		OtherIndexGetter oig = new OtherIndexGetter() {
			@Override
			public int getNextIndex(int currentIndex, int listSize) {
				return (currentIndex+1) % listSize;
			}
		};
		return calculateSum(input, oig);
	}

	public int calculateSum2(String input) {
		OtherIndexGetter oig = new OtherIndexGetter() {
			@Override
			public int getNextIndex(int currentIndex, int listSize) {
				if (listSize % 2 != 0) {
					throw new IllegalArgumentException("listSize is not an even number");
				}
				return (currentIndex+listSize/2) % listSize;
			}
		};
		return calculateSum(input, oig);
	}

	private int calculateSum(String input, OtherIndexGetter oig) {
		String[] digits = input.split("");
		int sum = 0;
		
		for (int i = 0; i < digits.length; i++) {
			int digit1 = Integer.parseInt(digits[i]);
			int digit2 = Integer.parseInt(digits[oig.getNextIndex(i, digits.length)]);
			if (digit1 == digit2) {
				sum += digit1;
			}
		}
		
		return sum;
	}

	private static interface OtherIndexGetter {
		public int getNextIndex(int currentIndex, int listSize);
	}
}
