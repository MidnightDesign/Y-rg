package at.yoerg.businesslogic.board;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import at.yoerg.businesslogic.rule.Rule;
import at.yoerg.businesslogic.rule.drinkingRule.playerCenteredRule.AllPlayersDrinkingRule;
import at.yoerg.businesslogic.rule.drinkingRule.playerCenteredRule.NeighboursDrinkingRule;
import at.yoerg.businesslogic.rule.drinkingRule.playerCenteredRule.PlayerCenteredDrinkingRule;
import at.yoerg.businesslogic.rule.drinkingRule.playerCenteredUserInputRule.PlayerAmountDrinkingRule;
import at.yoerg.businesslogic.rule.drinkingRule.playerCenteredUserInputRule.PlayerDecisionDrinkingRule;
import at.yoerg.businesslogic.rule.drinkingRule.userInputRule.PlayerListDrinkingRule;
import at.yoerg.businesslogic.rule.drinkingRule.userInputRule.PlayersDrinkingRule;
import at.yoerg.util.CollectionUtil;

public class FieldManager {
	
	private static final String PATH_TO_FIELD_TITLE = "at/yoerg/businesslogic/board/field_title_en.properties";
	private static final String PATH_TO_FIELD_TEXT = "at/yoerg/businesslogic/board/field_text_en.properties";
	private static FieldManager instance = null;
	
	private Set<Field> registeredFields;
	
	public static FieldManager getInstance() throws IOException {
		if(instance == null) {
			instance = new FieldManager();
		}
		return instance;
	}
	
	private FieldManager() throws IOException {
		registeredFields = new HashSet<Field>();
		init();
	}
	
	private void init() throws IOException {
		// initialise FieldManager with current fields
		
		// load the properties file
		Properties fieldText = new Properties();
		Properties fieldTitle = new Properties();
		InputStream fieldTextIs = this.getClass().getClassLoader().getResourceAsStream(PATH_TO_FIELD_TEXT);
		InputStream fieldTitleIs = this.getClass().getClassLoader().getResourceAsStream(PATH_TO_FIELD_TITLE);
		fieldText.load(fieldTextIs);
		fieldTitle.load(fieldTitleIs);
		
		// hardcode the fields
		Field f = null;
		List<Rule> rules = null;
		
		// drink 1
		rules = new ArrayList<Rule>();
		rules.add(new PlayerCenteredDrinkingRule(fieldText.getProperty("drink_one"), 1));
		f = Field.getField(fieldTitle.getProperty("drink_one"), rules);
		registerField(f);
		
		// drink 2
		rules = new ArrayList<Rule>();
		rules.add(new PlayerCenteredDrinkingRule(fieldText.getProperty("drink_two"), 2));
		f = Field.getField(fieldTitle.getProperty("drink_two"), rules);
		registerField(f);
		
		// give 1
		rules = new ArrayList<Rule>();
		rules.add(new PlayersDrinkingRule(fieldText.getProperty("give_one"), 1));
		f = Field.getField(fieldTitle.getProperty("give_one"), rules);
		registerField(f);
		
		// give 2
		rules = new ArrayList<Rule>();
		rules.add(new PlayersDrinkingRule(fieldText.getProperty("give_two"), 2));
		f = Field.getField(fieldTitle.getProperty("give_two"), rules);
		registerField(f);
		
		// drink left
		rules = new ArrayList<Rule>();
		rules.add(new NeighboursDrinkingRule(fieldText.getProperty("drink_left"), 1, NeighboursDrinkingRule.Type.LEFT));
		f = Field.getField(fieldTitle.getProperty("drink_left"), rules);
		registerField(f);
		
		// drink right
		rules = new ArrayList<Rule>();
		rules.add(new NeighboursDrinkingRule(fieldText.getProperty("drink_right"), 1, NeighboursDrinkingRule.Type.RIGHT));
		f = Field.getField(fieldTitle.getProperty("drink_right"), rules);
		registerField(f);
		
		// social
		rules = new ArrayList<Rule>();
		rules.add(new AllPlayersDrinkingRule(fieldText.getProperty("social"), 1));
		f = Field.getField(fieldTitle.getProperty("social"), rules);
		registerField(f);
		
		// free pass
		//TODO: free pass field
		
		// rule card
		//TODO: rule card field
		
		// like a virgin
		rules = new ArrayList<Rule>();
		rules.add(new PlayerDecisionDrinkingRule(fieldText.getProperty("like_a_virgin"), 5));
		f = Field.getField(fieldTitle.getProperty("like_a_virgin"), rules);
		registerField(f);
		
		// i never
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("i_never"), 1));
		f = Field.getField(fieldTitle.getProperty("i_never"), rules);
		registerField(f);
		
		// the loner
		rules = new ArrayList<Rule>();
		rules.add(new PlayerDecisionDrinkingRule(fieldText.getProperty("the_loner"), 1));
		f = Field.getField(fieldTitle.getProperty("the_loner"), rules);
		registerField(f);
		
		// brothers
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("brothers"), 1));
		f = Field.getField(fieldTitle.getProperty("brothers"), rules);
		registerField(f);
		
		// sisters
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("sisters"), 1));
		f = Field.getField(fieldTitle.getProperty("sisters"), rules);
		registerField(f);
		
		// shut up
		//TODO: shut up
		
		// music expert
		rules = new ArrayList<Rule>();
		rules.add(new PlayerDecisionDrinkingRule(fieldText.getProperty("music_expert"), 3));
		f = Field.getField(fieldTitle.getProperty("music_expert"), rules);
		registerField(f);
		
		// the one before the last one
		//TODO: the one before the last one field
		
		// my siblings
		rules = new ArrayList<Rule>();
		rules.add(new PlayerAmountDrinkingRule(fieldText.getProperty("my_siblings"), 1));
		f = Field.getField(fieldTitle.getProperty("my_siblings"), rules);
		registerField(f);
		
		// name that bitch
		rules = new ArrayList<Rule>();
		rules.add(new at.yoerg.businesslogic.rule.drinkingRule.userInputRule.PlayerDecisionDrinkingRule(fieldText.getProperty("name_that_bitch"), 5));
		f = Field.getField(fieldTitle.getProperty("name_that_bitch"), rules);
		registerField(f);
		
		// the youth of today
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("the_youth_of_today"), 1));
		f = Field.getField(fieldTitle.getProperty("the_youth_of_today"), rules);
		registerField(f);
		
		// old geezers
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("old_geezers"), 1));
		f = Field.getField(fieldTitle.getProperty("old_geezers"), rules);
		registerField(f);
		
		// those damned healthy people
		rules = new ArrayList<Rule>();
		rules.add(new PlayerAmountDrinkingRule(fieldText.getProperty("those_damned_healthy_people"), 1));
		f = Field.getField(fieldTitle.getProperty("those_damned_healthy_people"), rules);
		registerField(f);
		
		// guys
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("guys"), 1));
		f = Field.getField(fieldTitle.getProperty("guys"), rules);
		registerField(f);
		
		// chicks
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("chicks"), 1));
		f = Field.getField(fieldTitle.getProperty("chicks"), rules);
		registerField(f);
		
		// drink of shame
		rules = new ArrayList<Rule>();
		rules.add(new PlayerAmountDrinkingRule(fieldText.getProperty("drink_of_shame"), 1));
		f = Field.getField(fieldTitle.getProperty("drink_of_shame"), rules);
		registerField(f);
		
		// remember that time?
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("remember_that_time"), 1));
		f = Field.getField(fieldTitle.getProperty("remember_that_time"), rules);
		registerField(f);
		
		// oral love
		rules = new ArrayList<Rule>();
		rules.add(new PlayerListDrinkingRule(fieldText.getProperty("oral_love"), 1));
		f = Field.getField(fieldTitle.getProperty("oral_love"), rules);
		registerField(f);
		
		// one bad idea
		//TODO: one bad idea field
		
		// just kidding
		//TODO: just kidding field
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
