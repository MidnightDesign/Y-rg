package at.yoerg.businesslogic.rule;

public abstract class AbstractRule implements Rule {
	
	private boolean executed = false;
	
	protected final void setExecuted(boolean executed) {
		this.executed = executed;
	}
	
	protected final boolean getExecuted() {
		return executed;
	}
	
	protected abstract String generateResult();

	public final String getResult() throws IllegalStateException {
		if(!getExecuted()) {
			throw new IllegalStateException("Rule has not been executed yet!");
		}
		return generateResult();
	}
	
	public abstract Rule clone();

}
