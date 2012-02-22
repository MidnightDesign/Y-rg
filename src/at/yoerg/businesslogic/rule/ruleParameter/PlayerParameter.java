package at.yoerg.businesslogic.rule.ruleParameter;

import at.yoerg.businesslogic.player.Player;

public class PlayerParameter extends RuleParameter<Player> {

	public PlayerParameter(String text) {
		this(text, false);
	}
	
	public PlayerParameter(String text, boolean optional) {
		super(text, optional);
	}

}
