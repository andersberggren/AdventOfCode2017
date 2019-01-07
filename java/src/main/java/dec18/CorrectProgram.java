package dec18;

public class CorrectProgram extends Program {
	private final int programId;
	private final int partnerProgramId;
	private final MessageQueue messageQueue;
	private int numberOfSends = 0;
	
	public CorrectProgram(int programId, int partnerProgramId, MessageQueue messageQueue) {
		this.programId = programId;
		this.partnerProgramId = partnerProgramId;
		this.messageQueue = messageQueue;
		setRegisterValue("p", programId);
	}
	
	@Override
	protected void handleSnd(String register) {
		messageQueue.send(partnerProgramId, getRegisterValue(register));
		numberOfSends++;
	}
	
	@Override
	protected boolean handleRcv(String register) {
		Long value = messageQueue.receive(programId);
		if (value == null) {
			return false;
		} else {
			setRegisterValue(register, value);
			return true;
		}
	}
	
	/** Returns the number of values this program has sent to the message queue. */
	public int getNumberOfSends() {
		return numberOfSends;
	}
}
