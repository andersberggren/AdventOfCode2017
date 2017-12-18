package dec18;

import java.util.HashMap;
import java.util.Map;

public abstract class Program {
	private final Map<String, Long> registers = new HashMap<String, Long>();
	protected int instructionIndex = 0;
	
	/**
	 * Executes as many instructions as the program can.
	 * @return The number of instructions executed.
	 */
	public int executeInstructions(String[] instructions) {
		int instructionsExecuted = 0;
		for (; true; instructionIndex++, instructionsExecuted++) {
			String instruction = instructions[instructionIndex];
			String[] words = instruction.split("[ \t]+");
			String type = words[0];
			String register = words[1];
			Long currentValue = getRegisterValue(register);
			if (type.equals("snd")) {
				handleSnd(register);
			} else if (type.equals("set")) {
				setRegisterValue(register, getRegisterValue(words[2]));
			} else if (type.equals("add")) {
				setRegisterValue(register, currentValue + getRegisterValue(words[2]));
			} else if (type.equals("mul")) {
				setRegisterValue(register, currentValue * getRegisterValue(words[2]));
			} else if (type.equals("mod")) {
				setRegisterValue(register, currentValue % getRegisterValue(words[2]));
			} else if (type.equals("rcv")) {
				if (!handleRcv(register)) {
					return instructionsExecuted;
				}
			} else if (type.equals("jgz")) {
				if (getRegisterValue(words[1]) > 0) {
					// Subtract 1 to compensate for i being incremented by the for-loop
					instructionIndex += getRegisterValue(words[2]) - 1;
				}
			}
		}
	}
	
	protected abstract void handleSnd(String register);
	
	/** Returns true on success, false if program should halt. */
	protected abstract boolean handleRcv(String register);

	protected final long getRegisterValue(String registerOrValue) {
		if (registerOrValue.matches("[A-Za-z]+")) {
			Long value = registers.get(registerOrValue);
			return value == null ? 0 : value;
		} else {
			return Long.parseLong(registerOrValue);
		}
	}
	
	protected final void setRegisterValue(String register, long value) {
		registers.put(register, (Long) value);
	}
}
