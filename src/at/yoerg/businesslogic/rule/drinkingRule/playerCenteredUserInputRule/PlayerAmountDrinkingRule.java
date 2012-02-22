package at.yoerg.businesslogic.rule.drinkingRule.playerCenteredUserInputRule;

import java.util.ArrayList;
import java.util.List;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.ruleParameter.AmountParameter;
import at.yoerg.businesslogic.rule.ruleParameter.RuleParameter;

public class PlayerAmountDrinkingRule extends PlayerCenteredUserInputRule {

	private Player currentPlayer;
	private List<Player> allPlayers;
	private Integer amount;
	
	public PlayerAmountDrinkingRule(String ruleText, int sips) {
		super(ruleText, sips);
	}

	// expects at the first position of the list an AmountParameter
	@Override
	public void setParameters(List<RuleParameter<?>> parameters) throws NullPointerException, IllegalArgumentException {
		if(parameters == null) {
			throw new NullPointerException("parameters is null");
		}
		if(parameters.isEmpty() || parameters.size() < 1) {
			throw new IllegalArgumentException("parameters has not a valid size");
		}
		
		AmountParameter amount = null;
		try {
			amount = (AmountParameter)parameters.get(0);
		} catch(ClassCastException cce) {
			throw new IllegalArgumentException("parameter at first position is not of type AmountParameter");
		}
		
		this.amount = amount.getValue();

		// retrieve other parameters
	}

	// returns a list of RuleParameters where the first position is an AmountParameter
	@Override
	public List<RuleParameter<?>> getParameters() {
		List<RuleParameter<?>> ruleParameters = new ArrayList<RuleParameter<?>>();
		ruleParameters.add(new AmountParameter("Select amount which the player should drink."));
		return ruleParameters;
	}

	@Override
	protected List<Player> getPlayers() throws IllegalStateException {
		if(amount == null) {
			throw new IllegalStateException("amount has not been set set");
		}
		List<Player> players = new ArrayList<Player>();
		for(int i = 0; i < amount; i++) {
			players.add(currentPlayer);
		}
		return players;
	}

	@Override
	public PlayerAmountDrinkingRule clone() {
		PlayerAmountDrinkingRule rule = new PlayerAmountDrinkingRule(getRuleText(), getSips());
		rule.allPlayers = this.allPlayers;
		rule.amount = this.amount;
		rule.currentPlayer = this.currentPlayer;
		return rule;
	}

}
