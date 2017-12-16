package com.mountainbranch;

import java.util.HashSet;
import java.util.Set;

public class Dec16 {
	private static final int NUMBER_OF_PROGRAMS = 16;
	private char[] line = new char[NUMBER_OF_PROGRAMS];
	
	public Dec16() {
		for (int i = 0; i < NUMBER_OF_PROGRAMS; i++) {
			line[i] = (char) ('a' + i);
		}
	}
	
	public String dance(String input) {
		for (String action : input.split(",")) {
			switch (action.charAt(0)) {
			case 's':
				spin(Integer.parseInt(action.substring(1)));
				break;
			case 'x':
				String[] indices = action.substring(1).split("/");
				exchange(Integer.parseInt(indices[0]), Integer.parseInt(indices[1]));
				break;
			case 'p':
				String[] programs = action.substring(1).split("/");
				partner(programs[0].charAt(0), programs[1].charAt(0));
				break;
			}
		}
		return lineToString();
	}
	
	public String billionDances(String input) {
		int numberOfDancesLeft = 1000000000;
		
		// Do the dance until we find a repeating permutation
		Set<String> permutations = new HashSet<String>();
		do {
			dance(input);
			numberOfDancesLeft--;
		} while (permutations.add(lineToString()));
		
		// The permutations repeat every x times, so we can subtract a multiple of that from
		// the number of remaining dances since it would result in the same permutation.
		numberOfDancesLeft = numberOfDancesLeft % permutations.size();
		while (numberOfDancesLeft > 0) {
			dance(input);
			numberOfDancesLeft--;
		}
		return lineToString();
	}
	
	private void spin(int numberOfProgramsToTheFront) {
		int indexInOldLine = NUMBER_OF_PROGRAMS - numberOfProgramsToTheFront;
		char[] newLine = new char[NUMBER_OF_PROGRAMS];
		for (int i = 0; i < NUMBER_OF_PROGRAMS; i++) {
			newLine[i] = line[indexInOldLine++ % NUMBER_OF_PROGRAMS];
		}
		line = newLine;
	}
	
	private void exchange(int positionA, int positionB) {
		char programAtPositionA = line[positionA];
		line[positionA] = line[positionB];
		line[positionB] = programAtPositionA;
	}
	
	private void partner(char programA, char programB) {
		int indexOfA = getIndexOfProgram(programA);
		int indexOfB = getIndexOfProgram(programB);
		line[indexOfA] = programB;
		line[indexOfB] = programA;
	}
	
	private int getIndexOfProgram(char program) {
		for (int i = 0; i < NUMBER_OF_PROGRAMS; i++) {
			if (line[i] == program) {
				return i;
			}
		}
		throw new RuntimeException("Couldn't find program " + program + " in line");
	}
	
	private String lineToString() {
		String order = "";
		for (int i = 0; i < NUMBER_OF_PROGRAMS; i++) {
			order += line[i];
		}
		return order;
	}
}
