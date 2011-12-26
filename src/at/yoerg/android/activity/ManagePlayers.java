package at.yoerg.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import at.yoerg.android.R;
import at.yoerg.businesslogic.game.GameManager;
import at.yoerg.businesslogic.game.drinkinggame.DrinkingGame;
import at.yoerg.businesslogic.player.Player;

public class ManagePlayers extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.manageplayers);
        Button b = (Button) findViewById(R.id.btnAdd);
        b.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		EditText t = (EditText) findViewById(R.id.inputPlayerName);
		Player player = Player.create(t.getText().toString());
		GameManager.getInstance().getCurrentGame().addPlayer(player);
		GameManager.getInstance().getCurrentGame();
	}

}
