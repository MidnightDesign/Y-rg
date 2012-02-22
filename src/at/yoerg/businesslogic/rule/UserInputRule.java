package at.yoerg.businesslogic.rule;

import java.util.List;

import at.yoerg.businesslogic.rule.ruleParameter.RuleParameter;

public interface UserInputRule extends Rule {
	
	// sets the parameter list
	// can throw NullPointerException if parameters are required and not provided (param == null)
	// throws IllegalArgumentException if ParameterObject is not valid
	void setParameters(List<RuleParameter<?>> parameters) throws NullPointerException, IllegalArgumentException;
	
	// returns the parameter object or collection of parameter objects which are needed
	// to execute the rule.
	// the execute method must be invoked with the filled out parameters
	List<RuleParameter<?>> getParameters();
}
