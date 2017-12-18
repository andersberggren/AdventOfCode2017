package com.mountainbranch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import dec18.Dec18;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.dec1();
		main.dec2();
		main.dec3();
		main.dec4();
		main.dec5();
		main.dec6();
		//main.dec7();
		main.dec8();
		main.dec9();
		main.dec10();
		main.dec11();
		main.dec12();
		//main.dec13();
		main.dec14();
		//main.dec15();
		main.dec16();
		main.dec17();
		main.dec18();
	}
	
	private String[] resourceToStringArray(String resource) {
		InputStream inputStream = this.getClass().getResourceAsStream(resource);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		List<String> linesAsList = new LinkedList<String>();
		try {
			String line;
			while (true) {
				line = bufferedReader.readLine();
				if (line == null)
					break;
				linesAsList.add(line);
			}
		} catch (IOException e) {
			System.err.println("IOException thrown while reading resource");
			e.printStackTrace();
			return null;
		}
		
		return linesAsList.toArray(new String[0]);
	}
	
	public void dec1() {
		String[] input = resourceToStringArray("/input1");
		System.out.println("Dec 1 (1): " + Dec1.calculateSum1(input[0]));
		System.out.println("Dec 1 (2): " + Dec1.calculateSum2(input[0]));
	}
	
	public void dec2() {
		String[] input = resourceToStringArray("/input2");
		System.out.println("Dec 2 (1): " + Dec2.calcChecksum1(input));
		System.out.println("Dec 2 (2): " + Dec2.calcChecksum2(input));
	}
	
	public void dec3() {
		int inputAddress = 265149;
		System.out.println("Dec 3 (1): " + Dec3.calcDistance(inputAddress));
		System.out.println("Dec 3 (2): " + Dec3.calcFirstValueLargerThan(inputAddress));
	}
	
	public void dec4() {
		String[] input = resourceToStringArray("/input4");
		System.out.println("Dec 4 (1): " + Dec4.countValidPassphrases1(input));
		System.out.println("Dec 4 (2): " + Dec4.countValidPassphrases2(input));
	}
	
	public void dec5() {
		String[] input = resourceToStringArray("/input5");
		System.out.println("Dec 5 (1): " + Dec5.mazeOfTwistyTrampolines1(input));
		System.out.println("Dec 5 (2): " + Dec5.mazeOfTwistyTrampolines2(input));
	}
	
	public void dec6() {
		String[] input = resourceToStringArray("/input6");
		System.out.println("Dec 6 (1): " + Dec6.memoryReallocation1(input[0]));
		System.out.println("Dec 6 (2): " + Dec6.memoryReallocation2(input[0]));
	}
	
	public void dec7() {
		String[] input = resourceToStringArray("/input7");
		Dec7 dec7 = new Dec7(input);
		System.out.println("Dec 7 (1): " + dec7.findBottomProgram());
		System.out.println("Dec 7 (2): ");
		dec7.findUnbalancedProgram();
	}
	
	public void dec8() {
		String[] input = resourceToStringArray("/input8");
		Dec8 dec8 = new Dec8();
		dec8.executeInstructions(input);
		System.out.println("Dec 8 (1): " + dec8.getLargestRegisterValue());
		System.out.println("Dec 8 (2): " + dec8.getLargestRegisterValueEverHeld());
	}
	
	public void dec9() {
		String[] input = resourceToStringArray("/input9");
		Dec9 dec9 = new Dec9(input[0]);
		System.out.println("Dec 9 (1): " + dec9.getTotalScore());
		System.out.println("Dec 9 (2): " + dec9.getTotalGarbage());
	}
	
	public void dec10() {
		String[] input = resourceToStringArray("/input10");
		Dec10 dec10 = new Dec10();
		System.out.println("Dec 10 (1): " + dec10.hash1(input[0]));
		dec10 = new Dec10();
		System.out.println("Dec 10 (2): " + dec10.hash2(input[0]));
	}
	
	public void dec11() {
		String[] input = resourceToStringArray("/input11");
		Dec11 dec11 = new Dec11();
		dec11.doMoves(input[0]);
		System.out.println("Dec 11 (1): " + dec11.getDistanceFromOrigin());
		System.out.println("Dec 11 (2): " + dec11.getMaxDistanceFromOrigin());
	}
	
	public void dec12() {
		String[] input = resourceToStringArray("/input12");
		Dec12 dec12 = new Dec12(input);
		System.out.println("Dec 12 (1): " + dec12.getGroupContaining(0).size());
		System.out.println("Dec 12 (2): " + dec12.getNumberOfGroups());
	}
	
	public void dec13() {
		String[] input = resourceToStringArray("/input13");
		Dec13 dec13 = new Dec13(input);
		System.out.println("Dec 13 (1): " + dec13.getTotalSeverity());
		System.out.println("Dec 13 (2): " + dec13.getDelayToPassWithoutBeingCaught());
	}
	
	public void dec14() {
		String input = "nbysizxe";
		Dec14 dec14 = new Dec14();
		System.out.println("Dec 14 (1): " + dec14.getNumberOfUsedSquares(input));
		System.out.println("Dec 14 (2): " + dec14.getNumberOfRegions(input));
	}
	
	public void dec15() {
		Dec15 dec15 = new Dec15();
		System.out.println("Dec 15 (1): " + dec15.getNumberOfMatches(40000000L));
		System.out.println("Dec 15 (2): " + dec15.getNumberOfMatchesStrict(5000000L));
	}
	
	public void dec16() {
		String[] input = resourceToStringArray("/input16");
		Dec16 dec16 = new Dec16();
		System.out.println("Dec 16 (1): " + dec16.dance(input[0]));
		dec16 = new Dec16();
		System.out.println("Dec 16 (2): " + dec16.billionDances(input[0]));
	}
	
	public void dec17() {
		Dec17 dec17 = new Dec17(344, 2017);
		System.out.println("Dec 17 (1): " + dec17.getValueAfterLastInsertion());
		dec17 = new Dec17(344, 50000000);
		System.out.println("Dec 17 (2): " + dec17.getValueAfter0());
	}
	
	public void dec18() {
		String[] input = resourceToStringArray("/input18");
		System.out.println("Dec 18 (1): " + Dec18.runNaiveProgram(input));
		System.out.println("Dec 18 (2): " + Dec18.runCorrectPrograms(input));
	}
}
