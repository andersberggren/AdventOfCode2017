package com.mountainbranch;

import java.util.LinkedList;
import java.util.List;

public class Dec6 {
	public static int memoryReallocation1(String input) {
		return getConfigurationsUntilRepeat(input).size()-1;
	}
	
	public static int memoryReallocation2(String input) {
		List<String> configurations = getConfigurationsUntilRepeat(input);
		int lastIndex = configurations.size()-1;
		String lastConfiguration = configurations.get(lastIndex);
		return lastIndex - configurations.indexOf(lastConfiguration);
	}
	
	private static List<String> getConfigurationsUntilRepeat(String input) {
		String[] inputArray = input.split("[\t ]+");
		int[] memoryBanks = new int[inputArray.length];
		for (int i = 0; i < memoryBanks.length; i++) {
			memoryBanks[i] = Integer.parseInt(inputArray[i]);
		}
		List<String> previousConfigurations = new LinkedList<String>();
		previousConfigurations.add(memoryBanksAsString(memoryBanks));
		
		while (true) {
			// Find largest memory bank
			int index = 0;
			int maxSize = 0;
			for (int i = 0; i < memoryBanks.length; i++) {
				if (memoryBanks[i] > maxSize) {
					index = i;
					maxSize = memoryBanks[i];
				}
			}
			
			// Reallocate
			int blocksToRedistribute = memoryBanks[index];
			memoryBanks[index] = 0;
			int blocksToEveryIndex = blocksToRedistribute / memoryBanks.length;
			for (int i = 0; i < memoryBanks.length; i++) {
				memoryBanks[i] += blocksToEveryIndex;
			}
			blocksToRedistribute = blocksToRedistribute % memoryBanks.length;
			while (blocksToRedistribute > 0) {
				index = (index+1) % memoryBanks.length;
				memoryBanks[index]++;
				blocksToRedistribute--;
			}
			
			// If configuration has been seen before, return size of infinite loop
			String str = memoryBanksAsString(memoryBanks);
			index = previousConfigurations.indexOf(str);
			previousConfigurations.add(str);
			if (index != -1) {
				return previousConfigurations;
			}
		}
	}

	private static String memoryBanksAsString(int[] memoryBanks) {
		StringBuilder sb = new StringBuilder();
		for (int memoryBank : memoryBanks) {
			sb.append(memoryBank).append(";");
		}
		return sb.toString();
	}
}
