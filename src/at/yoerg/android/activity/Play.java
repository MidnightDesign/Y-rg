package at.yoerg.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import at.yoerg.businesslogic.exception.EndOfGameException;
import at.yoerg.businesslogic.game.Game;
import at.yoerg.businesslogic.game.GameManager;
import at.yoerg.businesslogic.game.Turn;

public class Play extends Activity implements OnClickListener {
	
	Game game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameManager gm = GameManager.getInstance();
		Game game = gm.getCurrentGame();
		game.start();
		Turn t;
		try {
			t = game.nextTurn(gm.rollTheDice());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EndOfGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
