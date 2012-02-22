package at.yoerg.businesslogic.rule.drinkingRule.playerCenteredUserInputRule;

import java.util.List;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.PlayerCenteredRule;
import at.yoerg.businesslogic.rule.UserInputRule;
import at.yoerg.businesslogic.rule.drinkingRule.DrinkingRule;

public abstract class PlayerCenteredUserInputRule extends DrinkingRule implements PlayerCenteredRule, UserInputRule {

	protected Player currentPlayer;
	protected List<Player> allPlayers;
	
	public PlayerCenteredUserInputRule(String ruleText, int sips) {
		super(ruleText, sips);
	}

	@Override
	protected String generateResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player ");
		sb.append(currentPlayer.getName());
		sb.append(" drank ");
		sb.append(getPlayers().size());
		sb.append("sips.");
		return sb.toString();
	}

	@Override
	public final void setCurrentPlayer(Player player) {
		if(player == null) {
			throw new NullPointerException("player can not be null");
		}
		this.currentPlayer = player;
	}

	@Override
	public final void setAllPlayers(List<Player> players) {
		if(players == null) {
			throw new NullPointerException("players can not be null");
		}
		if(players.isEmpty()) {
			throw new IllegalArgumentException("players is empty");
		}
		this.allPlayers = players;
	}
}
