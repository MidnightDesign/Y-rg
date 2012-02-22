package at.yoerg.businesslogic.game;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import at.yoerg.android.activity.PlayerNameExistsException;
import at.yoerg.businesslogic.board.Board;
import at.yoerg.businesslogic.board.Field;
import at.yoerg.businesslogic.card.rulecard.RuleCardManager;
import at.yoerg.businesslogic.exception.EndOfGameException;
import at.yoerg.businesslogic.game.drinkinggame.drink.Sip;
import at.yoerg.businesslogic.player.Person;
import at.yoerg.businesslogic.player.Player;
import at.yoerg.businesslogic.rule.PlayerCenteredRule;
import at.yoerg.businesslogic.rule.Rule;
import at.yoerg.util.ArrayUtil;
import at.yoerg.util.CollectionUtil;


public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3509633604478116999L;
	
	private static final int MIN_PLAYERS = 3;
	
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
	private Turn currentTurn;
	
	// create instances of Game through GameFactory
	protected Game() {
	}
	
	public void start() {
		checkIfGameHasStarted();
		if(players.size() < MIN_PLAYERS) {
			throw new IllegalStateException("not enough players. at least " + MIN_PLAYERS + " are required.");
		}
		this.setStarted(new Date());
	}
	
	public void finish() {
		checkIfGameHasFinished();
		if(started == null) {
			throw new IllegalStateException("Game has not started yet");
		}
		this.setFinished(new Date());
	}

	public boolean addPlayer(Person person) throws PlayerNameExistsException {
		if(person == null) {
			throw new NullPointerException("player is null");
		}
		String playerName = person.getName();
		if(playerNameExists(playerName)) {
			throw new PlayerNameExistsException("A player with the name \"" + playerName + "\" already exists.");
		}
		Player p = Player.getPlayer(person, this);
		if(getPlayers().contains(p)) {
			return false;
		}
		return getPlayers().add(p);
	}
	
	private boolean playerNameExists(String name) {
		Collection<Player> players = getAllPlayers();
		Iterator<Player> iterator = players.iterator(); 
		while(iterator.hasNext()) {
			String currentName = iterator.next().getName();
			boolean x = currentName.equalsIgnoreCase(name);
			if(x) {
				return true;
			}
		}
		return false;
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
	public List<Player> getAllPlayers() {
		return (List<Player>)CollectionUtil.copy(getPlayers());
	}
	
	// returns the next turn containing the next player
	// throws IllegalStateException if game has not started or current turn has not been finished yet
	public Turn nextTurn() throws IllegalStateException {
		checkIfGameHasFinished();
		checkCurrentTurn();
		
		// gets the nex player in line
		Player p = getNextPlayer();
		
		// creates the new turn for the player
		Turn t = new Turn(p, this);
		currentTurn = t;
		
		return t;
	}
	
	private void checkCurrentTurn() {
		if(currentTurn == null) {
			return;
		}
		if(!currentTurn.played) {
			throw new IllegalStateException("current turn has not been played yet.");
		}
	}
	
	public Turn getCurrentTurn() {
		return currentTurn;
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
		if(!validatePips(pips)) {
			throw new IllegalArgumentException("pips must be between 1 and 6");
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
	// if player has not played his turn the same player gets returned every time
	private Player getNextPlayer() throws IllegalStateException {
		if(getPlayers().size() == 0) {
			throw new IllegalStateException("no players added to game");
		}
		int newPlayerPosition = CollectionUtil.getCycledCollectionIndex(getPlayers(), getCurrentPlayerPosition() + 1);
		setCurrentPlayerPosition(newPlayerPosition);
		Player next = getPlayers().get(newPlayerPosition); 
		return next;
	}
	
	public void givePlayerSips(Player player, int sips) throws NullPointerException, IllegalArgumentException {
		if(player == null) {
			throw new NullPointerException("player can not be null");
		}
		if(!getPlayers().contains(player)) {
			throw new IllegalArgumentException("provided player is not part of the game");
		}
		if(sips < 1) {
			throw new IllegalArgumentException("sips must be greater than 0");
		}
		for(int i = 0; i < sips; i++) {
			player.addSip(new Sip());
		}
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
	
	public static class Turn implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8645924455142975915L;
		
		private Player player;
		private Integer pips;
		private Field field;
		private Game game;
		private Boolean played = false;
		
		protected Turn(Player player, Game game) {
			setPlayer(player);
			this.game = game;
		}

		public Player getPlayer() {
			return player;
		}

		protected void setPlayer(Player player) {
			this.player = player;
		}

		public Integer getPips() {
			return pips;
		}

		protected void setPips(Integer pips) {
			this.pips = pips;
		}
		
		public Field getField(int pips) throws IllegalArgumentException, IllegalStateException, EndOfGameException {
			if(field != null) {
				return field;
			}
			game.validatePips(pips);
			this.pips = pips;
			field = game.getNextFieldForPlayer(player, pips);List<Rule> rules = field.getRules();
			
			// check if rules need user input or not
			// if not, set required fields
			for(Rule r : rules) {
				if(r instanceof PlayerCenteredRule) {
					((PlayerCenteredRule) r).setCurrentPlayer(player);
					((PlayerCenteredRule) r).setAllPlayers(game.getAllPlayers());
				}
			}

			return field;
		}

		protected Field getField() {
			return field;
		}

		protected void setField(Field field) {
			this.field = field;
		}
		
		// finishes this turn
		// throws IllegalStateException if no field is set and no pips are set (getField(int pips) has never been successfully invoked)
		public void finish() throws IllegalStateException {
			if(field == null || pips == null) {
				throw new IllegalStateException("method getField(int pips) has never been successfully invoked");
			}
			this.played = true;
		}
	}
}
