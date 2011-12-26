package at.yoerg.android.adapter;

import java.util.HashMap;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import at.yoerg.businesslogic.game.Game;
import at.yoerg.businesslogic.player.Person;

public class PlayerListAdapter implements ListAdapter {
	
	private Game game;
	private Context ctx;
	private HashMap<Person, View> players;
	
	public PlayerListAdapter(Game game, Context ctx) {
		this.game = game;
		this.ctx = ctx;
		players = new HashMap<Person, View>();
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
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}
	
	private Person[] getPlayerArray() {
		return game.getAllPlayers().toArray(new Person[0]);
	}
	
	private Person getPlayerAt(int position) {
		return getPlayerArray()[position];
	}
	
	private View getEntry(int position) {
		Person p = getPlayerAt(position);
		if(!players.containsKey(p)) {
			players.put(p, new TextView(ctx));
		}
		TextView v = (TextView) players.get(p);
		v.setText(p.getName());
		return v;
	}

}
