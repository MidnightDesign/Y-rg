package at.yoerg.util;


public abstract class ArrayUtil {

	public static <T> T getElementWithNewPosition(T[] array, int newPosition) {
		checkArray(array);
		
		if(newPosition < 0) {
			return getElementWithNewPosition(array, newPosition + array.length);
		}
		
		if(array.length > newPosition) {
			return array[newPosition];
		}
		return getElementWithNewPosition(array, (newPosition - array.length));
	}
	
	public static <T> int getCycledPosition(T[] array, int position) throws NullPointerException {
		checkArray(array);
		return (position < array.length) ? position : getCycledPosition(array, (position - array.length));
	}
	
	private static <T> void checkArray(T[] array) throws NullPointerException {
		if(array == null) {
			throw new NullPointerException("array is null.");
		}
	}
}
