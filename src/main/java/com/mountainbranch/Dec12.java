package com.mountainbranch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dec12 {
	/** Map from programId to a list of all the programIds it has a direct connection to. */
	private Map<Integer, List<Integer>> connections = new HashMap<Integer, List<Integer>>();
	
	public void readConnections(String[] input) {
		for (String line : input) {
			String[] words = line.split("[ \t,]+");
			Integer connectionFrom = Integer.parseInt(words[0]);
			List<Integer> connectionsForThisProgram = new LinkedList<Integer>();
			for (int i = 2; i < words.length; i++) {
				connectionsForThisProgram.add((Integer) Integer.parseInt(words[i]));
			}
			connections.put(connectionFrom, connectionsForThisProgram);
		}
	}
	
	/** Returns the number of disjoint groups among all programs. */
	public int getNumberOfGroups() {
		int numberOfGroups = 0;
		Set<Integer> unhandledPrograms = new HashSet<Integer>(connections.keySet());
		while (!unhandledPrograms.isEmpty()) {
			// Find all programs in the group containing programId,
			// then remove them all from unhandledPrograms.
			Integer programId = unhandledPrograms.iterator().next();
			Set<Integer> connectedPrograms = new HashSet<Integer>();
			addAllConnectedPrograms(programId, connectedPrograms);
			numberOfGroups++;
			unhandledPrograms.removeAll(connectedPrograms);
		}
		return numberOfGroups;
	}
	
	/** Returns the size of the group of connected programs containing {@code programId}. */
	public int getNumberOfConnectionsInGroup(int programId) {
		Set<Integer> connectedPrograms = new HashSet<Integer>();
		addAllConnectedPrograms(programId, connectedPrograms);
		return connectedPrograms.size();
	}
	
	/** Adds {@code programId}, and all other programIds it's connected to,
	 *  to {@code connectedPrograms}.
	 */
	private void addAllConnectedPrograms(Integer programId, Set<Integer> connectedPrograms) {
		// To prevent infinite recursion, don't do anything programId is already
		// in connectedPrograms (that means we've already handled it).
		if (connectedPrograms.add(programId)) {
			// The set did not already contain programId.
			// Call method for all programs directly connected to programId.
			for (Integer connectedProgramId : connections.get(programId)) {
				addAllConnectedPrograms(connectedProgramId, connectedPrograms);
			}
		}
	}
}
