package at.yoerg.businesslogic.game;

public class GameManager {
	
	static private GameManager instance = null;
	
	private Game currentGame = null;
	
	private GameManager() {
		
	}
	
	public Game getCurrentGame() {
		if(currentGame == null) {
			currentGame = new Game();
		}
		return currentGame;
	}
	
	static public GameManager getInstance() {
		if(instance == null) {
			instance = new GameManager();
		}
		return instance;
	}
	
}
