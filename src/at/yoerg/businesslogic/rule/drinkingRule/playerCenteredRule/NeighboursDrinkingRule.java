package at.yoerg.businesslogic.rule.drinkingRule.playerCenteredRule;

import java.util.ArrayList;
import java.util.List;

import at.yoerg.businesslogic.player.Player;
import at.yoerg.util.CollectionUtil;

public class NeighboursDrinkingRule extends PlayerCenteredDrinkingRule {
	
	private static final int DEFAULT_NEIGHBOURS_OFFSET = 1;
	
	private Player left, right;
	private Type type;
	private int neighbourOffsetLeft, neighbourOffsetRight;
	
	public static enum Type {
		LEFT, RIGHT, BOTH
	}

	public NeighboursDrinkingRule(String ruleText, int sips, Type type) {
		super(ruleText, sips);
		this.type = type;
		neighbourOffsetLeft = DEFAULT_NEIGHBOURS_OFFSET;
		neighbourOffsetRight = DEFAULT_NEIGHBOURS_OFFSET;
	}

	@Override
	protected List<Player> getPlayers() throws IllegalStateException {
		if(currentPlayer == null || allPlayers == null) {
			throw new IllegalStateException("current player or/and all players have not been set yet");
		}
		// calculate neighbours
		neighbours();
		List<Player> playerList = new ArrayList<Player>();
		for(int i = 0; i < getSips(); i++) {
			if(Type.LEFT.equals(type) || Type.BOTH.equals(type)) {
				playerList.add(left);
			}
			if(Type.RIGHT.equals(type) || Type.BOTH.equals(type)) {
				playerList.add(right);
			}
		}
		return playerList;
	}
	
	// calculates the neighbours of current player 
	private void neighbours() {
		if(currentPlayer == null || allPlayers == null) {
			throw new IllegalStateException("current player or/and all players have not been set yet");
		}
		// if left and right already set, return from method
		if(left != null && right != null) {
			return;
		}
		int currentPlayerPosition = allPlayers.indexOf(currentPlayer);
		if(currentPlayerPosition == -1) {
			throw new IllegalStateException("current player not in all players");
		}
		
		int leftPosition = CollectionUtil.getCycledCollectionIndex(allPlayers, currentPlayerPosition - neighbourOffsetLeft);
		int rightPosition = CollectionUtil.getCycledCollectionIndex(allPlayers, currentPlayerPosition + neighbourOffsetRight);
		
		left = allPlayers.get(leftPosition);
		right = allPlayers.get(rightPosition);
	}

	@Override
	public NeighboursDrinkingRule clone() {
		NeighboursDrinkingRule rule = new NeighboursDrinkingRule(getRuleText(), getSips(), type);
		rule.allPlayers = this.allPlayers;
		rule.currentPlayer = this.currentPlayer;
		rule.left = this.left;
		rule.right = this.right;
		return rule;
	}
}
