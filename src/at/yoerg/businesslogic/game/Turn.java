package at.yoerg.businesslogic.game;

import java.io.Serializable;

import at.yoerg.businesslogic.board.Field;
import at.yoerg.businesslogic.player.Player;

public class Turn implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8645924455142975915L;
	
	private Player player;
	private Integer pips;
	private Field field;
	
	protected Turn() {
	}
	
	public static Turn getTurn(Player player, Field field, int pips) {
		if(player == null || field == null) {
			throw new NullPointerException();
		}
		Turn turn = new Turn();
		
		turn.setField(field);
		turn.setPlayer(player);
		turn.setPips(pips);
		
		return turn;
	}

	public Player getPlayer() {
		return player;
	}

	protected void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getPips() {
		return pips;
	}

	protected void setPips(Integer pips) {
		this.pips = pips;
	}

	public Field getField() {
		return field;
	}

	protected void setField(Field field) {
		this.field = field;
	}
}
