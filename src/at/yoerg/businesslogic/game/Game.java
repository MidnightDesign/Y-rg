package at.yoerg.businesslogic.game;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import at.yoerg.businesslogic.board.Board;
import at.yoerg.businesslogic.board.Field;
import at.yoerg.businesslogic.card.rulecard.RuleCardManager;
import at.yoerg.businesslogic.player.Player;
import at.yoerg.util.CollectionUtil;
import at.yoerg.util.comparator.AppendToTailComperator;


public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3509633604478116999L;
	
	private String id;
	private SortedMap<Player,Integer> players;
	private RuleCardManager ruleCardManager;
	private SortedSet<Turn> turns;
	private Board board;
	private Date started;
	private Date finished;
	
	// create instances of Game through GameFactory
	protected Game() {
		
	}
	
	public void start() {
		if(started != null) {
			throw new IllegalStateException("Game has already started");
		}
		this.setStarted(new Date());
	}
	
	public void finish() {
		if(finished != null) {
			throw new IllegalStateException("Game has alredy been finished");
		}
		if(started == null) {
			throw new IllegalStateException("Game has not started yet");
		}
		this.setFinished(new Date());
	}

	public boolean addPlayer(Player player) {
		if(player == null) {
			throw new NullPointerException("player is null");
		}
		try {
			getPlayers().put(player, getPlayers().size());
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		return true;
	}
	
	public boolean removePlayer(Player player) {
		if(player == null) {
			throw new NullPointerException("player is null");
		}
		
		Integer position = getPlayers().remove(player);
		if(position == null) {
			return false;
		}
		return true;
	}
	
	// returns a copy of the players set
	public Collection<Player> getAllPlayers() {
		return CollectionUtil.copy(getPlayers().keySet());
	}
	
	public Turn nextTurn(int pips) throws IllegalArgumentException, IllegalStateException {
		if(!validatePips(pips)) {
			throw new IllegalArgumentException("pips not valid. must be between 1 and 6.");
		}
		
		// gets the nex player in line
		Player p = getNextPlayer();
		// get next field for player
		Field f = getNextFieldForPlayer(p);
		
		Turn t = new Turn();
		
		return t;
	}
	
	private boolean validatePips(int pips) {
		if(pips < 1 || pips > 6) {
			return false;
		}
		return true;
	}
	
	private Field getNextFieldForPlayer(Player player) throws IllegalStateException, IllegalArgumentException, NullPointerException {
		if(player == null) {
			throw new NullPointerException("player can not be null");
		}
		if(board == null) {
			throw new IllegalStateException("no board initialised");
		}
		
		Integer playerPosition = getPlayers().get(player);
		if(playerPosition == null) {
			throw new IllegalArgumentException("player not in player list");
		}
		
		Field f = null;
		Field[] fieldArray = CollectionUtil.asArray(getBoard().getAllFields());
	}
	
	// returns the next player in line
	private Player getNextPlayer() throws IllegalStateException {
		if(getPlayers().size() == 0) {
			throw new IllegalStateException("no players added to game");
		}
		// if no turns have been played return the first player in line
		if(getTurns().size() == 0) {
			return getPlayers().firstKey();
		}
		Player lastPlayer = getTurns().last().getPlayer();
		Iterator<Player> it = getPlayers().keySet().iterator();
		Player current = null;
		while(it.hasNext()) {
			current = it.next();
			if(lastPlayer.equals(current)) {
				if(it.hasNext()) {
					current = it.next();
				} else {
					current = getPlayers().firstKey();
				}
				break;
			}
		}
		return current;
	}
	
	// returns copy of the turn set
	public Collection<Turn> getAllTurns() {
		return CollectionUtil.copy(getTurns());
	}
	
	// setters for fields
	
	protected String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	protected SortedMap<Player, Integer> getPlayers() {
		if(players == null) {
			players = new TreeMap<Player, Integer>(new AppendToTailComperator<Player>());
		}
		return players;
	}

	protected void setPlayers(SortedMap<Player, Integer> players) {
		this.players = players;
	}

	public RuleCardManager getRuleCardManager() {
		return ruleCardManager;
	}

	protected void setRuleCardManager(RuleCardManager ruleCardManager) {
		this.ruleCardManager = ruleCardManager;
	}

	public Board getBoard() {
		return board;
	}

	protected void setBoard(Board board) {
		this.board = board;
	}

	protected SortedSet<Turn> getTurns() {
		if(turns == null) {
			turns = new TreeSet<Turn>(new AppendToTailComperator<Turn>());
		}
		return turns;
	}

	protected void setTurns(SortedSet<Turn> turns) {
		this.turns = turns;
	}

	public Date getStarted() {
		return started;
	}

	protected void setStarted(Date started) {
		this.started = started;
	}

	public Date getFinished() {
		return finished;
	}

	protected void setFinished(Date finished) {
		this.finished = finished;
	}
}
