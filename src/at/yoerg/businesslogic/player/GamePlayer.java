package at.yoerg.businesslogic.player;

import java.util.List;

import at.yoerg.businesslogic.game.drinkinggame.drink.Sip;

public class GamePlayer {

	private final Player PLAYER;
	
	private List<Sip> sips;
	
	
	public GamePlayer(Player player) {
		PLAYER = player;
	}
	
}
