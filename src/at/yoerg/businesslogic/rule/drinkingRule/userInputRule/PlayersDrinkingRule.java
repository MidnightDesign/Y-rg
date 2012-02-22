package at.yoerg.businesslogic.rule.drinkingRule.userInputRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.UserInputRule;
import at.yoerg.businesslogic.rule.drinkingRule.DrinkingRule;
import at.yoerg.businesslogic.rule.ruleParameter.AmountParameter;
import at.yoerg.businesslogic.rule.ruleParameter.PlayerParameter;
import at.yoerg.businesslogic.rule.ruleParameter.RuleParameter;

public class PlayersDrinkingRule extends DrinkingRule implements UserInputRule {

	private List<Player> players;
	
	protected static final String RULE_PARAMETER_TEXT = "Choose a player who drinks one";
	
	public PlayersDrinkingRule(String ruleText, int sips) {
		super(ruleText, sips);
	}

	@Override
	protected List<Player> getPlayers() {
		if(players == null) {
			throw new IllegalStateException("no players have been set. (maybe you have not set the RuleParameters)");
		}
		return players;
	}

	@Override
	protected String generateResult() {
		Map<Player, Integer> playerSips = new HashMap<Player, Integer>();
		for(Player p : getPlayers()) {
			if(playerSips.containsKey(p)) {
				int sips = playerSips.get(p).intValue() + 1;
				playerSips.put(p, sips);
			} else {
				playerSips.put(p, 1);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("The following players drank:\n");
		for(Player p : playerSips.keySet()) {
			sb.append(p.getName());
			sb.append(" drank ");
			sb.append(playerSips.get(p));
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public void setParameters(List<RuleParameter<?>> parameters) throws NullPointerException, IllegalArgumentException {
		if(parameters == null) {
			throw new NullPointerException("paramters is null");
		}
		if(parameters.isEmpty()) {
			throw new IllegalArgumentException("parameters can not be empty");
		}
		List<Player> players = new ArrayList<Player>();
		int counter = getSips();
		for(RuleParameter<?> rp : parameters) {
			if(counter > 0) {
				// player parameters
				if(!(rp instanceof PlayerParameter)) {
					throw new IllegalArgumentException("parameters is not valid");
				}
				// add player to internal player list
				players.add(((PlayerParameter)rp).getValue());
				counter--;
			} else {
				// additional parameters
			}
		}
	}

	@Override
	public List<RuleParameter<?>> getParameters() {
		List<RuleParameter<?>> params = new ArrayList<RuleParameter<?>>();
		RuleParameter<Player> r = null;
		for(int i = 0; i < getSips(); i++) {
			r = new PlayerParameter(RULE_PARAMETER_TEXT);
			params.add(r);
		}
		// add optional amount of sips per player
		params.add(new AmountParameter("How much sips should each player drink", true));
		return params;
	}

	@Override
	public PlayersDrinkingRule clone() {
		PlayersDrinkingRule clone = new PlayersDrinkingRule(getRuleText(), getSips());
		clone.players = this.players;
		return clone;
	}

}
