package at.yoerg.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import at.yoerg.android.R;
import at.yoerg.android.player.PlayerListAdapter;
import at.yoerg.android.player.RemovePlayerButton;
import at.yoerg.businesslogic.game.Game;
import at.yoerg.businesslogic.game.GameManager;
import at.yoerg.businesslogic.player.Person;

public class ManagePlayers extends Activity implements OnClickListener {
	
	private Game game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameManager gm = GameManager.getInstance();
		game = gm.startNewGame();
        setContentView(R.layout.manageplayers);
        
        Button b = (Button) findViewById(R.id.btnAdd);
        b.setOnClickListener(this);
        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        
		ListView playerList = (ListView) findViewById(R.id.lvPlayers);
		ListAdapter adapter = new PlayerListAdapter(game, this);
		playerList.setAdapter(adapter);
	}
	
	public void onClick(View v) {
		if(v.getId() == R.id.btnAdd) {
			EditText t = (EditText) findViewById(R.id.inputPlayerName);
			addPlayer(t.getText().toString());
		} else if(v.getId() == R.id.btnPlay) {
    		startActivity(new Intent(this, Play.class));
		} else if(v instanceof RemovePlayerButton) {
			RemovePlayerButton remButton = (RemovePlayerButton) v; 
			game.removePlayer(remButton.getPlayer());
			refreshList();
		}
	}
	
	private void addPlayer(String name) {
		Person player = Person.create(name);
		game.addPlayer(player);
		EditText t = (EditText) findViewById(R.id.inputPlayerName);
		t.setText("");
		refreshList();
	}
	
	private void refreshList() {
		ListView playerList = (ListView) findViewById(R.id.lvPlayers);
		PlayerListAdapter adapter = (PlayerListAdapter) playerList.getAdapter();
		adapter.notifyDataSetChanged();
	}

}
