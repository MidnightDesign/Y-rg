package at.yoerg.businesslogic.rule.ruleParameter;

public class DecisionParameter extends RuleParameter<Boolean> {
	
	public DecisionParameter(String text) {
		this(text, false);
	}
	
	public DecisionParameter(String text, boolean optional) {
		super(text, optional);
	}

}
