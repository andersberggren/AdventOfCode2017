package com.mountainbranch;

import java.util.HashSet;
import java.util.Set;

public class Dec16 {
	private String programs = "abcdefghijklmnop";
	
	public String dance(String input) {
		for (String instruction : input.split(",")) {
			char typeOfInstruction = instruction.charAt(0);
			String arg = instruction.substring(1);
			switch (typeOfInstruction) {
			case 's':
				spin(Integer.parseInt(arg));
				break;
			case 'x':
				String[] indices = arg.split("/");
				exchange(Integer.parseInt(indices[0]), Integer.parseInt(indices[1]));
				break;
			case 'p':
				String[] programs = arg.split("/");
				partner(programs[0].charAt(0), programs[1].charAt(0));
				break;
			}
		}
		return programs;
	}
	
	public String billionDances(String input) {
		int numberOfDancesLeft = 1000000000;
		
		// Do the dance until we find a repeating permutation
		Set<String> permutations = new HashSet<String>();
		do {
			dance(input);
			numberOfDancesLeft--;
		} while (permutations.add(programs));
		
		// The permutations repeat every x times, so we can subtract a multiple of that from
		// the number of remaining dances since it would result in the same permutation.
		numberOfDancesLeft = numberOfDancesLeft % permutations.size();
		while (numberOfDancesLeft > 0) {
			dance(input);
			numberOfDancesLeft--;
		}
		return programs;
	}
	
	private void spin(int numberOfProgramsToTheFront) {
		int indexOfNewFirst = programs.length() - numberOfProgramsToTheFront;
		programs = programs.substring(indexOfNewFirst) + programs.substring(0, indexOfNewFirst);
	}
	
	private void exchange(int positionA, int positionB) {
		partner(programs.charAt(positionA), programs.charAt(positionB));
	}
	
	private void partner(char programA, char programB) {
		char temp = '.';
		programs = programs.replace(programA, temp);
		programs = programs.replace(programB, programA);
		programs = programs.replace(temp, programB);
	}
}
