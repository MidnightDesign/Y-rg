package at.yoerg.businesslogic.rule.drinkingRule.playerCenteredRule;

import java.util.ArrayList;
import java.util.List;

import at.yoerg.businesslogic.player.Player;

public class AllPlayersDrinkingRule extends PlayerCenteredDrinkingRule {

	public AllPlayersDrinkingRule(String ruleText, int sips) {
		super(ruleText, sips);
	}
	
	@Override
	protected List<Player> getPlayers() throws IllegalStateException {
		if(allPlayers == null) {
			throw new IllegalStateException("all players has not been set yet.");
		}
		List<Player> playerList = new ArrayList<Player>();
		for(int i = 0; i < getSips(); i++) {
			for(Player p : allPlayers) {
				playerList.add(p);
			}
		}
		return playerList;
	}

	@Override
	public AllPlayersDrinkingRule clone() {
		AllPlayersDrinkingRule rule = new AllPlayersDrinkingRule(getRuleText(), getSips());
		rule.allPlayers = this.allPlayers;
		rule.currentPlayer = this.currentPlayer;
		return rule;
	}

}
