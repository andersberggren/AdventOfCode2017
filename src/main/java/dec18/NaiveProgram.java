package dec18;

public class NaiveProgram extends Program {
	private long frequency = 0;

	@Override
	protected void handleSnd(String register) {
		frequency = getRegisterValue(register);
	}

	@Override
	protected boolean handleRcv(String register) {
		return getRegisterValue(register) == 0;
	}
	
	public long getFrequency() {
		return frequency;
	}
}
