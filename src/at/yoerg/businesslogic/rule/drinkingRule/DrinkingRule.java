package at.yoerg.businesslogic.rule.drinkingRule;

import java.util.List;

import at.yoerg.businesslogic.game.drinkinggame.drink.Sip;
import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.AbstractRule;

public abstract class DrinkingRule extends AbstractRule {
	
	private String ruleText = null;
	private int sips;
	
	public DrinkingRule(String ruleText, int sips) {
		this.ruleText = ruleText;
		if(sips < 1) {
			throw new IllegalArgumentException("sips must be at least 1");
		}
		this.sips = sips;
	}
	
	@Override
	public String getRuleText() {
		return ruleText;
	}

	@Override
	public String execute() throws IllegalStateException {
		for(Player p : getPlayers()) {
			p.addSip(new Sip());
		}
		
		setExecuted(true);
		return getResult();
	}
	
	// returns players that have to drink
	// throws IllegalStateException if players could not be returned
	protected abstract List<Player> getPlayers() throws IllegalStateException;
	
	protected int getSips() {
		return sips;
	}

}
