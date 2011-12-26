package at.yoerg.android.player;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.yoerg.android.R;
import at.yoerg.businesslogic.player.Player;

public class PlayerListItem extends LinearLayout {

	public PlayerListItem(Context context, Player p) {
		super(context);
		
		TextView name = new TextView(context);
		name.setText(p.getName());
		
		Button remove = new Button(context);
		remove.setText(R.string.remove);
		
		addView(name);
		addView(remove);
	}

}
