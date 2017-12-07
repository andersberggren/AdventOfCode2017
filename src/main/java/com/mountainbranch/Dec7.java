package com.mountainbranch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dec7 {
	public String solveProblem1(String[] input) {
		Map<String, List<String>> tower = new HashMap<String, List<String>>();
		Set<String> allSupportedDiscs = new HashSet<String>();
		
		for (String line : input) {
			String[] words = line.split("[\t ]+");
			String disc = words[0];
			// TODO Weight
			
			List<String> supportedDiscs = new LinkedList<String>();
			for (int i = 3; i < words.length; i++) {
				String supportedDisc = words[i].replace(",", "");
				supportedDiscs.add(supportedDisc);
				allSupportedDiscs.add(supportedDisc);
			}
			tower.put(disc, supportedDiscs);
		}
		
		for (String disc : tower.keySet()) {
			if (!allSupportedDiscs.contains(disc)) {
				return disc;
			}
		}
		
		return "";
	}

	public int solveProblem2(String[] input) {
		return 0;
	}

}
