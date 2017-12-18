package dec18;

public class NaiveProgram extends Program {
	private long frequency = 0;

	@Override
	protected void handleSnd(String register) {
		frequency = getRegisterValue(register);
	}

	@Override
	protected void handleRcv(String register) throws HaltException {
		if (getRegisterValue(register) != 0) {
			throw new HaltException();
		}
	}
	
	public long getFrequency() {
		return frequency;
	}
}
