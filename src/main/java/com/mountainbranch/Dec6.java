package com.mountainbranch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dec6 {
	public int solveProblem1(String[] input) {
		int count = 0;
		for (String line : input) {
			if (isValid(line)) {
				count++;
			}
		}
		return count;
	}
	
	private boolean isValid(String line) {
		String[] words = line.split("[\t ]+");
		
		Set<String> wordSet = new HashSet<String>();
		for (String word : words) {
			wordSet.add(word);
		}
		
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		for (String word : words) {
			wordMap.put(word, getValueOfWord(word));
		}
		
		return false;
	}
	
	private Integer getValueOfWord(String word) {
		// TODO Implement
		return 0;
	}
	
	
	
	public int solveProblem2(String[] input) {
		return 0;
	}
}
