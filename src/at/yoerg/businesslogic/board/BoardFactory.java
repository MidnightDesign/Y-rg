package at.yoerg.businesslogic.board;

import java.io.IOException;
import java.util.List;

public enum BoardFactory {
	INSTANCE;
	
	private static final int RANDOM_BOARD_FIELD_COUNT = 100;
	
	public Board createRandomBoard() {
		return createRandomBoard(RANDOM_BOARD_FIELD_COUNT);
	}
	
	public Board createRandomBoard(int fieldCount) {
		try {
			return createBoard(FieldManager.getInstance().getRandomFields(fieldCount));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Board createBoard(List<Field> fields) {
		Board board = new Board();
		board.setFields(fields);
		return board;
	}
}
