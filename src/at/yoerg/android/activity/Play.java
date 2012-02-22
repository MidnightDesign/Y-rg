package at.yoerg.android.activity;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import at.yoerg.android.R;
import at.yoerg.businesslogic.board.Field;
import at.yoerg.businesslogic.exception.EndOfGameException;
import at.yoerg.businesslogic.game.Game;
import at.yoerg.businesslogic.game.Game.Turn;
import at.yoerg.businesslogic.game.GameManager;
import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.Rule;

public class Play extends Activity implements OnClickListener {
	
	private Game game;
	List<Player> players;
	String[][] fields;
	Random random;
	Player lastPlayer;
	String[] lastField;
	
	private GameManager gm = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
		gm = GameManager.getInstance();
		game = gm.getCurrentGame();	
		game.start();
		
//		random = new Random();
		
		players = game.getAllPlayers();
		
//		String[][] fields = {
//				{ "Drink One", "Bang! Take that little glass of yours and have a shot!" },
//				{ "Drink Two", "Smack! That's two shots from your glass for you." },
//				{ "Give One", "Choose a player who drinks one." },
//				{ "Give Two", "Choose a player who drinks two or two players who drink one each." },
//				{ "Drink Left", "Your left neighbour drinks one." },
//				{ "Drink Right", "Your right neighbour drinks one." },
//				{ "Social", "Everyone drinks!" },
////				{ "Free Pass", "It's boring, but it's reality. Just pass me to the next player." },
////				{ "Categories", "" },
////				{ "Yörg", "Make up a rule. Better make it good since this doesn't happen too often." },
//				{ "Rule Card", "Draw a rule card. If there are already three of them in the game, put one of those back into the stack. Whatever the drawn card says, applies to all players for as long as the card is in the game." },
//				{ "Like A Virgin", "Drink 5 if you're playing Yörg for the first time." },
//				{ "I Never", "Make a statement that begins with \"I never\". For example: \"I never visited Paris\". Every player (including you!) that this statement doesn't apply to, must drink one. If nobody drinks, you have to drink one." },
//				{ "The Loner", "Drink one if you're single." },
//				{ "Brothers", "Every player who has at least one brother, must drink one." },
//				{ "Sisters", "Every player who has at least one sister, must drink one." },
//				{ "Shut Up", "You're not allowed to say anything before you have to drink again because of any rule. If you break the silence prematurely, you have to drink 5." },
//				{ "Music Expert", "Drink 3 if you don't know the title of the song that's currently playing." },
//				{ "The One Before The Last One", "Fill up a medium-sized glass with a drink of your choice (I recommend beer or something similar).\nStarting with you, the glass gets passed clockwise from player to player, with each drinking from it. It doesn't matter how much they drink as long as they do drink.\nThe player who had the glass before the one that finishes it, has to down a full one of the same drink." },
//				{ "My Siblings", "Drink one for each of your siblings." },
//				{ "Name That Bitch", "Choose a player. That player must tell you the name of your latest sexual partner. If the answer is wrong, that player must drink 5." },
//				{ "The Youth Of Today", "Every player that is younger than you, must drink one." },
//				{ "Old Geezers", "Every player that is older than you, must drink one." },
//				{ "Those Damned Healthy People", "Drink one for every player who isn't a smoker." },
//				{ "Guys", "Every guy must drink one." },
//				{ "Chicks", "Every girl must drink one." },
//				{ "Drink Of Shame", "Drink one for every person you've had sex with." },
//				{ "Remember That Time?", "Each player who slept with another player, must drink one." },
//				{ "Oral Love", "Each player who likes to be the active partner during oral sex, must drink one." },
//				{ "One Bad Idea", "Send a text message to your ex boyfriend/girlfriend." },
//				{ "Just Kidding", "Tell a joke." },
//		};
//		this.fields = fields;
		
		Button next = (Button) findViewById(R.id.btnNext);
		next.setOnClickListener(this);
		Button roll = (Button) findViewById(R.id.btnRollDice);
		roll.setOnClickListener(this);
		
		next();
	}
	
	private void next() {
		Turn t = game.nextTurn();
		TextView playerName = (TextView) findViewById(R.id.txtPlayerName);
		playerName.setText(t.getPlayer().getName());

		TextView title = (TextView) findViewById(R.id.txtTitle);
		title.setText("");
		TextView desc = (TextView) findViewById(R.id.txtDescription);
		desc.setText("");
		
		Button next = (Button) findViewById(R.id.btnNext);
		next.setVisibility(View.INVISIBLE);
		Button roll = (Button) findViewById(R.id.btnRollDice);
		roll.setVisibility(View.VISIBLE);

		TranslateAnimation slideUp = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0, 
				Animation.RELATIVE_TO_PARENT, .3f,
				Animation.RELATIVE_TO_SELF, 0
			);
		slideUp.setDuration(1000);
		playerName.startAnimation(slideUp);
	}
	
	private void rollDice() {
		
		// get current turn
		Turn t = game.getCurrentTurn();
		Field f = null;
		try {
			f = t.getField(gm.rollTheDice());
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
		
		List<Rule> rules = f.getRules();
		if(rules.size() > 1) {
			// multiple rules not implemented yet. just return from method
			return;
		}
		
		TextView title = (TextView) findViewById(R.id.txtTitle);
		title.setText(f.getTitle());
		TextView desc = (TextView) findViewById(R.id.txtDescription);
		desc.setText(rules.get(0).getRuleText());
		
		Button next = (Button) findViewById(R.id.btnNext);
		next.setVisibility(View.VISIBLE);
		Button roll = (Button) findViewById(R.id.btnRollDice);
		roll.setVisibility(View.INVISIBLE);
	}
	
//	private Player getRandomPlayer() {
//		Player p = players.get(random.nextInt(players.size()));
//		if(lastPlayer != null && lastPlayer.equals(p)) {
//			p = getRandomPlayer();
//		}
//		lastPlayer = p;
//		return p;
//	}
	
//	private String[] getRandomField() {
//		return fields[random.nextInt(fields.length)];
//	}
	
	private void tension() {
		TranslateAnimation animOne = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, 
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 3
			);
		animOne.setDuration(2000);

		TranslateAnimation animTwo = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, 
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 3
			);
		animTwo.setDuration(1500);
		animTwo.setStartOffset(500);

		TranslateAnimation animThree = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, 
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 3
			);
		animThree.setDuration(1100);
		animThree.setStartOffset(900);
		
		animThree.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) { }
			
			@Override
			public void onAnimationRepeat(Animation animation) { }
			
			@Override
			public void onAnimationEnd(Animation animation) {
				next();
			}
		});
		
		findViewById(R.id.txtPlayerName).startAnimation(animOne);
		findViewById(R.id.txtTitle).startAnimation(animTwo);
		findViewById(R.id.txtDescription).startAnimation(animThree);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnNext) {
			tension();
		} else if(v.getId() == R.id.btnRollDice) {
			rollDice();
		}
	}

}
