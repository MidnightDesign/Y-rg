package at.yoerg.businesslogic.player;

import java.util.UUID;

public class Player {
	
	private String name;
	private Sex sex;
	private String id;
	
	protected Player() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
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
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Sex getSex() {
		return sex;
	}
	
	public static Player create(String name) {
		Player player = new Player();
		player.setName(name);
		player.setSex(Sex.UNKNOWN);
		player.id = UUID.randomUUID().toString();
		return player;
	}

	public static enum Sex {
		MALE, FEMALE, UNKNOWN
	}

}
