package at.yoerg.businesslogic.board;

import java.util.List;

import at.yoerg.businesslogic.rule.Rule;

public class Field {
	
	private String title;
	private List<Rule> rules;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public List<Rule> getRules() {
		return rules;
	}
	
	

}
