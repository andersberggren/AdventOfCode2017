package com.mountainbranch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dec4 {
	public static int countValidPassphrases1(String[] passphrases) {
		return countValidPassphrases(passphrases, false);
	}

	public static int countValidPassphrases2(String[] passphrases) {
		return countValidPassphrases(passphrases, true);
	}

	private static int countValidPassphrases(String[] passphrases, boolean detectAnagrams) {
		int count = 0;
		for (String passphrase : passphrases) {
			if (isValid(passphrase, detectAnagrams)) {
				count++;
			}
		}
		return count;
	}
	
	private static boolean isValid(String passphrase, boolean detectAnagrams) {
		Set<String> wordSet = new HashSet<String>();
		for (String word : passphrase.split("[\t ]+")) {
			if (detectAnagrams) {
				char[] chars = word.toCharArray();
				Arrays.sort(chars);
				word = String.valueOf(chars);
			}
			if (!wordSet.add(word)) {
				return false;
			}
		}
		return true;
	}
}
