package at.yoerg.businesslogic.rule.drinkingRule.userInputRule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.UserInputRule;
import at.yoerg.businesslogic.rule.drinkingRule.DrinkingRule;
import at.yoerg.businesslogic.rule.ruleParameter.PlayerListParameter;
import at.yoerg.businesslogic.rule.ruleParameter.RuleParameter;

public class PlayerListDrinkingRule extends DrinkingRule implements UserInputRule {

	private List<Player> players;
	
	private static final String RULE_PARAMETER_TEXT = "Choose players who drink";
	
	public PlayerListDrinkingRule(String ruleText, int sips) {
		super(ruleText, sips);
	}

	@Override
	protected List<Player> getPlayers() {
		if(players == null) {
			throw new IllegalStateException("no players have been set. (maybe you have not set the RuleParameters)");
		}
		List<Player> pl = new ArrayList<Player>();
		// add the players from user input
		for(Player p : players) {
			// add the player for each sip one time
			for(int i = 0; i < getSips(); i++) {
				pl.add(p);
			}
		}
		return pl;
	}

	@Override
	protected String generateResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("Each of the following players drank ");
		sb.append(getSips());
		sb.append(" sips.\n");
		for(Player p : players) {
			sb.append(p.getName());
			sb.append("\n");
		}
		return sb.toString().trim();
	}

	@Override
	public void setParameters(List<RuleParameter<?>> parameters) throws NullPointerException, IllegalArgumentException {
		if(parameters == null) {
			throw new NullPointerException("param is null");
		}
		if(parameters.isEmpty()) {
			throw new IllegalArgumentException("param list must contain at least one rule parameter");
		}
		
		Iterator<RuleParameter<?>> it = parameters.iterator();
		
		RuleParameter<?> rp = it.next();
		if(!(rp instanceof PlayerListParameter)) {
			throw new IllegalArgumentException("no player list provided");
		}
		players = ((PlayerListParameter)rp).getValue();
		
		// additional parameters
//		while((rp = it.next()) != null) {
//			
//		}
	}

	@Override
	public List<RuleParameter<?>> getParameters() {
		List<RuleParameter<?>> params = new ArrayList<RuleParameter<?>>();
		params.add(new PlayerListParameter(RULE_PARAMETER_TEXT));
		return params;
	}

	@Override
	public PlayerListDrinkingRule clone() {
		PlayerListDrinkingRule clone = new PlayerListDrinkingRule(getRuleText(), getSips());
		clone.players = this.players;
		return clone;
	}

}
