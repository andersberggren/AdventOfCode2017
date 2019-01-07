package com.mountainbranch;

public class Dec5 {
	public static int mazeOfTwistyTrampolines1(String[] input) {
		NewValueGetter nvg = new NewValueGetter() {
			@Override
			public int getNewValue(int oldValue) {
				return oldValue + 1;
			}
		};
		return mazeOfTwistyTrampolines(input, nvg);
	}

	public static int mazeOfTwistyTrampolines2(String[] input) {
		NewValueGetter nvg = new NewValueGetter() {
			@Override
			public int getNewValue(int oldValue) {
				return oldValue>=3 ? oldValue-1 : oldValue+1;
			}
		};
		return mazeOfTwistyTrampolines(input, nvg);
	}

	private static int mazeOfTwistyTrampolines(String[] input, NewValueGetter newValueGetter) {
		int currentAddress = 0;
		int steps = 0;
		Integer[] instructions = new Integer[input.length];
		
		while (true) {
			steps++;
			
			// Get jump value at current address
			Integer value = instructions[currentAddress];
			if (value == null)
				value = Integer.parseInt(input[currentAddress]);
			
			// Update jump value at current address, and jump to new address
			instructions[currentAddress] = newValueGetter.getNewValue(value);
			currentAddress += value;
			
			// If outside instruction list, break
			if (currentAddress < 0 || currentAddress >= instructions.length)
				return steps;
		}
	}
	
	private static interface NewValueGetter {
		public int getNewValue(int oldValue);
	}
}
