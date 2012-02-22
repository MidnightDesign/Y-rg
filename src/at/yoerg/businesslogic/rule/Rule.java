package at.yoerg.businesslogic.rule;


public interface Rule extends Cloneable {
	
	// returns the general rule text
	// the text must be provided when creating a new rule
	String getRuleText();
	
	// returns the result of the rule
	// throws IllegalStateException if rule has not been executed yet
	String getResult() throws IllegalStateException;
	
	// executes the rule with given parameters
	// returns result of execution
	// throws IllegalStateException if information for execution is missing
	String execute() throws IllegalStateException;

	// clones the rule
	Rule clone();
}
