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
