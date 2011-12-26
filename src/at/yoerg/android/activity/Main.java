package at.yoerg.android.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import at.yoerg.android.R;
import at.yoerg.businesslogic.game.GameManager;
import at.yoerg.businesslogic.game.drinkinggame.DrinkingGame;
import at.yoerg.businesslogic.game.drinkinggame.drink.DrinkCategory;

public class Main extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	if(position == 0) {
    		startActivity(new Intent(Main.this, ManagePlayers.class));
    	}
    }
}