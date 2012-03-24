package at.yoerg.android.datastore;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import at.yoerg.businesslogic.player.Person;
import at.yoerg.businesslogic.player.Player;

public interface PersonStore {

	public Collection<Person> getPeople();
	
	public void savePerson(Person p);
	
}
