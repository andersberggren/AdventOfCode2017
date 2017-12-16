package com.mountainbranch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		//main.dec1();
		//main.dec2();
		//main.dec3();
		//main.dec4();
		//main.dec5();
		//main.dec6();
		//main.dec7();
		//main.dec8();
		//main.dec9();
		//main.dec10();
		//main.dec11();
		//main.dec12();
		//main.dec13();
		main.dec14();
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
		Dec1 dec1 = new Dec1();
		System.out.println("Dec 1 (1): " + dec1.calculateSum1(input[0]));
		System.out.println("Dec 1 (2): " + dec1.calculateSum2(input[0]));
	}
	
	public void dec2() {
		String[] input = resourceToStringArray("/input2");
		Dec2 dec2 = new Dec2();
		System.out.println("Dec 2 (1): " + dec2.calcChecksum1(input));
		System.out.println("Dec 2 (2): " + dec2.calcChecksum2(input));
	}
	
	public void dec3() {
		int inputAddress = 265149;
		Dec3 dec3 = new Dec3();
		System.out.println("Dec 3 (1): " + dec3.calcDistance(inputAddress));
		System.out.println("Dec 3 (2): " + dec3.calcFirstValueLargerThan(inputAddress));
	}
	
	public void dec4() {
		String[] input = resourceToStringArray("/input4");
		Dec4 dec4 = new Dec4();
		System.out.println("Dec 4 (1): " + dec4.countValidPasswords1(input));
		System.out.println("Dec 4 (1): " + dec4.countValidPasswords2(input));
	}
	
	public void dec5() {
		String[] input = resourceToStringArray("/input5");
		Dec5 dec5 = new Dec5();
		System.out.println("Dec 5 (1): " + dec5.mazeOfTwistyTrampolines1(input));
		System.out.println("Dec 5 (2): " + dec5.mazeOfTwistyTrampolines2(input));
	}
	
	public void dec6() {
		String[] input = resourceToStringArray("/input6");
		Dec6 dec6 = new Dec6();
		System.out.println("Dec 6 (1): " + dec6.memoryReallocation1(input[0]));
		System.out.println("Dec 6 (1): " + dec6.memoryReallocation2(input[0]));
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
}
