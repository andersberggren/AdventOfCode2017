package com.mountainbranch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dec7 {
	private Map<String, Disc> tower = new HashMap<String, Disc>();

	public Dec7(String[] input) {
		// Build tower
		for (String line : input) {
			String[] words = line.split("[\t ]+");
			String name = words[0];
			int ownWeight = Integer.parseInt(words[1].replace("(", "").replace(")", ""));
			Disc disc = new Disc(name, ownWeight);
			for (int i = 3; i < words.length; i++) {
				String supportedDisc = words[i].replace(",", "");
				disc.supportedDiscs.add(supportedDisc);
			}
			tower.put(name, disc);
		}

		// Calculate total weight of each disc and its supported discs
		for (Disc disc : tower.values()) {
			if (disc.totalWeight == null) {
				disc.totalWeight = getTotalWeight(disc);
			}
		}
	}
	
	public String findBottomProgram() {
		Set<String> allSupportedDiscs = new HashSet<String>();
		
		for (Disc disc : tower.values()) {
			allSupportedDiscs.addAll(disc.supportedDiscs);
		}
		
		for (String disc : tower.keySet()) {
			if (!allSupportedDiscs.contains(disc)) {
				return disc;
			}
		}
		
		return "";
	}
	
	public void findUnbalancedProgram() {
		for (Disc disc : tower.values()) {
			boolean ok = true;
			boolean first = true;
			int weight = 0;
			for (String name : disc.supportedDiscs) {
				Disc supportedDisc = tower.get(name);
				if (first) {
					weight = supportedDisc.totalWeight;
					first = false;
				}
				if (supportedDisc.totalWeight != weight) {
					ok = false;
					break;
				}
			}
			
			if (!ok) {
				System.out.println("Weights of " + disc.name + " are not equal!");
				for (String name : disc.supportedDiscs) {
					Disc supportedDisc = tower.get(name);
					System.out.println(supportedDisc.name + ", " + supportedDisc.ownWeight + "/" + supportedDisc.totalWeight);
				}
			}
		}
	}
	
	private int getTotalWeight(Disc disc) {
		int totalWeight = disc.ownWeight;
		for (String supportedDisc : disc.supportedDiscs) {
			totalWeight += getTotalWeight(tower.get(supportedDisc));
		}
		disc.totalWeight = totalWeight;
		return totalWeight;
	}

	private static class Disc {
		public final String name;
		public final List<String> supportedDiscs = new LinkedList<String>();
		public final int ownWeight;
		public Integer totalWeight;
		
		public Disc(String name, int ownWeight) {
			this.name = name;
			this.ownWeight = ownWeight;
		}
	}
}
