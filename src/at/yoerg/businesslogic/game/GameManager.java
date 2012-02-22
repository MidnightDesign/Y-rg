package at.yoerg.businesslogic.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import at.yoerg.businesslogic.board.Board;
import at.yoerg.businesslogic.board.BoardFactory;

public class GameManager {
	
	private static final int DICE_COUNT = 1;
	static private GameManager instance = null;
	
	private Game currentGame = null;
	
	private List<Die> dice;
	
	private GameManager() {
		dice = Die.createDice(DICE_COUNT);
	}
	
	public Game getCurrentGame() {
		return currentGame;
	}
	
	static public GameManager getInstance() {
		if(instance == null) {
			instance = new GameManager();
		}
		return instance;
	}
	
	public Game startNewGame() {
		BoardFactory bf = BoardFactory.INSTANCE;
		try {
			Board board = bf.createRandomBoard();
			currentGame = GameFactory.getInstance().createGame(board, false);
		} catch(Exception e) {
			//TODO: remove log
			Log.d("christoph", "", e);
		}
		return currentGame;
	}
	
	public int rollTheDice() {
		return Die.rollAll(dice);
	}
	
	private static class Die {
		
		private static final Random random = new Random();
		private int currentPips;
		
		public Die() {
			currentPips = roll();
		}
		
		public int roll() {
			currentPips = random.nextInt(5) + 1;
			return currentPips;
		}
		
		private static int rollAll(List<Die> dice) {
			if(dice == null) {
				throw new NullPointerException("dice is null");
			}
			int pipsCount = 0;
			for(Die d : dice) {
				pipsCount += d.roll();
			}
			return pipsCount;
		}
		
		public static List<Die> createDice(int amount) {
			if(amount < 1) {
				throw new IllegalArgumentException("amount must be greater than 0.");
			}
			List<Die> dice = new ArrayList<GameManager.Die>();
			for(int i = 0; i < amount; i++) {
				dice.add(new Die());
			}
			return dice;
		}
	}
}
