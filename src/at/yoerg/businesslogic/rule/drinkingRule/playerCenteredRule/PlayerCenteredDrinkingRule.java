package at.yoerg.businesslogic.rule.drinkingRule.playerCenteredRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.PlayerCenteredRule;
import at.yoerg.businesslogic.rule.drinkingRule.DrinkingRule;

public class PlayerCenteredDrinkingRule extends DrinkingRule implements PlayerCenteredRule {

	protected Player currentPlayer;
	protected List<Player> allPlayers;
	
	public PlayerCenteredDrinkingRule(String ruleText, int sips) {
		super(ruleText, sips);
	}

	@Override
	protected List<Player> getPlayers() throws IllegalStateException {
		if(currentPlayer == null) {
			throw new IllegalStateException("current player has not been set yet");
		}
		List<Player> playerList = new ArrayList<Player>();
		for(int i = 0; i < getSips(); i++) {
			playerList.add(currentPlayer);
		}
		return playerList;
	}

	@Override
	protected String generateResult() {
		Set<Player> playerSet = new HashSet<Player>();
		playerSet.addAll(getPlayers());
		StringBuilder sb = new StringBuilder();
		sb.append("The following players drank ");
		sb.append(getSips());
		sb.append(" sips.\n");
		for(Player p : playerSet) {
			sb.append(p.getName());
			sb.append("\n");
		}
		return sb.toString().trim();
	}

	@Override
	public PlayerCenteredDrinkingRule clone() {
		PlayerCenteredDrinkingRule rule = new PlayerCenteredDrinkingRule(getRuleText(), getSips());
		rule.allPlayers = this.allPlayers;
		rule.currentPlayer = this.currentPlayer;
		return rule;
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
