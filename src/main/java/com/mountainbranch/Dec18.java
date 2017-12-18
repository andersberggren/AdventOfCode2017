package com.mountainbranch;

import java.util.HashMap;
import java.util.Map;

public class Dec18 {
	private final Map<String, Long> registers = new HashMap<String, Long>();
	private long lastSoundPlayed = 0;
	
	public long executeInstructions(String[] instructions) {
		for (int i = 0; i >= 0 && i < instructions.length; i++) {
			String instruction = instructions[i];
			System.out.println("Instruction " + i + ": " + instruction);
			String[] words = instruction.split("[ \t]+");
			String type = words[0];
			if (type.equals("snd")) {
				lastSoundPlayed = getValue(words[1]);
			} else if (type.equals("set")) {
				registers.put(words[1], getValue(words[2]));
				debugRegister(words[1]);
			} else if (type.equals("add")) {
				Long currentValue = getValue(words[1]);
				registers.put(words[1], currentValue + getValue(words[2]));
				debugRegister(words[1]);
			} else if (type.equals("mul")) {
				Long currentValue = getValue(words[1]);
				registers.put(words[1], currentValue * getValue(words[2]));
				debugRegister(words[1]);
			} else if (type.equals("mod")) {
				Long currentValue = getValue(words[1]);
				registers.put(words[1], currentValue % getValue(words[2]));
				debugRegister(words[1]);
			} else if (type.equals("rcv")) {
				if (getValue(words[1]) != 0) {
					return lastSoundPlayed;
				}
			} else if (type.equals("jgz")) {
				if (getValue(words[1]) > 0) {
					// Subtract 1 to compensate for i being incremented by the for-loop
					i += Integer.parseInt(words[2]) - 1;
				}
			}
		}
		throw new RuntimeException("Jumped outside instructions");
	}

	/**
	 * If value is the name of a register, the value in that register is returned.
	 * If value is an integer, that integer is returned.
	 */
	public Long getValue(String valueOrRegister) {
		if (valueOrRegister.matches("[A-Za-z]+")) {
			Long value = registers.get(valueOrRegister);
			return value == null ? 0 : value;
		} else {
			return Long.parseLong(valueOrRegister);
		}
	}
	
	private void debugRegister(String register) {
		System.out.println("Register " + register + ": " + getValue(register));
	}
}
