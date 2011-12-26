package at.yoerg.businesslogic.player;

import java.util.ArrayList;
import java.util.List;

import at.yoerg.businesslogic.game.Game;
import at.yoerg.businesslogic.game.drinkinggame.drink.Sip;

public class Player {

	private Person person;	
	private List<Sip> sips;
	private Integer position;
	private Game game;
	private Boolean finishedGame;
	
	protected Player() {
	}
	
	public static Player getPlayer(Person person, Game game) {
		if(person == null || game == null) {
			throw new NullPointerException("person or game is null.");
		} 
		Player p = new Player();
		
		p.setPerson(person);
		p.setPosition(0);
		p.setSips(new ArrayList<Sip>());
		p.setGame(game);
		p.setFinishedGame(false);
		
		return p;
	}

	public Person getPerson() {
		return person;
	}

	protected void setPerson(Person person) {
		this.person = person;
	}

	public List<Sip> getSips() {
		return sips;
	}

	protected void setSips(List<Sip> sips) {
		this.sips = sips;
	}
	
	public Integer getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Game getGame() {
		return game;
	}

	protected void setGame(Game game) {
		this.game = game;
	}

	public Boolean getFinishedGame() {
		return finishedGame;
	}

	protected void setFinishedGame(Boolean finishedGame) {
		this.finishedGame = finishedGame;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		Player other = (Player) obj;
		if (game == null) {
			if (other.game != null) {
				return false;
			}
		} else if (!game.equals(other.game)) {
			return false;
		}
		if (person == null) {
			if (other.person != null) {
				return false;
			}
		} else if (!person.equals(other.person)) {
			return false;
		}
		return true;
	}
}
