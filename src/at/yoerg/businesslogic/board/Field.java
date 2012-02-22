package at.yoerg.businesslogic.board;

import java.util.ArrayList;
import java.util.List;

import at.yoerg.businesslogic.rule.Rule;

public class Field implements Cloneable {
	
	private String title;
	private List<Rule> rules;
	
	private Field() {
	}
	
	protected static Field getField(String title, List<Rule> rules) {
		if(title == null || rules == null) {
			throw new NullPointerException("title or rules is null");
		}
		if(rules.isEmpty()) {
			throw new IllegalArgumentException("rules can not be empty. at least 1 rule has to be specified");
		}
		
		Field f = new Field();
		
		f.setTitle(title);
		f.setRules(rules);
		
		return f;
	} 

	private void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	private void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public List<Rule> getRules() {
		return rules;
	}
	
	@Override
	public Field clone() {
		Field clone = new Field();
		
		clone.setTitle(this.title);
		
		List<Rule> rules = new ArrayList<Rule>();
		for(Rule r : this.rules) {
			rules.add(r.clone());
		}
		clone.setRules(rules);
		
		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Field other = (Field) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
}
