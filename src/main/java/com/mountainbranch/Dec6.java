package com.mountainbranch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// TODO Generalize the two versions. Inject only the behaviour that differs.
public class Dec6 {
	public int memoryReallocation1(String input) {
		String[] inputArray = input.split("[\t ]+");
		int[] memoryBank = new int[inputArray.length];
		for (int i = 0; i < memoryBank.length; i++) {
			memoryBank[i] = Integer.parseInt(inputArray[i]);
		}
		Set<String> previousConfigurations = new HashSet<String>();
		previousConfigurations.add(memoryBankAsString(memoryBank));
		
		while (true) {
			// Find largest memory bank
			int index = 0;
			int maxSize = 0;
			for (int i = 0; i < memoryBank.length; i++) {
				if (memoryBank[i] > maxSize) {
					index = i;
					maxSize = memoryBank[i];
				}
			}
			
			// Reallocate
			int blocksToRedistribute = memoryBank[index];
			memoryBank[index] = 0;
			int blocksToEveryIndex = blocksToRedistribute / memoryBank.length;
			for (int i = 0; i < memoryBank.length; i++) {
				memoryBank[i] += blocksToEveryIndex;
			}
			blocksToRedistribute = blocksToRedistribute % memoryBank.length;
			while (blocksToRedistribute > 0) {
				index = (index+1) % memoryBank.length;
				memoryBank[index]++;
				blocksToRedistribute--;
			}
			
			// If configuration has been seen before, return number of configurations
			if (!previousConfigurations.add(memoryBankAsString(memoryBank))) {
				return previousConfigurations.size();
			}
		}
	}
	
	public int memoryReallocation2(String input) {
		String[] inputArray = input.split("[\t ]+");
		int[] memoryBank = new int[inputArray.length];
		for (int i = 0; i < memoryBank.length; i++) {
			memoryBank[i] = Integer.parseInt(inputArray[i]);
		}
		List<String> previousConfigurations = new LinkedList<String>();
		previousConfigurations.add(memoryBankAsString(memoryBank));
		
		while (true) {
			// Find largest memory bank
			int index = 0;
			int maxSize = 0;
			for (int i = 0; i < memoryBank.length; i++) {
				if (memoryBank[i] > maxSize) {
					index = i;
					maxSize = memoryBank[i];
				}
			}
			
			// Reallocate
			int blocksToRedistribute = memoryBank[index];
			memoryBank[index] = 0;
			int blocksToEveryIndex = blocksToRedistribute / memoryBank.length;
			for (int i = 0; i < memoryBank.length; i++) {
				memoryBank[i] += blocksToEveryIndex;
			}
			blocksToRedistribute = blocksToRedistribute % memoryBank.length;
			while (blocksToRedistribute > 0) {
				index = (index+1) % memoryBank.length;
				memoryBank[index]++;
				blocksToRedistribute--;
			}
			
			// If configuration has been seen before, return size of infinite loop
			String str = memoryBankAsString(memoryBank);
			index = previousConfigurations.indexOf(str);
			if (index != -1) {
				return previousConfigurations.size() - index;
			}
			previousConfigurations.add(str);
		}
	}

	private String memoryBankAsString(int[] memoryBank) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < memoryBank.length; i++) {
			if (i > 0) {
				sb.append(";");
			}
			sb.append(memoryBank[i]);
		}
		return sb.toString();
	}
}
