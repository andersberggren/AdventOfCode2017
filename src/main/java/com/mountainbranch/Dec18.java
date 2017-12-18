package com.mountainbranch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Dec18 {
	private static Map<Integer, List<Long>> messageQueues = new HashMap<Integer, List<Long>>();
	
	private final Map<String, Long> registers = new HashMap<String, Long>();
	private final int programId;
	private final int otherProgramId;
	private int instructionIndex = 0;
	private int numberOfSends = 0;
	
	public static int executeInstructionsUntilDeadlock(String[] instructions) {
		Dec18 program0 = new Dec18(0, 1);
		Dec18 program1 = new Dec18(1, 0);
		while (program0.execute(instructions) + program1.execute(instructions) > 0);
		return program1.numberOfSends;
	}
	
	private Dec18(int programId, int otherProgramId) {
		this.programId = programId;
		this.otherProgramId = otherProgramId;
		setRegister("p", Long.valueOf(programId));
	}
	
	private int execute(String[] instructions) {
		int instructionsExecuted = 0;
		for (; true; instructionIndex++, instructionsExecuted++) {
			String instruction = instructions[instructionIndex];
			String[] words = instruction.split("[ \t]+");
			String type = words[0];
			String register = words[1];
			Long currentValue = getValue(register);
			if (type.equals("snd")) {
				send(register);
			} else if (type.equals("set")) {
				setRegister(register, getValue(words[2]));
			} else if (type.equals("add")) {
				setRegister(register, currentValue + getValue(words[2]));
			} else if (type.equals("mul")) {
				setRegister(register, currentValue * getValue(words[2]));
			} else if (type.equals("mod")) {
				setRegister(register, currentValue % getValue(words[2]));
			} else if (type.equals("rcv")) {
				if (!receive(register)) {
					return instructionsExecuted;
				}
			} else if (type.equals("jgz")) {
				if (getValue(words[1]) > 0) {
					// Subtract 1 to compensate for i being incremented by the for-loop
					instructionIndex += getValue(words[2]) - 1;
				}
			}
		}
	}

	/**
	 * If value is the name of a register, the value in that register is returned.
	 * If value is an integer, that integer is returned.
	 */
	private Long getValue(String valueOrRegister) {
		if (valueOrRegister.matches("[A-Za-z]+")) {
			Long value = registers.get(valueOrRegister);
			return value == null ? 0 : value;
		} else {
			return Long.parseLong(valueOrRegister);
		}
	}

	private void setRegister(String register, Long value) {
		registers.put(register, value);
	}
	
	private void send(String valueOrRegister) {
		List<Long> messageQueue = messageQueues.get(otherProgramId);
		if (messageQueue == null) {
			messageQueue = new LinkedList<Long>();
			messageQueues.put(otherProgramId, messageQueue);
		}
		messageQueue.add(getValue(valueOrRegister));
		numberOfSends++;
	}
	
	private boolean receive(String register) {
		List<Long> messageQueue = messageQueues.get(programId);
		if (messageQueue == null || messageQueue.isEmpty()) {
			return false;
		} else {
			setRegister(register, messageQueue.remove(0));
			return true;
		}
	}
}
