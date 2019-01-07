package dec18;

public class Dec18 {
	public static long runNaiveProgram(String[] instructions) {
		NaiveProgram naiveProgram = new NaiveProgram();
		naiveProgram.executeInstructions(instructions);
		return naiveProgram.getFrequency();
	}
	
	/**
	 * Creates two programs and lets them execute instructions until they get
	 * stuck in a deadlock.
	 * @return The number of values sent by program 1.
	 */
	public static int runCorrectPrograms(String[] instructions) {
		MessageQueue messageQueue = new MessageQueue();
		CorrectProgram program0 = new CorrectProgram(0, 1, messageQueue);
		CorrectProgram program1 = new CorrectProgram(1, 0, messageQueue);
		int numberOfExecutedInstructions;
		do {
			numberOfExecutedInstructions = 0;
			numberOfExecutedInstructions += program0.executeInstructions(instructions);
			numberOfExecutedInstructions += program1.executeInstructions(instructions);
		} while (numberOfExecutedInstructions > 0);
		return program1.getNumberOfSends();
	}
}
