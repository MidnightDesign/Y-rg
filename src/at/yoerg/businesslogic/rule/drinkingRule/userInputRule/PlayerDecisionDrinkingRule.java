package at.yoerg.businesslogic.rule.drinkingRule.userInputRule;

import java.util.ArrayList;
import java.util.List;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.UserInputRule;
import at.yoerg.businesslogic.rule.drinkingRule.DrinkingRule;
import at.yoerg.businesslogic.rule.ruleParameter.DecisionParameter;
import at.yoerg.businesslogic.rule.ruleParameter.PlayerParameter;
import at.yoerg.businesslogic.rule.ruleParameter.RuleParameter;

public class PlayerDecisionDrinkingRule extends DrinkingRule implements UserInputRule {

	private Player player;
	private Boolean decision;
	
	public PlayerDecisionDrinkingRule(String ruleText, int sips) {
		super(ruleText, sips);
	}

	// sets parameters for rule
	// 1st parameter: PlayerParameter
	// 2nd parameter: DecisionParameter
	// throws IllegalArgumentException if parameters not provided or in wrong order
	@Override
	public void setParameters(List<RuleParameter<?>> parameters) throws NullPointerException, IllegalArgumentException {
		if(parameters == null) {
			throw new NullPointerException("parameters is null");
		}
		if(parameters.size() < 2) {
			throw new IllegalArgumentException("2 parameters required: 1 PlayerParameter, 1 DecisionParameter");
		}
		
		PlayerParameter player = null;
		DecisionParameter decision = null;
		try {
			player = (PlayerParameter)parameters.get(0);
			decision = (DecisionParameter)parameters.get(1);
		} catch(ClassCastException cce) {
			throw new IllegalArgumentException("required parameters not provided or in wrong order");
		}
		this.player = player.getValue();
		this.decision = decision.getValue();
	}

	@Override
	public List<RuleParameter<?>> getParameters() {
		List<RuleParameter<?>> ruleParameters = new ArrayList<RuleParameter<?>>();
		ruleParameters.add(new PlayerParameter("Choose player"));
		ruleParameters.add(new DecisionParameter("Is the answer correct?"));
		return ruleParameters;
	}

	@Override
	protected List<Player> getPlayers() throws IllegalStateException {
		if(player == null || decision == null) {
			throw new IllegalStateException("player and/or decision has not been set");
		}
		List<Player> players = new ArrayList<Player>();
		if(Boolean.TRUE.equals(decision)) {
			return players;
		}
		for(int i = 0; i < getSips(); i++) {
			players.add(player);
		}
		return players;
	}

	@Override
	protected String generateResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player ");
		sb.append(player.getName());
		sb.append(" drank ");
		sb.append(getSips());
		sb.append(" sips.");
		return sb.toString();
	}

	@Override
	public PlayerDecisionDrinkingRule clone() {
		PlayerDecisionDrinkingRule clone = new PlayerDecisionDrinkingRule(getRuleText(), getSips());
		clone.decision = this.decision;
		clone.player = this.player;
		return clone;
	}

}
