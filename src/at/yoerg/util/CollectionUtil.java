package at.yoerg.util;

import java.util.ArrayList;
import java.util.Collection;

public abstract class CollectionUtil {

	public static <T> Collection<T> copy(Collection<T> collection) {
		checkCollection(collection);
		Collection<T> coll = new ArrayList<T>();
		for(T elem : collection) {
			coll.add(elem);
		}
		return coll;
	}
	
	public static <T> T[] asArray(Collection<T> collection) {
		checkCollection(collection);
		@SuppressWarnings("unchecked")
		T[] collArray = (T[])collection.toArray(); 
		return collArray;
	}
	
	private static void checkCollection(Collection<?> collection) {
		if(collection == null) {
			throw new NullPointerException("collection can not be null.");
		}
	}
	
}
