package com.mountainbranch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dec4 {
	public int countValidPasswords1(String[] passwords) {
		int count = 0;
		for (String password : passwords)
			if (isValid1(password))
				count++;
		return count;
	}

	private boolean isValid1(String password) {
		String[] words = password.split("[\t ]+");
		Set<String> wordSet = new HashSet<String>();
		for (String word : words)
			wordSet.add(word);
		return words.length == wordSet.size();
	}
	
	public int countValidPasswords2(String[] passwords) {
		int count = 0;
		for (String password : passwords)
			if (isValid2(password))
				count++;
		return count;
	}

	private boolean isValid2(String password) {
		String[] words = password.split("[\t ]+");
		Set<String> wordSet = new HashSet<String>();
		for (String word : words) {
			String[] chars = word.split("");
			Arrays.sort(chars);
			String newWord = "";
			for (String s : chars)
				newWord += s;
			wordSet.add(newWord);
		}
		return words.length == wordSet.size();
	}
}
