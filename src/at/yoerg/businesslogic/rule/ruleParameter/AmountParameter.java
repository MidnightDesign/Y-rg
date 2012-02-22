package at.yoerg.businesslogic.rule.ruleParameter;


public class AmountParameter extends RuleParameter<Integer> {

	public AmountParameter(String text) {
		this(text, false);
	}
	
	public AmountParameter(String text, boolean optional) {
		super(text, optional);
	}

	// check if value is in range
	public void setValue(Integer value) {
		if(value == null) {
			throw new NullPointerException("value can not be null");
		}
		int primValue = value.intValue();
		if(primValue < 0) {
			throw new IllegalArgumentException("amount must be 0 or higher");
		}
		super.setValue(value);
	}
}
