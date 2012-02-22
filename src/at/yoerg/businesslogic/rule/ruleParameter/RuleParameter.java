package at.yoerg.businesslogic.rule.ruleParameter;

public abstract class RuleParameter<T> {
	
	private String text;
	private T value;
	private boolean optional;
	
	public RuleParameter(String text, boolean optional) {
		setText(text);
		this.optional = optional;
	}

	private void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public void setValue(T value) throws NullPointerException, IllegalArgumentException {
		if(value == null) {
			throw new NullPointerException("value can not be null");
		}
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	public boolean getOptional() {
		return optional;
	}

}
