package at.yoerg.util;


public abstract class ArrayUtil {

	// position: 0 - (array-length - 1)
	// offset: starting from position go right offset-times
	public static <T> T getElementWithOffsetFromPosition(T[] array, int position, int offset) throws NullPointerException, IllegalArgumentException {
		checkArray(array);
		if(array.length < position) {
			throw new IllegalArgumentException("position greater than array length.");
		}
		if(offset < 0) {
			throw new IllegalArgumentException("offset must be positiv.");
		}
		
		int newPosition = position + offset;
		if(array.length > newPosition) {
			return array[newPosition];
		}
		return getElementWithOffsetFromPosition(array, 0, (newPosition - array.length));
	}
	
	public static <T> int getCycledPosition(T[] array, int position) throws NullPointerException {
		checkArray(array);
		return (array.length < position) ? position : getCycledPosition(array, (position - array.length));
	}
	
	private static <T> void checkArray(T[] array) throws NullPointerException {
		if(array == null) {
			throw new NullPointerException("array is null.");
		}
	}
}
