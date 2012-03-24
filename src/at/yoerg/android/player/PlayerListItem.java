package at.yoerg.android.player;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import at.yoerg.android.R;
import at.yoerg.businesslogic.player.Player;

public class PlayerListItem extends LinearLayout {

	public PlayerListItem(Context context, Player p) {
		super(context);
		
		CheckBox cb = new CheckBox(context);
		
		TextView name = new TextView(context);
		name.setText(p.getName());
		LayoutParams nameLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		RemovePlayerButton remove = new RemovePlayerButton(context, p);
		remove.setText(R.string.remove);
		remove.setOnClickListener((OnClickListener) context);
		
		addView(cb);
		addView(name);
		addView(remove);
	}

}
