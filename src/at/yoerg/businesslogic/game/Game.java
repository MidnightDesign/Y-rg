package at.yoerg.businesslogic.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import at.yoerg.businesslogic.board.Board;
import at.yoerg.businesslogic.board.Field;
import at.yoerg.businesslogic.card.rulecard.RuleCardManager;
import at.yoerg.businesslogic.exception.EndOfGameException;
import at.yoerg.businesslogic.player.Person;
import at.yoerg.businesslogic.player.Player;
import at.yoerg.util.ArrayUtil;
import at.yoerg.util.CollectionUtil;
import at.yoerg.util.comparator.AppendToTailComperator;


public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3509633604478116999L;
	
	private String id;
	private List<Player> players;
	private RuleCardManager ruleCardManager;
	private List<Turn> turns;
	private Board board;
	private Date started;
	private Date finished;
	private Boolean finite;
	
	private int currentPlayerPosition;
	
	// create instances of Game through GameFactory
	protected Game() {
		currentPlayerPosition = 0;
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

	public boolean addPlayer(Person person) {
		if(person == null) {
			throw new NullPointerException("player is null");
		}
		Player p = Player.getPlayer(person, this);
		if(getPlayers().contains(p)) {
			return false;
		}
		return getPlayers().add(p);
	}
	
	public boolean removePlayer(Player player) {
		if(player == null) {
			throw new NullPointerException("player is null");
		}
		return getPlayers().remove(player);
	}
	
	public int getNumberOfPlayers() {
		return getPlayers().size();
	}
	
	// returns a copy of the players set
	public Collection<Player> getAllPlayers() {
		return CollectionUtil.copy(getPlayers());
	}
	
	public Turn nextTurn(int pips) throws IllegalArgumentException, IllegalStateException, EndOfGameException {
		if(!validatePips(pips)) {
			throw new IllegalArgumentException("pips not valid. must be between 1 and 6.");
		}
		
		// gets the nex player in line
//		Player p = getNextPlayer();
		
		// get next field for player
//		Field f = getNextFieldForPlayer(p, pips);
		
		Turn t = new Turn();
		
		return t;
	}
	
	private boolean validatePips(int pips) {
		if(pips < 1 || pips > 6) {
			return false;
		}
		return true;
	}
	
	private Field getNextFieldForPlayer(Player player, int pips) throws EndOfGameException, IllegalStateException, IllegalArgumentException, NullPointerException {
		if(player == null) {
			throw new NullPointerException("player can not be null");
		}
		if(board == null) {
			throw new IllegalStateException("no board initialised");
		}
		
		Integer playerPosition = player.getPosition();
		playerPosition = (playerPosition.equals(0)) ? -1 : playerPosition;
		
		Field f = null;
		Field[] fieldArray = getBoard().getAllFields().toArray(new Field[0]);

		int newPlayerPosition = ArrayUtil.getCycledPosition(fieldArray, (playerPosition + pips));
		
		// for infinite games
		if(!getFinite()) {
			f = ArrayUtil.getElementWithOffsetFromPosition(fieldArray, playerPosition, pips);
		} else if((playerPosition + pips) > fieldArray.length) {
			throw new EndOfGameException(player);
		} else {
			
		}
		return f;
	}
	
	// returns the next player in line
//	private Player getNextPlayer() throws IllegalStateException {
//		if(getPlayers().size() == 0) {
//			throw new IllegalStateException("no players added to game");
//		}
//		// if no turns have been played return the first player in line
//		if(getTurns().size() == 0) {
//			return getPlayers().first();
//		}
//		Player lastPlayer = getTurns().last().getPlayer();
//		Iterator<Player> it = getPlayers().iterator();
//		Player current = null;
//		while(it.hasNext()) {
//			current = it.next();
//			if(lastPlayer.equals(current)) {
//				if(it.hasNext()) {
//					current = it.next();
//				} else {
//					current = getPlayers().first();
//				}
//				break;
//			}
//		}
//		return current;
//	}
	
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

	protected List<Player> getPlayers() {
		if(players == null) {
			players = new ArrayList<Player>();
		}
		return players;
	}

	protected void setPlayers(List<Player> players) {
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

	protected List<Turn> getTurns() {
		if(turns == null) {
			turns = new ArrayList<Turn>();
		}
		return turns;
	}

	protected void setTurns(List<Turn> turns) {
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
	
	public Boolean getFinite() {
		return finite;
	}
	
	protected void setFinite(Boolean finite) {
		this.finite = finite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Game other = (Game) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
