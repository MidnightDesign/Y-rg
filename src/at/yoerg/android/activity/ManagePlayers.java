package at.yoerg.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import at.yoerg.android.R;
import at.yoerg.android.player.PlayerListAdapter;
import at.yoerg.businesslogic.game.Game;
import at.yoerg.businesslogic.game.GameManager;
import at.yoerg.businesslogic.player.Player;

public class ManagePlayers extends Activity implements OnClickListener {
	
	private Game game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		game = GameManager.getInstance().getCurrentGame();
        setContentView(R.layout.manageplayers);
        Button b = (Button) findViewById(R.id.btnAdd);
        b.setOnClickListener(this);
		ListView playerList = (ListView) findViewById(R.id.lvPlayers);
		ListAdapter adapter = new PlayerListAdapter(game, this);
		playerList.setAdapter(adapter);
	}
	
	public void onClick(View v) {
		if(v.getId() == R.id.btnAdd) {
			EditText t = (EditText) findViewById(R.id.inputPlayerName);
			addPlayer(t.getText().toString());
		}
	}
	
	private void addPlayer(String name) {
		Player player = Player.create(name);
		game.addPlayer(player);
	}

}
