package at.yoerg.android.player;

import android.content.Context;
import android.widget.Button;
import at.yoerg.businesslogic.player.Player;

public class RemovePlayerButton extends Button {
	
	private Player player;

	public RemovePlayerButton(Context context, Player player) {
		super(context);
		setPlayer(player);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

}
