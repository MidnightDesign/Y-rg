package at.yoerg.businesslogic.exception;

import at.yoerg.businesslogic.player.Player;

public class EndOfGameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8603146888791522487L;

	private final Player PLAYER;
	
	public EndOfGameException(Player p) {
		PLAYER = p;
	}
	
	public Player getPlayer() {
		return PLAYER;
	}
	
}
