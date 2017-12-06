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
		main.dec6();
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
}
