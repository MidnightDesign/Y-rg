package at.yoerg.businesslogic.rule;

public abstract class Rule<T> {
	
	public abstract RuleParameter<T> getParamObject();
	
	public abstract void execute(RuleParameter<T> param);

}
