package com.mountainbranch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dec12 {
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
	
	public int getNumberOfConnectionsInGroup(int programId) {
		Set<Integer> connectedPrograms = new HashSet<Integer>();
		addAllConnectedPrograms(programId, connectedPrograms);
		return connectedPrograms.size();
	}
	
	private void addAllConnectedPrograms(Integer programId, Set<Integer> connectedPrograms) {
		if (connectedPrograms.add(programId)) {
			// The set did not already contain programId.
			// Call method for all programs directly connected to programId.
			for (Integer connectedProgramId : connections.get(programId)) {
				addAllConnectedPrograms(connectedProgramId, connectedPrograms);
			}
		}
	}
}
