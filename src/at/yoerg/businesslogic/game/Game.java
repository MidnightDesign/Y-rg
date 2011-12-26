package at.yoerg.businesslogic.game;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import at.yoerg.businesslogic.board.Board;
import at.yoerg.businesslogic.board.Field;
import at.yoerg.businesslogic.card.rulecard.RuleCardManager;
import at.yoerg.businesslogic.exception.EndOfGameException;
import at.yoerg.businesslogic.player.Person;
import at.yoerg.businesslogic.player.Player;
import at.yoerg.util.ArrayUtil;
import at.yoerg.util.CollectionUtil;


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
	// should be initialised with -1 so that the first nextPlayer() returns the Player with index 0
	private Integer currentPlayerPosition;
	
	// create instances of Game through GameFactory
	protected Game() {
	}
	
	public void start() {
		checkIfGameHasStarted();
		this.setStarted(new Date());
	}
	
	public void finish() {
		checkIfGameHasFinished();
		if(started == null) {
			throw new IllegalStateException("Game has not started yet");
		}
		this.setFinished(new Date());
	}

	public boolean addPlayer(Person person) {
		checkIfGameHasStarted();
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
		checkIfGameHasFinished();
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
		checkIfGameHasFinished();
		if(!validatePips(pips)) {
			throw new IllegalArgumentException("pips not valid. must be between 1 and 6.");
		}
		
		// gets the nex player in line
		Player p = getNextPlayer();
		
		// get next field for player
		Field f = getNextFieldForPlayer(p, pips);
		
		// create new turn to return
		Turn t = new Turn();
		t.setField(f);
		t.setPips(pips);
		t.setPlayer(p);
		
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

		// calculate new player position according to the finiteness of the game 
		int newPlayerPosition = (getFinite()) ? (playerPosition + pips) : ArrayUtil.getCycledPosition(fieldArray, (playerPosition + pips));
		
		// for infinite games
		if(!getFinite()) {
			f = ArrayUtil.getElementWithNewPosition(fieldArray, newPlayerPosition);
		} else if(newPlayerPosition > fieldArray.length) {
			setFinished(new Date());
			throw new EndOfGameException(player);
		} else {
			f = fieldArray[newPlayerPosition];
		}
		player.setPosition(newPlayerPosition);
		return f;
	}
	
	// returns the next player in line
	private Player getNextPlayer() throws IllegalStateException {
		if(getPlayers().size() == 0) {
			throw new IllegalStateException("no players added to game");
		}
		int newPlayerPosition = CollectionUtil.getCycledCollectionIndex(getPlayers(), getCurrentPlayerPosition() + 1);
		setCurrentPlayerPosition(newPlayerPosition);
		Player next = getPlayers().get(newPlayerPosition); 
		return next;
	}
	
	private void checkIfGameHasStarted() {
		if(getStarted() != null) {
			throw new IllegalStateException("game has already started");
		}
	}
	
	private void checkIfGameHasFinished() {
		if(getFinished() != null) {
			throw new IllegalStateException("game has already been finished");
		}
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

	protected List<Player> getPlayers() {
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

	protected Integer getCurrentPlayerPosition() {
		return currentPlayerPosition;
	}

	protected void setCurrentPlayerPosition(Integer currentPlayerPosition) {
		this.currentPlayerPosition = currentPlayerPosition;
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
