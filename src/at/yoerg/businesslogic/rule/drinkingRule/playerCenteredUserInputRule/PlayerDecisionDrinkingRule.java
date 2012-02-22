package at.yoerg.businesslogic.rule.drinkingRule.playerCenteredUserInputRule;

import java.util.ArrayList;
import java.util.List;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.ruleParameter.DecisionParameter;
import at.yoerg.businesslogic.rule.ruleParameter.RuleParameter;

public class PlayerDecisionDrinkingRule extends PlayerCenteredUserInputRule {

	private Boolean decision;
	
	public PlayerDecisionDrinkingRule(String ruleText, int sips) {
		super(ruleText, sips);
	}

	@Override
	public void setParameters(List<RuleParameter<?>> parameters) throws NullPointerException, IllegalArgumentException {
		if(parameters == null) {
			throw new NullPointerException("parameters can not be null");
		}
		if(parameters.size() < 1) {
			throw new IllegalArgumentException("at least 1 parameter (DecisionParameter) must be provided");
		}
		
		DecisionParameter decision = null;
		try {
			decision = (DecisionParameter)parameters.get(0);
		} catch(ClassCastException cce) {
			throw new IllegalArgumentException("parameter at first position is not of type DecisionRuleParameter");
		}
		this.decision = decision.getValue();
	}

	@Override
	public List<RuleParameter<?>> getParameters() {
		List<RuleParameter<?>> ruleParameters = new ArrayList<RuleParameter<?>>();
		ruleParameters.add(new DecisionParameter("Is this true?"));
		return ruleParameters;
	}

	@Override
	protected List<Player> getPlayers() throws IllegalStateException {
		if(decision == null || currentPlayer == null) {
			throw new IllegalStateException("decision and/or current player has not been set yet");
		}
		List<Player> players = new ArrayList<Player>();
		if(Boolean.FALSE.equals(decision)) {
			return players;
		}
		for(int i = 0; i < getSips(); i++) {
			players.add(currentPlayer);
		}
		return players;
	}

	@Override
	public PlayerDecisionDrinkingRule clone() {
		PlayerDecisionDrinkingRule clone = new PlayerDecisionDrinkingRule(getRuleText(), getSips());
		clone.allPlayers = this.allPlayers;
		clone.currentPlayer = this.currentPlayer;
		clone.decision = this.decision;
		return clone;
	}

}
