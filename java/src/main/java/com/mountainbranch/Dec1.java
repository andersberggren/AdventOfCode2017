package com.mountainbranch;

public class Dec1 {
	public static int calculateSum1(String input) {
		OtherIndexGetter oig = new OtherIndexGetter() {
			@Override
			public int getNextIndex(int currentIndex, int listSize) {
				return (currentIndex+1) % listSize;
			}
		};
		return calculateSum(input, oig);
	}

	public static int calculateSum2(String input) {
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

	private static int calculateSum(String input, OtherIndexGetter oig) {
		int sum = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == input.charAt(oig.getNextIndex(i, input.length()))) {
				sum += Integer.parseInt(input.substring(i, i+1));
			}
		}
		return sum;
	}

	private static interface OtherIndexGetter {
		public int getNextIndex(int currentIndex, int listSize);
	}
}
