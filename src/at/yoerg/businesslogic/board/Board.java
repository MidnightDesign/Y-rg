package at.yoerg.businesslogic.board;

import java.util.Collection;
import java.util.List;

import at.yoerg.util.CollectionUtil;

public class Board {

	private List<Field> fields;

	public Collection<Field> getAllFields() {
		return CollectionUtil.copy(getFields());
	}
	
	protected List<Field> getFields() {
		return fields;
	}

	protected void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
