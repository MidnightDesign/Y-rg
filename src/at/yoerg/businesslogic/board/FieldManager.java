package at.yoerg.businesslogic.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import at.yoerg.util.CollectionUtil;

public class FieldManager {
	
	private static FieldManager fieldManager = null;
	
	private Set<Field> registeredFields;
	
	public static FieldManager getInstance() {
		if(fieldManager == null) {
			fieldManager = new FieldManager();
		}
		return fieldManager;
	}
	
	private FieldManager() {
		registeredFields = new HashSet<Field>();
		init();
	}
	
	private void init() {
		//TODO: initialise FieldManager
	}
	
	public Collection<Field> getAllRegisteredFields() {
		return CollectionUtil.copy(registeredFields);
	}
	
	public List<Field> getRandomFields(int fieldCount) {
		if(fieldCount < 1) {
			throw new IllegalArgumentException("fieldCount has to be greater than 0");
		}
		List<Field> randomFields = new ArrayList<Field>();
		Field[] fieldArray = (Field[])registeredFields.toArray();
		
		for(int i = 0; i < fieldCount; i++) {
			randomFields.add(getRandomField(fieldArray));
		}
		
		return randomFields;
	}
	
	private Field getRandomField(Field[] fieldArray) {
		Random rand = new Random();
		int fieldsSize = fieldArray.length;
		int randomOffset = rand.nextInt(fieldsSize - 1);
		return fieldArray[randomOffset];	
	}
	
	public void registerField(Field field) {
		if(field == null) {
			throw new NullPointerException();
		}
		registeredFields.add(field);
	}
}
