package at.yoerg.android.player;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.yoerg.android.R;
import at.yoerg.businesslogic.player.Player;

public class PlayerListItem extends LinearLayout {

	public PlayerListItem(Context context, Player p) {
		super(context);
		
		TextView name = new TextView(context);
		name.setText(p.getName());
		
		RemovePlayerButton remove = new RemovePlayerButton(context, p);
		remove.setText(R.string.remove);
		remove.setOnClickListener((OnClickListener) context);
		
		addView(name);
		addView(remove);
	}

}
