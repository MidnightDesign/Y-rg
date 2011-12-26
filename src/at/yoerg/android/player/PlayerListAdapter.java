package at.yoerg.android.player;

import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import at.yoerg.businesslogic.game.Game;
import at.yoerg.businesslogic.player.Player;

public class PlayerListAdapter extends BaseAdapter {
	
	private Game game;
	private Context ctx;
	private HashMap<Player, View> players;
	
	public PlayerListAdapter(Game game, Context ctx) {
		this.game = game;
		this.ctx = ctx;
		players = new HashMap<Player, View>();
	}

	@Override
	public int getCount() {
		return game.getNumberOfPlayers();
	}

	@Override
	public Object getItem(int position) { 
		return getPlayerAt(position);
	}

	@Override
	public long getItemId(int position) {
		return getEntry(position).getId();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return this.getEntry(position);
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(this.getCount() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}
	
	private Player[] getPlayerArray() {
		return game.getAllPlayers().toArray(new Player[0]);
	}
	
	private Player getPlayerAt(int position) {
		return getPlayerArray()[position];
	}
	
	private View getEntry(int position) {
		Player p = getPlayerAt(position);
		if(!players.containsKey(p)) {
			players.put(p, new PlayerListItem(ctx, p));
		}
		PlayerListItem v = (PlayerListItem) players.get(p);
		return v;
	}

}
