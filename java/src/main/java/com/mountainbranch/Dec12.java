package com.mountainbranch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dec12 {
	/** Map from a program to a list of all the programs it has a direct connection to. */
	private Map<Integer, List<Integer>> connections = new HashMap<Integer, List<Integer>>();
	
	public Dec12(String[] input) {
		for (String line : input) {
			String[] words = line.split("[ \t,]+");
			Integer program = Integer.parseInt(words[0]);
			List<Integer> connectionsForThisProgram = new LinkedList<Integer>();
			for (int i = 2; i < words.length; i++) {
				connectionsForThisProgram.add((Integer) Integer.parseInt(words[i]));
			}
			connections.put(program, connectionsForThisProgram);
		}
	}
	
	/** Returns the number of isolated groups among all programs. */
	public int getNumberOfGroups() {
		int numberOfGroups = 0;
		// Initially, unhandledPrograms contains all programs
		Set<Integer> unhandledPrograms = new HashSet<Integer>(connections.keySet());
		while (!unhandledPrograms.isEmpty()) {
			// Pick any program in unhandledPrograms, find all programs in its group,
			// and remove them all from unhandledPrograms.
			unhandledPrograms.removeAll(getGroupContaining(unhandledPrograms.iterator().next()));
			numberOfGroups++;
		}
		return numberOfGroups;
	}
	
	/** Returns a {@link java.util.Set} of the programs connected
	 *  (directly or indirectly) to {@code program}. */
	public Set<Integer> getGroupContaining(Integer program) {
		Set<Integer> group = new HashSet<Integer>();
		Set<Integer> unhandledPrograms = new HashSet<Integer>();
		unhandledPrograms.add(program);
		while (!unhandledPrograms.isEmpty()) {
			Integer thisProgram = unhandledPrograms.iterator().next();
			unhandledPrograms.remove(thisProgram);
			if (!group.contains(thisProgram)) {
				group.add(thisProgram);
				for (Integer connectedProgram : connections.get(thisProgram)) {
					unhandledPrograms.add(connectedProgram);
				}
			}
		}
		return group;
	}
}
