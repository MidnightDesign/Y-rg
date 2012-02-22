package at.yoerg.businesslogic.rule.ruleParameter;

import java.util.List;

import at.yoerg.businesslogic.player.Player;

public class PlayerListParameter extends RuleParameter<List<Player>> {

	public PlayerListParameter(String text) {
		this(text, false);
	}
	
	public PlayerListParameter(String text, boolean optional) {
		super(text, optional);
	}

}
