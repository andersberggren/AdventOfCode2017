package com.mountainbranch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dec8 {
	private Map<String, Integer> registers = new HashMap<String, Integer>();
	private int largestRegisterValueEverHeld = 0;
	
	public void executeInstructions(String[] input) {
		for (String line : input) {
			String[] words = line.split("[ \t]+");
			String register = words[0];
			boolean increase = words[1].equals("inc");
			int delta = Integer.parseInt(words[2]) * (increase ? 1 : -1);
			if (isConditionSatisfied(words[4], words[5], Integer.parseInt(words[6]))) {
				setRegisterValue(register, getRegisterValue(register) + delta);
			}
		}
	}
	
	public int getLargestRegisterValue() {
		Integer[] values = registers.values().toArray(new Integer[registers.values().size()]);
		Arrays.sort(values, null);
		return values[values.length-1];
	}
	
	public int getLargestRegisterValueEverHeld() {
		return largestRegisterValueEverHeld;
	}
	
	private boolean isConditionSatisfied(String register, String comparison, int comparisonValue) {
		int registerValue = getRegisterValue(register);
		return (comparison.equals("!=") && registerValue != comparisonValue)
				|| (comparison.equals(">")  && registerValue >  comparisonValue)
				|| (comparison.equals(">=") && registerValue >= comparisonValue)
				|| (comparison.equals("==") && registerValue == comparisonValue)
				|| (comparison.equals("<=") && registerValue <= comparisonValue)
				|| (comparison.equals("<")  && registerValue <  comparisonValue);
	}
	
	private int getRegisterValue(String register) {
		Integer value = registers.get(register);
		return value == null ? 0 : value;
	}
	
	private void setRegisterValue(String register, Integer value) {
		registers.put(register, value);
		largestRegisterValueEverHeld = Math.max(largestRegisterValueEverHeld, value);
	}
}
