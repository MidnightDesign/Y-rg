package at.yoerg.businesslogic.game;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import at.yoerg.businesslogic.board.Board;
import at.yoerg.businesslogic.card.rulecard.RuleCardManager;
import at.yoerg.businesslogic.player.Player;
import at.yoerg.util.CollectionUtil;

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
			throw new NullPointerException();
		}
		try {
			players.put(player, 0);
		} catch (IllegalArgumentException iae) {
			return false;
		}
		return true;
	}
	
	public boolean removePlayer(Player player) {
		if(player == null) {
			throw new NullPointerException();
		}
		
		Integer position = players.remove(player);
		if(position == null) {
			return false;
		}
		return true;
	}
	
	// returns a copy of the players set
	public Collection<Player> getAllPlayers() {
		return CollectionUtil.copy(getPlayers().keySet());
	}
	
	public Turn nextTurn(int pips) {
		
	}
	
	// returns the next player in line
	private Player getNextPlayer() {
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
