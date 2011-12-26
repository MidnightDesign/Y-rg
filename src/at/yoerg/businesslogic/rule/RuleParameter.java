package at.yoerg.businesslogic.rule;

public class RuleParameter<T> {
	
	private String text;
	private T value;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}

}
