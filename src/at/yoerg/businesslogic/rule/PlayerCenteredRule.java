package at.yoerg.businesslogic.rule;

import java.util.List;

import at.yoerg.businesslogic.player.Player;

public interface PlayerCenteredRule extends Rule {

	// sets the player which turn it is
	void setCurrentPlayer(Player player);
	
	// sets all players in the game
	void setAllPlayers(List<Player> players);
}
