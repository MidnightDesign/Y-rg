package at.yoerg.businesslogic.game;

import java.util.ArrayList;
import java.util.UUID;

import at.yoerg.businesslogic.board.Board;
import at.yoerg.businesslogic.card.rulecard.RuleCardManager;
import at.yoerg.businesslogic.game.Game.Turn;
import at.yoerg.businesslogic.player.Player;

public class GameFactory {
	
	private static GameFactory instance = null;
	
	public static GameFactory getInstance() {
		if(instance == null) {
			instance = new GameFactory();
		}
		return instance;
	}
	
	private GameFactory() {}
	
	public Game createGame(Board board, boolean finite) {
		if(board == null) {
			throw new NullPointerException("board is null.");
		}
		Game game = new Game();
		
		game.setBoard(board);
		game.setCurrentPlayerPosition(new Integer(-1));
		game.setFinite(finite);
		game.setId(UUID.randomUUID().toString());
		game.setPlayers(new ArrayList<Player>());
		game.setRuleCardManager(new RuleCardManager());
		game.setTurns(new ArrayList<Turn>());
		
		return game;
	}

}
